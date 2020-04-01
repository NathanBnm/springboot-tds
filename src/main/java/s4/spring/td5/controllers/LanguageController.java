package s4.spring.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.models.Language;
import s4.spring.td5.models.User;
import s4.spring.td5.repositories.LanguageRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LanguageController {
    @Autowired
    LanguageRepository languageRepository;

    @GetMapping("/language/new")
    public String newLanguage(HttpSession session) {
        User connectedUser = (User) session.getAttribute("connectedUser");
        if (connectedUser != null)
            return "language/new";
        else
            return "login";
    }

    @PostMapping("language/submit")
    public RedirectView submitLanguage(@RequestParam String name, RedirectAttributes attributes) {
        if (!"".equals(name)) {
            Language language = new Language(name);
            languageRepository.saveAndFlush(language);
            return new RedirectView("/index");
        } else {
            attributes.addFlashAttribute("message", "Vous devez renseigner un nom de langage");
            attributes.addFlashAttribute("messageType", "error");
            return new RedirectView("/language/new");
        }
    }
}
