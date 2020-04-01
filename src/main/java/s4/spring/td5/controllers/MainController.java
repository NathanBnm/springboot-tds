package s4.spring.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import s4.spring.td5.models.User;
import s4.spring.td5.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"", "/", "index"})
    public String index(ModelMap model, HttpSession session) {
        User connectedUser = (User) session.getAttribute("connectedUser");
        if (connectedUser != null) {
            model.put("connected", true);
            model.put("identity", connectedUser.getIdentity());
            model.put("email", connectedUser.getEmail());
        }
        return "index";
    }
}
