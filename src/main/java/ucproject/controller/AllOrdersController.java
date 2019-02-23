package ucproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucproject.domain.Statement;
import ucproject.repos.StatementRepo;

import java.util.List;
import java.util.Map;

@Controller
public class AllOrdersController {

    @Autowired
    public StatementRepo statementRepo;

    @GetMapping("/allorders")
    public String allorders(Map<String, Object> model) {
        Iterable<Statement> statements = statementRepo.findAll();
        model.put("statements", statements);
        model.put("orgList", "<option value=\"2\"></option>\n<option value=\"hello\"></option>");
        return "allorders";
    }

    @GetMapping("/addorder")
    public String addorder(Map<String, Object> model)
    {
        return "addorder";
    }

    @GetMapping
    public String main(String name, Map<String, Object> model)
    {
        model.put("name", "Ok");
        return "allorders";
    }

    @PostMapping
    public String add(@RequestParam String comment, Map<String, Object> model)
    {
        Statement statement = new Statement(comment);
        statementRepo.save(statement);
        return "addorder";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model)
    {
        Iterable<Statement> statements;
        if (filter != null && !filter.isEmpty()) {
            statements = statementRepo.findByComment(filter);
        }
        else
        {
            statements = statementRepo.findAll();
        }
        model.put("statements", statements);
        model.put("orgList", "<option value=\"2\"></option>\n<option value=\"hello\"></option>");
        return "allorders";
    }
}
