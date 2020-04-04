package s4.spring.td6.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import s4.spring.td6.repositories.ScriptRepository;

@Controller
public class VueScriptController {
    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private VueJS vue;

    @GetMapping("/search")
    public String searchView(ModelMap model) {
        String rest = "/rest/scripts";

        vue.addDataRaw("headers", "[" +
                "{text: 'Nom', value: 'title'}," +
                "{text: 'Description', value: 'description'}," +
                "{text: 'Contenu', value: 'content'}," +
                "{text: 'Catégorie', value: 'category.name'}," +
                "{text: 'Langage', value: 'language.name'}" +
                "]");
        vue.addDataRaw("selectedHeaders", "['title', 'description', 'content', 'category.name', 'language.name']");

        vue.addData("search", "");
        vue.addData("scripts", scriptRepository.findAll());

        vue.addComputed("computedHeaders", "return this.headers.filter((header => this.selectedHeaders.includes(header.value)));");
        vue.addComputed("colspan", "return this.headers.length;");

        model.put("vue", this.vue);
        return "search";
    }
}
