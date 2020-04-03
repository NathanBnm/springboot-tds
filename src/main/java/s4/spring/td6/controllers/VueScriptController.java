package s4.spring.td6.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import s4.spring.td6.models.Script;
import s4.spring.td6.repositories.ScriptRepository;

import java.util.List;

@Controller
public class VueScriptController {
    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private VueJS vue;

    @GetMapping("/search")
    public String searchView(ModelMap model) {
        String rest = "/rest/scripts";
        List<Script> scripts = scriptRepository.findAll();
        String headers = ("[" +
                "{text: 'Nom', value: 'title'}," +
                "{text: 'Description', value: 'description'}," +
                "{text: 'Contenu', value: 'content'}," +
                "{text: 'Catégorie', value: 'category'}," +
                "{text: 'Langage', value: 'language'}" +
                "]");
        vue.addDataRaw("headers", headers);
        vue.addDataRaw("selectedHeaders", headers);
        vue.addDataRaw("checkedColumns", "['name', 'description', 'content', 'category', 'language']");
        vue.addData("scripts", scripts);
        vue.addData("search", "");
        model.put("vue", this.vue);
        return "search";
    }
}
