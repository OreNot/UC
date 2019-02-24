package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.*;
import ucproject.repos.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class AllOrdersController {

    @Autowired
    public StatementRepo statementRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public FioRepo fioRepo;

    @Autowired
    public OrganizationRepo organizationRepo;

    @Autowired
    public ClientRepo clientRepo;
/*
    @GetMapping("/allorders")
    public String allorders(@RequestParam(required = false, defaultValue = "") String filter,
                            @RequestParam(required = false, defaultValue = "0") String radio,
                            Map<String, Object> model) {

        String s = radio;
        Iterable<Statement> statements = statementRepo.findAll();

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
*/
    @GetMapping("/allmyorders")
    public String allorders(@AuthenticationPrincipal User user,
                            @RequestParam (required = false, defaultValue = "") String status,
                            @RequestParam(required = false, defaultValue = "0") String radio,
                            Map<String, Object> model) {


        Iterable<Statement> statements = statementRepo.findByExecutor(user);

        Statement updStatement;

        if (!status.equals("") && !radio.equals(""))
        {
            updStatement = statementRepo.findById(Integer.parseInt(radio)).get();
            updStatement.setStatus(status);
            statementRepo.save(updStatement);
        }

        statements = statementRepo.findByExecutor(user);
        model.put("statements", statements);

        return "allmyorders";
    }
/*
    @GetMapping("/setexec")
    public String setexec(@RequestParam(required = false, defaultValue = "") String executor,
                            @RequestParam(required = false, defaultValue = "0") String radio,
                            Map<String, Object> model) {

        String s = radio;
        Iterable<Statement> statements = statementRepo.findByExecutorNull();

        Statement upStatement;
        User user;
        if (!radio.equals("0"))
        {
            upStatement = statementRepo.findById(Integer.parseInt(radio)).get();
            user = userRepo.findByUsername(executor);
            upStatement.setExecutor(user);
            statementRepo.save(upStatement);
            statements = statementRepo.findByExecutorNull();
        }

        //statementRepo.save();

        model.put("statements", statements);
        return "setexec";
    }
*/
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
            @RequestParam String fio,
            @RequestParam String organization,
            Map<String, Object> model)
    {
        Fio newFio = new Fio(fio);
        fioRepo.save(newFio);

        Organization newOrg = new Organization(organization);
        organizationRepo.save(newOrg);

        Client newClient = new Client(newFio, newOrg);
        clientRepo.save(newClient);

        Statement statement = new Statement(comment, user, newClient, "Зарегистрировано");
        statementRepo.save(statement);

        return "addorder";
    }



    @GetMapping("/logout")
    public String logout(Map<String, Object> model)
    {
        return "main";
    }
}
