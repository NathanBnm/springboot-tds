package s4.spring.td6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td6.models.*;
import s4.spring.td6.repositories.CategoryRepository;
import s4.spring.td6.repositories.HistoryRepository;
import s4.spring.td6.repositories.LanguageRepository;
import s4.spring.td6.repositories.ScriptRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ScriptController {
    @Autowired
    ScriptRepository scriptRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/script/new")
    public String newScript(ModelMap model, HttpSession session) {
        User connectedUser = (User) session.getAttribute("connectedUser");
        if (connectedUser != null) {
            model.put("categories", categoryRepository.findAll());
            model.put("languages", languageRepository.findAll());
            return "script/new";
        } else {
            return "login";
        }
    }

    @GetMapping("/script/{id}")
    public String editScript(@PathVariable String id, ModelMap model) {
        try {
            int scriptId = Integer.parseInt(id);
            Script script = scriptRepository.findById(scriptId);
            model.put("id", scriptId);
            model.put("title", script.getTitle());
            model.put("description", script.getDescription());
            model.put("content", script.getContent());
            model.put("category", script.getCategory());
            model.put("language", script.getLanguage());

            model.put("categories", categoryRepository.findAll());
            model.put("languages", languageRepository.findAll());
            return "script/edit";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "404";
        }
    }

    @PostMapping("/script/submit")
    public RedirectView submitScript(@RequestParam(required = false) String id,
                                     @RequestParam String title,
                                     @RequestParam String description,
                                     @RequestParam String content,
                                     @RequestParam Category category,
                                     @RequestParam Language language,
                                     @RequestParam(required = false) String comment,
                                     RedirectAttributes attributes,
                                     HttpSession session) {
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
                    script = new Script(title, description, content, new Date(), category, connectedUser, language);
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
