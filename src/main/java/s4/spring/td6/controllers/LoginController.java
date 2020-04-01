package s4.spring.td6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td6.models.User;
import s4.spring.td6.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        if (userRepository.findAll().size() == 0) {
            User user = new User("NathanBnm", "azerty", "bonnemainsnathan@gmail.com", "Nathan Bonnemains");
            userRepository.saveAndFlush(user);
        }
        return "login";
    }

    @PostMapping("/doLogin")
    public RedirectView doLogin(@RequestParam String login, @RequestParam String password, RedirectAttributes attributes, HttpSession session) {
        if (!"".equals(login) && !"".equals(password)) {
            User user = userRepository.findByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("connectedUser", user);
                    return new RedirectView("/index");
                } else {
                    attributes.addFlashAttribute("message", "Identifiants incorrects.");
                    attributes.addFlashAttribute("messageType", "error");
                    return new RedirectView("/login");
                }
            } else {
                attributes.addFlashAttribute("message", "Cet utilisateur n'existe pas.");
                attributes.addFlashAttribute("messageType", "error");
                return new RedirectView("/login");
            }
        } else {
            attributes.addFlashAttribute("message", "Tous les champs n'ont pas été remplis.");
            attributes.addFlashAttribute("messageType", "error");
            return new RedirectView("/login");
        }
    }

    @GetMapping("/logout")
    public RedirectView doLogout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/index");
    }
}
