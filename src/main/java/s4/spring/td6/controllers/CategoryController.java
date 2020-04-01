package s4.spring.td6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td6.models.Category;
import s4.spring.td6.models.User;
import s4.spring.td6.repositories.CategoryRepository;

import javax.servlet.http.HttpSession;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category/new")
    public String newCategory(HttpSession session) {
        User connectedUser = (User) session.getAttribute("connectedUser");
        if (connectedUser != null)
            return "category/new";
        else
            return "login";
    }

    @PostMapping("/category/submit")
    public RedirectView submitCategory(@RequestParam String name, RedirectAttributes attributes) {
        if (!"".equals(name)) {
            Category category = new Category(name);
            categoryRepository.saveAndFlush(category);
            return new RedirectView("/index");
        } else {
            attributes.addFlashAttribute("message", "Vous devez renseigner un nom de catégorie");
            attributes.addFlashAttribute("messageType", "error");
            return new RedirectView("/category/new");
        }
    }
}
