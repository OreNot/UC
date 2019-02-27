package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.*;
import ucproject.repos.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

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

    @GetMapping("/allorders")
    public String allorders(@RequestParam(required = false, defaultValue = "") String filter,
                            @RequestParam(required = false, defaultValue = "0") String radio,
                            @RequestParam(required = false, defaultValue = "0") String radiofilter,
                            Map<String, Object> model) {

        filter = filter.replaceAll(",", "");
        String s = radio;
        Iterable<Statement> statements = statementRepo.findAll();
        Iterable<User> users = userRepo.findAll();
        Map<String, Integer> mp = new HashMap<>();

        for (User user : users)
        {
            mp.put(user.getUsername(), statementRepo.findByStatusNotLikeAndExecutor("В архиве", userRepo.findByUsername(user.getUsername())).size());
        }



        if (filter != null && !filter.isEmpty()) {

            switch (radiofilter) {

                case "executorfilter" :
                    if (filter.replaceAll(",", "").equals("Все"))
                    {
                        statements = statementRepo.findAll();
                    }
                    else {
                        statements = statementRepo.findByExecutor(userRepo.findByUsername(filter));
                    }
                break;

                case  "orgfilter" :
                    Organization upOrg = organizationRepo.findByOrgNameContaining(filter.replaceAll(",", ""));
                    Client upClientByOrg = clientRepo.findByOrganization(upOrg);
                    statements = statementRepo.findByClient(upClientByOrg);
                    break;

                case  "fiofilter" :
                    Fio updFio = fioRepo.findByFioContaining(filter.replaceAll(",", ""));
                    Client upClientByFio = clientRepo.findByFio(updFio);
                    statements = statementRepo.findByClient(upClientByFio);
                    break;


            }
        }
        else
        {
            statements = statementRepo.findAll();
        }
        model.put("statements", statements);
        model.put("usercol", mp);
        model.put("filter", filter);
        return "allorders";
    }

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
}
