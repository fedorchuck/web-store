package fedorchuck.com.github.webstore.web.controllers;

import fedorchuck.com.github.webstore.web.models.UserActions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Bogdan Mart on 03.05.2016.
 */
@Controller
@RequestMapping("/sysinfo")
public class SystemInfoController {

    @RequestMapping(value = "", method = GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("sysinfo");

        final double mb = 1024*1024;
        Runtime runtime = Runtime.getRuntime();
        long usedRam = runtime.totalMemory() - runtime.freeMemory();

        Map<String,Double> mem = new TreeMap<>();
        mem.put("Used memory", usedRam/mb);
        mem.put("Total memory", runtime.totalMemory()/mb);
        mem.put("Free memory",  runtime.freeMemory()/mb);

        model.getModel().put("mem",mem);

        //model.addObject("userActions", new UserActions());

        return model;
    }
}
