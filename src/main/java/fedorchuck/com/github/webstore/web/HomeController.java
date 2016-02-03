package fedorchuck.com.github.webstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by v on 28/01/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = GET)
    public String home(Model model) {
        return "index";
    }

}
