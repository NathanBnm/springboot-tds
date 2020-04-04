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

        vue.addData("hideTitle", false);
        vue.addData("hideDescription", false);
        vue.addData("hideContent", false);
        vue.addData("hideCategory", false);
        vue.addData("hideLanguage", false);

        vue.addData("search", "");
        vue.addDataRaw("checkedColumns", "['name', 'description', 'content', 'category', 'language']");

        vue.addDataRaw("headers", "[" +
                "{text: 'Nom', value: 'title'}," +
                "{text: 'Description', value: 'description'}," +
                "{text: 'Contenu', value: 'content'}," +
                "{text: 'Catégorie', value: 'category'}," +
                "{text: 'Langage', value: 'language'}" +
                "]");

        vue.addData("scripts", scripts);

        vue.addComputed("computedHeaders", "return this.hideTitle ? this.headers.filter(header => header.value !== 'title') : this.headers;");

        vue.addComputed("colspan", "return this.headers.length;");

        vue.addMethod("hideTitleToggle", "this.hideTitle = !this.hideTitle");
        vue.addMethod("hideDescriptionToggle", "this.hideDescription = !this.hideDescription");
        vue.addMethod("hideContentToggle", "this.hideContent = !this.hideContent");
        vue.addMethod("hideCategoryToggle", "this.hideCategory = !this.hideCategory");
        vue.addMethod("hideLanguageToggle", "this.hideLanguage = !this.hideLanguage");

        model.put("vue", this.vue);
        return "search";
    }
}
