package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucproject.domain.Fio;
import ucproject.domain.Role;
import ucproject.domain.User;
import ucproject.repos.FioRepo;
import ucproject.repos.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FioRepo fioRepo;

    @GetMapping
    public String userList(Map<String, Object> model)
    {
        model.put("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Map<String, Object> model)
    {
        model.put("user", user);
        model.put("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam String fio,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username)
    {
        Fio upFio = new Fio(fio);
        fioRepo.save(upFio);
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
}


