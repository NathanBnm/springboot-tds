package s4.spring.td1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td1.models.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("items")
public class MainController {

    @ModelAttribute("items")
    public List<Element> getItems(){
        return new ArrayList<>();
    }

    @GetMapping("/items")
    public String viewItems() {
        return "items";
    }

    @GetMapping("/items/new")
    public String viewItemsNew() {
        return "new";
    }

    @GetMapping("items/inc/{nom}")
    public RedirectView increment(@PathVariable String nom, @SessionAttribute List<Element> items) {
        Element element = new Element(nom);
        for (Element e : items) {
            if(e.equals(element)) {
                e.setEvaluation(e.getEvaluation() + 1);
            }
        }
        return new RedirectView("/items");
    }

    @GetMapping("items/dec/{nom}")
    public RedirectView decrement(@PathVariable String nom, @SessionAttribute List<Element> items) {
        Element element = new Element(nom);
        for (Element e : items) {
            if(e.equals(element)) {
                e.setEvaluation(e.getEvaluation() - 1);
            }
        }
        return new RedirectView("/items");
    }
    @GetMapping("items/delete/{nom}")
    public RedirectView delete(@PathVariable String nom, @SessionAttribute List<Element> items) {
        Element element = new Element(nom);
        items.removeIf(e -> e.equals(element));
        return new RedirectView("/items");
    }

    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam String nom, @SessionAttribute List<Element> items) {
        items.add(new Element(nom));
        return new RedirectView("/items");
    }
}