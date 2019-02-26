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
import ucproject.domain.Statement;
import ucproject.domain.User;
import ucproject.repos.StatementRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class ArchiveController {

    @Autowired
    public StatementRepo statementRepo;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/addtoarchive")
    public String addtoarchive(
            @AuthenticationPrincipal User user,
            Map<String, Object> model)
    {
        Iterable<Statement> statements = statementRepo.findByStatusAndExecutor("Ждёт отправки в архив", user);

        model.put("statements", statements);

        return "addtoarchive";
    }

    @PostMapping("/addtoarchive")
    public String addtoarchive(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "0") String radio,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model)
    {

        Statement statement = null;

        if(!radio.equals(""))
        {
            statement = statementRepo.findById(Integer.parseInt(radio)).get();

            if (file != null && !file.getOriginalFilename().equals(""))
            {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists())
                {
                    uploadDir.mkdir();
                }

                File fioDir = new File(uploadPath + "/" + statement.getClientFio());
                if (!fioDir.exists())
                {
                    fioDir.mkdir();
                }

                //String uuidFile = UUID.randomUUID().toString();
                String ordFName = statement.getFilename().substring(0, statement.getFilename().lastIndexOf("."));
                String fType =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
                String resultFileName = ordFName + "_ЛС" + fType;

                File destFile = new File(uploadPath + "/" + resultFileName);
                try {
                    file.transferTo(destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                statement.setPackfilename(resultFileName);
                statement.setStatus("В архиве");
                statementRepo.save(statement);
            }
        }


        Iterable<Statement> statements = statementRepo.findByStatusAndExecutor("Ждёт отправки в архив", user);

        model.put("statements", statements);



        return "addtoarchive";
    }

}
