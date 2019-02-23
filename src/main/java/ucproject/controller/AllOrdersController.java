package ucproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AllOrdersController {

    @GetMapping("/allorders")
    public String allorders(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "allorders";
    }

    @GetMapping
    public String main(String name, Map<String, Object> model)
    {
        model.put("name", "Ok");
        return "allorders";
    }
}
