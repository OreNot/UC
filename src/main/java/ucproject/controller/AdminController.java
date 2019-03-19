package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.*;
import ucproject.repos.*;

import java.io.File;
import java.util.*;
//upload.path = \\\\gren-wd-000318\\OrdStorage
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Value("${urlprefix}")
    private String urlprefixPath;

    @Value("${upload.path}")
    private String uploadPath;

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
                    List<Organization> upOrg = organizationRepo.findByOrgNameContainingIgnoreCase(filter.replaceAll(",", ""));
                    //Organization upOrg = organizationRepo.findByOrgNameContaining(filter.replaceAll(",", ""));
                    List<Client> upClientsByOrg = new ArrayList<>();
                    for (Organization org : upOrg)
                    {
                        upClientsByOrg.addAll((List<Client>) clientRepo.findByOrganization(org));
                    }
                   // List<Client> upClientsByOrg = (List<Client>) clientRepo.findByOrganization(upOrg);
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
                          @RequestParam(required = false, defaultValue = "0") String redact,
                          Map<String, Object> model) {

        String s = radio;
        List <String> sts = new ArrayList<>();
        Iterable<Statement> statements = statementRepo.findByExecutorNull();

        Statement upStatement;

        if (!redact.equals("0") && redact.equals("redact"))
        {
            String stId = radio.split(",")[0];
            upStatement = statementRepo.findById(Long.parseLong(stId)).get();

            String orgForRedact = upStatement.getClientOrg().replaceAll("\"", "'");

            model.put("statement", upStatement);
            model.put("organization", orgForRedact);
            model.put("executor", executor);
            return "redact";
        }


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


    @GetMapping("/redact")
    public String redact(@RequestParam(required = false, defaultValue = "") String fio,
                         @RequestParam(required = false, defaultValue = "") String organization,
                         @RequestParam(required = false, defaultValue = "")String executor,
                         @RequestParam(required = false, defaultValue = "") String id,
                         @RequestParam(required = false, defaultValue = "") String comment,
                         Map<String, Object> model) {

        Statement statement = statementRepo.findById(Long.parseLong(id)).get();

        Fio newFio;
        if (fioRepo.findByFioIgnoreCase(fio.trim()) == null || fioRepo.findByFioIgnoreCase(fio.trim()).equals(""))
        {
            newFio = new Fio(fio.trim());
            fioRepo.save(newFio);
        }
        else
        {
            newFio = fioRepo.findByFioIgnoreCase(fio.trim());
        }

        Organization newOrg;
        organization = organization.replaceAll("'", "\"");
        if (organizationRepo.findByOrgNameIgnoreCase(organization.trim()) == null || organizationRepo.findByOrgNameIgnoreCase(organization.trim()).equals(""))
        {
            newOrg = new Organization(organization.trim());
            organizationRepo.save(newOrg);
        }
        else
        {
            newOrg = organizationRepo.findByOrgNameIgnoreCase(organization.trim());
        }

        statement.getClient().setFio(newFio);
        statement.getClient().setOrganization(newOrg);
        statement.setComment(comment);
        statement.setExecutor(userRepo.findByUsername(executor));

        statementRepo.save(statement);

        Iterable<Statement> statements = statementRepo.findByExecutorNull();
        Iterable<User> users = userRepo.findAll();
        Map<String, Integer> mp = new HashMap<>();

        for (User usr : users)
        {
            mp.put(usr.getUsername(), statementRepo.findByStatusNotLikeAndExecutor("В архиве", userRepo.findByUsername(usr.getUsername())).size());
        }

        model.put("urlprefixPath", urlprefixPath);
        model.put("usercol", mp);
        model.put("statements", statements);


        return "setexec";
    }


}
