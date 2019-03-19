package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucproject.domain.Fio;
import ucproject.domain.Role;
import ucproject.domain.User;
import ucproject.repos.FioRepo;
import ucproject.repos.UserRepo;
import ucproject.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private FioRepo fioRepo;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Map<String, Object> model)
    {
        model.put("users", userRepo.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Map<String, Object> model)
    {
        model.put("user", user);
        model.put("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam String fio,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username)
    {
        Fio upFio;
        if (fioRepo.findByFioIgnoreCase(fio.trim()) == null || fioRepo.findByFioIgnoreCase(fio.trim()).equals(""))
        {
            upFio = new Fio(fio.trim());
            fioRepo.save(upFio);
        }
        else
        {
            upFio = fioRepo.findByFioIgnoreCase(fio.trim());
        }
        //Fio upFio = new Fio(fio);
       // fioRepo.save(upFio);
        user.setUsername(username);
        user.setFio(upFio);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role :: name)
                .collect(Collectors.toSet());


        user.getRoles().clear();

        for(String key : form.keySet())
        {
            if (roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }


        userRepo.save(user);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Map<String, Object> model, @AuthenticationPrincipal User user)
    {
        model.put("username", user.getUsername());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password)
    {
        userService.updateProfile(user, password);
        return "redirect:/user/profile";
    }
}


