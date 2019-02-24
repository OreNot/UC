package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.Statement;
import ucproject.domain.User;
import ucproject.repos.StatementRepo;
import ucproject.repos.UserRepo;

import java.util.Map;

@Controller
public class AllOrdersController {

    @Autowired
    public StatementRepo statementRepo;

    @Autowired
    public UserRepo userRepo;

    @GetMapping("/allorders")
    public String allorders(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {

        Iterable<Statement> statements = statementRepo.findAll();

/*
        if (filter != null && !filter.isEmpty()) {
            statements = statementRepo.findByComment(filter);
        }
        else
        {
            statements = statementRepo.findAll();
        }
*/
        if (filter != null && !filter.isEmpty()) {

            statements = statementRepo.findByAutor(userRepo.findByUsername(filter));
        }
        else
        {
            statements = statementRepo.findAll();
        }
        model.put("statements", statements);
        model.put("filter", filter);
        return "allorders";
    }



    @GetMapping("/addorder")
    public String addorder(Map<String, Object> model)
    {
        return "addorder";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model)
    {
        return "main";
    }

    @PostMapping("/addorder")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String comment,
            Map<String, Object> model)
    {
        Statement statement = new Statement(comment, user);
        statementRepo.save(statement);
        return "addorder";
    }



    @GetMapping("/logout")
    public String logout(Map<String, Object> model)
    {
        return "main";
    }
}
