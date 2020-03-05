package s4.spring.td3.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestVue {
    @Autowired
    private VueJS vue;

    @GetMapping("/testvue")
    public String index(ModelMap model)
    {
        vue.addData("message", "Test de message");
        vue.addData("copy");
        vue.addData("names", new String[] {"BIM", "BAM", "BOUM"});
        vue.addMethod("doCopy", "this.copy = this.message");
        model.put("vue", this.vue);
        return "testvue/index";
    }
}
