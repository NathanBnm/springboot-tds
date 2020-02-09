package s4.spring.td2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.entities.Organization;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("orgas")
public class OrgaController {

    @ModelAttribute("orgas")
    public List<Organization> getItems(){
        return new ArrayList<>();
    }
    
    @GetMapping("/orgas/index")
    public String viewIndex() {
        return "index";
    }

    @GetMapping("/orgas/new")
    public String viewNew() {
        return "new";
    }

    @GetMapping("/orgas/edit/{id}")
    public String viewEdit(@PathVariable int id, @SessionAttribute List<Organization> orgas, ModelMap model) {
        Organization orga = orgas.get(id);
        model.put("id", orga.getId());
        model.put("name", orga.getName());
        model.put("domain", orga.getDomain());
        model.put("aliases", orga.getAliases());
        return "edit";
    }

    @GetMapping("/orgas/delete/{id}")
    public String viewDelete(@PathVariable int id, @SessionAttribute List<Organization> orgas, ModelMap model) {
        Organization orga = orgas.get(id);
        model.put("id", orga.getId());
        model.put("domain", orga.getDomain());
        return "index";
    }

    @GetMapping("/orgas/display/{id}")
    public String viewDisplay(@PathVariable int id, @SessionAttribute List<Organization> orgas, ModelMap model) {
        Organization orga = orgas.get(id);
        model.put("id", orga.getId());
        model.put("name", orga.getName());
        model.put("domain", orga.getDomain());
        model.put("aliases", orga.getAliases());
        return "display";
    }

    @PostMapping("/orgas/add-new")
    public RedirectView addNew(@RequestParam String name, @RequestParam String domain, @RequestParam String aliases, @SessionAttribute List<Organization> orgas) {
        orgas.add(new Organization(name, domain, aliases));
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/orgas/edit")
    public RedirectView edit(@RequestParam int id, @RequestParam String name, @RequestParam String domain, @RequestParam String aliases, @SessionAttribute List<Organization> orgas) {
        Organization orga = orgas.get(id);
        orga.setName(name);
        orga.setDomain(domain);
        orga.setAliases(aliases);
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/orgas/delete")
    public RedirectView delete(@RequestParam int id, @SessionAttribute List<Organization> orgas) {
        orgas.remove(id);
        return new RedirectView("/orgas/index");
    }
}
