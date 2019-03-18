package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.*;
import ucproject.repos.*;

import java.util.*;
//upload.path = \\\\gren-wd-000318\\OrdStorage
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Value("${urlprefix}")
    private String urlprefixPath;

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
        //Iterable<Statement> statements = statementRepo.findAll();
        List<Statement> statements = (List<Statement>) statementRepo.findAll();
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
                        statements = (List<Statement>) statementRepo.findAll();
                    }
                    else {
                        statements = statementRepo.findByExecutor(userRepo.findByUsername(filter));
                    }
                break;

                case  "orgfilter" :
                    Organization upOrg = organizationRepo.findByOrgNameContainingIgnoreCase(filter.replaceAll(",", ""));
                    //Organization upOrg = organizationRepo.findByOrgNameContaining(filter.replaceAll(",", ""));
                    List<Client> upClientsByOrg = (List<Client>) clientRepo.findByOrganization(upOrg);
                    statements.clear();
                    List<Statement> updStatements = new ArrayList<>();
                    for (Client client : upClientsByOrg)
                    {
                       updStatements.addAll(statementRepo.findByClient(client));


                    }
                    for (Statement stu : updStatements)
                    {
                        if (!statements.contains(stu))
                        {
                            statements.add(stu);
                        }
                    }
                    //statements = statementRepo.findByClients(upClientsByOrg);
                    break;

                case  "fiofilter" :
                    Fio updFio = fioRepo.findByFioContainingIgnoreCase(filter.replaceAll(",", ""));
                    List<Client> upClientsByFio = (List<Client>) clientRepo.findByFio(updFio);
                    //Client upClientByFio = clientRepo.findByFio(updFio);
                    statements.clear();
                    List<Statement> updStatementsByFio = new ArrayList<>();
                    for (Client client : upClientsByFio)
                    {
                        updStatementsByFio.addAll(statementRepo.findByClient(client));


                    }
                    for (Statement stu : updStatementsByFio)
                    {
                        if (!statements.contains(stu))
                        {
                            statements.add(stu);
                        }
                    }

                    //statements = statementRepo.findByClient(upClientByFio);
                    break;


            }
        }
        else
        {
            statements = (List<Statement>) statementRepo.findAll();
        }
        model.put("statements", statements);
        model.put("usercol", mp);
        model.put("filter", filter);
        model.put("urlprefixPath", urlprefixPath);
        return "allorders";
    }

    @GetMapping("/setexec")
    public String setexec(@RequestParam(required = false, defaultValue = "") String executor,
                          @RequestParam(required = false, defaultValue = "0") String radio,
                          Map<String, Object> model) {

        String s = radio;
        List <String> sts = new ArrayList<>();
        Iterable<Statement> statements = statementRepo.findByExecutorNull();

        Statement upStatement;
        User user;
        if (!radio.equals("0"))
        {
            if (radio.contains(",")) {
                sts.addAll(Arrays.asList(radio.split(",")));
            }
            else
            {
                sts.add(radio);
            }
            for (String st : sts)
            {
                upStatement = statementRepo.findById(Long.parseLong(st)).get();
                user = userRepo.findByUsername(executor);
                upStatement.setExecutor(user);
                statementRepo.save(upStatement);
            }
           /* upStatement = statementRepo.findById(Long.parseLong(radio)).get();
            user = userRepo.findByUsername(executor);
            upStatement.setExecutor(user);
            statementRepo.save(upStatement);
            */
            statements = statementRepo.findByExecutorNull();
        }

        Iterable<User> users = userRepo.findAll();
        Map<String, Integer> mp = new HashMap<>();

        for (User usr : users)
        {
            mp.put(usr.getUsername(), statementRepo.findByStatusNotLikeAndExecutor("В архиве", userRepo.findByUsername(usr.getUsername())).size());
        }


        //statementRepo.save();
        model.put("urlprefixPath", urlprefixPath);
        model.put("usercol", mp);
        model.put("statements", statements);
        return "setexec";
    }


}
