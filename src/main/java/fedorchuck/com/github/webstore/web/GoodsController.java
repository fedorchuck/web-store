package fedorchuck.com.github.webstore.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fedorchuck.com.github.webstore.Commodity;
import fedorchuck.com.github.webstore.data.CommodityRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by v on 12/02/16.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private CommodityRepository commodityRepository;

    @Autowired
    public GoodsController(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @RequestMapping(value = "add", method = GET)
    public String showGoodsForm(Model model){
        model.addAttribute(new Commodity());
        return "goodsForm";
    }

    @RequestMapping(value = "add", method = POST)
    public String processAddCommodities(
            @Valid Commodity commodity,
            Errors errors) {
        if (errors.hasErrors()) return "goodsForm";
        commodityRepository.save(commodity);
        return "redirect:/goods/" + commodity.getName();
    }

    @RequestMapping(value="/{name}", method=GET)
    public ModelAndView showCommodity(@PathVariable String name, Model model) {
        List<Commodity> commodities = commodityRepository.findByName(name);
        //model.addAttribute(commodities);
        ModelAndView model2 = new ModelAndView("catalog");
        model2.addObject("lists", commodities);
        //return "catalog";
        return model2;
    }

    @RequestMapping(value = "all", method = GET)
    public String showAll(Model model) {
        List<Commodity> commodities = commodityRepository.all();
        model.addAttribute("lists", commodities);
        return "catalog";
    }
}
