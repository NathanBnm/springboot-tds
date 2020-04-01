package s4.spring.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.models.History;
import s4.spring.td5.models.Script;
import s4.spring.td5.models.User;
import s4.spring.td5.repositories.HistoryRepository;
import s4.spring.td5.repositories.ScriptRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ScriptController {
    @Autowired
    ScriptRepository scriptRepository;

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/script/new")
    public String newScript(HttpSession session) {
        User connectedUser = (User) session.getAttribute("connectedUser");
        if (connectedUser != null)
            return "script/new";
        else
            return "login";
    }

    @GetMapping("/script/{id}")
    public String editScript(@PathVariable String id, ModelMap model) {
        Script script = scriptRepository.findById(Integer.parseInt(id));
        model.put("id", id);
        model.put("title", script.getTitle());
        model.put("description", script.getDescription());
        model.put("content", script.getContent());
        return "script/edit";
    }

    @PostMapping("/script/submit")
    public RedirectView submitScript(@RequestParam(required = false) String id, @RequestParam String title, @RequestParam String description, @RequestParam String content, @RequestParam(required = false) String comment, RedirectAttributes attributes, HttpSession session) {
        Script script = null;
        if (!"".equals(title) && !"".equals(description) && !"".equals(content)) {
            User connectedUser = (User) session.getAttribute("connectedUser");
            if (connectedUser != null) {
                if (!"".equals(id) && id != null) {
                    script = scriptRepository.findById(Integer.parseInt(id));
                    script.setTitle(title);
                    script.setDescription(description);
                    script.setContent(content);
                }
                if (script == null) {
                    script = new Script(title, description, content, new Date(), connectedUser);
                    attributes.addFlashAttribute("message", "Le script <strong>" + title + "</strong> a bien été ajouté.");
                } else {
                    attributes.addFlashAttribute("message", "Le script <strong>" + title + "</strong> a bien été modifié.");
                }
                scriptRepository.saveAndFlush(script);
                History history = new History(new Date(), content, comment, script);
                historyRepository.saveAndFlush(history);
                attributes.addFlashAttribute("messageType", "success");
                return new RedirectView("/index");
            } else {
                attributes.addFlashAttribute("message", "Session expirée.");
                attributes.addFlashAttribute("messageType", "error");
                return new RedirectView("/login");
            }
        } else {
            attributes.addFlashAttribute("message", "Tous les champs n'ont pas été remplis.");
            attributes.addFlashAttribute("messageType", "error");
            return new RedirectView("/script/new");
        }
    }
}
