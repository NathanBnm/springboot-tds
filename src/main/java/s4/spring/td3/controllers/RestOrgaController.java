package s4.spring.td3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s4.spring.td3.entities.Organization;
import s4.spring.td3.repositories.OrgaRepository;

import java.util.List;

@RestController
@RequestMapping("/rest/orgas/")
public class RestOrgaController {
    @Autowired
    private OrgaRepository repo;

    @ResponseBody
    @GetMapping()
    public List<Organization> get() {
        return repo.findAll();
    }

    @PostMapping()
    public Organization add(@RequestBody Organization orga) {
        return repo.saveAndFlush(orga);
    }

    @PutMapping()
    public Organization update(@RequestBody Organization orga) {
        return repo.save(orga);
    }

    @DeleteMapping()
    public void delete(@RequestBody Organization orga) {
        repo.delete(orga);
    }
}

