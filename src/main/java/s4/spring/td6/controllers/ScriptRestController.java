package s4.spring.td6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s4.spring.td6.models.Script;
import s4.spring.td6.repositories.ScriptRepository;

import java.util.List;

@RestController
@RequestMapping("/rest/scripts")
public class ScriptRestController {
    @Autowired
    private ScriptRepository scriptRepository;

    @ResponseBody
    @GetMapping()
    public List<Script> get() {
        return scriptRepository.findAll();
    }

    @PostMapping()
    public Script add(@RequestBody Script script) {
        return scriptRepository.saveAndFlush(script);
    }

    @PutMapping()
    public Script update(@RequestBody Script script) {
        return scriptRepository.save(script);
    }

    @DeleteMapping()
    public void delete(@RequestBody Script script) {
        scriptRepository.delete(script);
    }
}
