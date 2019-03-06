package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ucproject.domain.*;
import ucproject.repos.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class AllOrdersController {

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

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/allmyorders")
    public String allorders(@AuthenticationPrincipal User user,
                            @RequestParam (required = false, defaultValue = "") String status,
                            @RequestParam(required = false, defaultValue = "0") String radio,
                            Map<String, Object> model) {


        Iterable<Statement> statements;


        Statement updStatement;

        if (!status.equals("") && !radio.equals(""))
        {
            updStatement = statementRepo.findById(Long.parseLong(radio)).get();
            updStatement.setStatus(status);
            statementRepo.save(updStatement);
        }

        statements = statementRepo.findByStatusNotLikeAndExecutor("В архиве", user);


        model.put("statements", statements);

        return "allmyorders";
    }

    @GetMapping("/addorder")
    public String addorder(Map<String, Object> model)
    {
        return "addorder";
    }



    @GetMapping("/")
    public String main(Map<String, Object> model)
    {
        model.put("urlprefixPath", urlprefixPath);
        return "main";
    }

    @PostMapping("/addorder")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String comment,
            @RequestParam String fio,
            @RequestParam String organization,
            @RequestParam String type,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) {

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
        if (organizationRepo.findByOrgNameIgnoreCase(organization.trim()) == null || organizationRepo.findByOrgNameIgnoreCase(organization.trim()).equals(""))
        {
            newOrg = new Organization(organization.trim());
            organizationRepo.save(newOrg);
        }
        else
        {
            newOrg = organizationRepo.findByOrgNameIgnoreCase(organization.trim());
        }

        Client newClient;

        if(clientRepo.findByFioAndOrganization(newFio, newOrg) == null) {
            newClient = new Client(newFio, newOrg);
        }
        else
        {
            newClient = clientRepo.findByFioAndOrganization(newFio, newOrg);
        }
        clientRepo.save(newClient);

        Statement statement = new Statement(user, newClient, type, comment, "Зарегистрировано");

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            File fioDir = new File(uploadPath + "/" + fio.trim());
            if (!fioDir.exists()) {
                fioDir.mkdir();
            }

            //String uuidFile = UUID.randomUUID().toString();
            String dateForFileName = new SimpleDateFormat("dd.MM.yyyy_HH_mm_ss").format(new Date());
            String resultFileName = dateForFileName + "_" + fio.split(" ")[0] + "_" + organization.trim().replaceAll("\"", "").replaceAll("\\\\\\\\", "-").replaceAll("/", "-").replaceAll(":", "-") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());

            File destFile = new File(fioDir + "/" + resultFileName);
            try {
                file.transferTo(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            statement.setFilename(fio.trim() + "/" + resultFileName);
        }

        statementRepo.save(statement);

        return "addorder";
    }

    /*


*/


    @GetMapping("/logout")
    public String logout(Map<String, Object> model)
    {
        return "main";
    }
}
