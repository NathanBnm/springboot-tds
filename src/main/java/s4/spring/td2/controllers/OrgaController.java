package s4.spring.td2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrgaController {
    
    @GetMapping("/orgas/index")
    
    public String viewIndex() {
        return "index";
    }
    
}
