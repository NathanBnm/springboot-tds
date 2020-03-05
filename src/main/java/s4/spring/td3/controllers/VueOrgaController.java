package s4.spring.td3.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import s4.spring.td3.models.Organization;
import s4.spring.td3.repositories.OrgaRepository;

import java.util.List;

@Controller
public class VueOrgaController {
    @Autowired
    private OrgaRepository repo;

    @Autowired
    private VueJS vue;

    @GetMapping("/vuejs/orgas/index")
    public String index(ModelMap model) {
        List<Organization> orgas = repo.findAll();
        String rest = "/rest/orgas/";
        vue.addDataRaw("headers", "[" +
            "{text: 'Id', value: 'id'}," +
            "{text: 'Nom', value: 'name'}," +
            "{text: 'Domaine', value: 'domain'}," +
            "{text: 'Alias', value: 'aliases'}," +
            "{text: 'Actions', value: 'actions'}" +
            "]");
        vue.addData("orgas", orgas);
        vue.addData("dialog", false);
        vue.addData("editedIndex", -1);
        vue.addDataRaw("editedItem", "{}");
        vue.addComputed("formTitle", "return this.editedIndex === -1 ? 'Nouvelle organisation' : 'Modifier une organisation';");
        vue.addWatcher("dialog", "val || this.close();");
        vue.addMethod("save", "var self = this; let item = this.editedItem; if (this.editedIndex > -1) { " + Http.put(rest, (Object) "item", JsArray.replace("self.orgas", "self.editedIndex", "item") + "self.close();") + " } else { " + Http.post(rest, "item", JsArray.add("self.orgas", "item") + "self.close();") + " };");
        vue.addMethod("close", "this.dialog = false; setTimeout(() => { this.editedItem = {}; this.editedIndex = -1; }, 300);");        vue.addMethod("editItem", "");
        vue.addMethod("editItem", "this.editedIndex = this.orgas.indexOf(item); this.editedItem = Object.assign({}, item); this.dialog = true;", "item");
        vue.addMethod("deleteItem", "var self = this;" + Http.delete(rest, (Object) "{ data: item }", JsArray.remove("self.orgas", "item")), "item");
        model.put("vue", this.vue);
        return "vuejs/index";
    }
}
