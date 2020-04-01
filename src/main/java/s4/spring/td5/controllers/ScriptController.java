package s4.spring.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.models.Script;
import s4.spring.td5.repositories.ScriptRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ScriptController {
    @Autowired
    ScriptRepository scriptRepository;

    @GetMapping("/script/new")
    public String newScript() {
        return "script/new";
    }

    @PostMapping("/script/submit")
    public RedirectView submitScript(@RequestParam String title, @RequestParam String description, @RequestParam String content, @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd") Date creationDate, ModelMap model, HttpSession session) {
        System.out.println(creationDate);
        if (!"".equals(title) && !"".equals(description) && !"".equals(content)) {
            Script script = new Script(title, description, content, creationDate);
            scriptRepository.saveAndFlush(script);
            return new RedirectView("/index");
        } else {
            return new RedirectView("/script/new");
        }
    }
}
