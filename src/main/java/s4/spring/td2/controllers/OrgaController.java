package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.repositories.OrgaRepository;

import java.util.List;


@Controller
public class OrgaController {
    @Autowired
    private OrgaRepository repo;
     
    @GetMapping("/orgas/index")
    public String viewIndex(ModelMap model) {
        model.put("orgas", repo.findAll());
        return "index";
    }

    @GetMapping("/orgas/new")
    public String viewNew() {
        return "new";
    }

    @GetMapping("/orgas/edit/{id}")
    public String viewEdit(@PathVariable("id") String id, ModelMap model) {
        Organization orga = repo.findById(Integer.parseInt(id));
        model.put("id", orga.getId());
        model.put("name", orga.getName());
        model.put("domain", orga.getDomain());
        model.put("aliases", orga.getAliases());
        return "edit";
    }

    @GetMapping("/orgas/delete/{id}")
    public String viewDelete(@PathVariable("id") String id, ModelMap model) {
        Organization orga = repo.findById(Integer.parseInt(id));
        model.put("orgas", repo.findAll());
        model.put("delete", true);
        model.put("domain", orga.getDomain());
        return "index";
    }
    
    @GetMapping("/orgas/display/{id}")
    public String viewDisplay(@PathVariable("id") String id, ModelMap model) {
        Organization orga = repo.findById(Integer.parseInt(id));
        model.put("id", orga.getId());
        model.put("name", orga.getName());
        model.put("domain", orga.getDomain());
        model.put("aliases", orga.getAliases());
        return "display";
    }

    @PostMapping("/orgas/add-new")
    public RedirectView addNew(@RequestParam String name, @RequestParam String domain, @RequestParam String aliases) {
        repo.save(new Organization(name, domain, aliases));
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/orgas/edit")
    public RedirectView edit(@RequestParam("id") String id, @RequestParam String name, @RequestParam String domain, @RequestParam String aliases) {
        Organization orga = repo.findById(Integer.parseInt(id));
        orga.setName(name);
        orga.setDomain(domain);
        orga.setAliases(aliases);
        repo.save(orga);
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/orgas/delete")
    public RedirectView delete(@RequestParam("id") String id) {
        Organization orga = repo.findById(Integer.parseInt(id));
        repo.delete(orga);
        return new RedirectView("/orgas/index");
    }
    
    @PostMapping("/orgas/search")
    public String search(@RequestParam String search, ModelMap model) {
        model.put("orgas", repo.findBySearch(search));
        return "index";
    }
}
