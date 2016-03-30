/*
 *  The GNU General Public Licence
 *
 *  Copyright (c) 2016 by Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */

package fedorchuck.com.github.webstore.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import fedorchuck.com.github.webstore.domainmodels.Category;
import fedorchuck.com.github.webstore.web.models.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fedorchuck.com.github.webstore.domainmodels.Commodity;
import fedorchuck.com.github.webstore.dao.CommodityRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("radioItems", RADIO_ITEMS);
        return "goodsForm";
    }

    @RequestMapping(value = "add", method = POST)
    public /*String*/ ModelAndView processAddCommodities(
            @Valid Commodity commodity,
            Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView model = new ModelAndView("goodsForm");
            model.addObject("searchRequest", new SearchRequest());
            model.addObject("radioItems", RADIO_ITEMS);
            return model;//"goodsForm";
        }
        commodityRepository.save(commodity);
        ModelAndView model = new ModelAndView("redirect:/goods/" + commodity.getName());
        model.addObject(new Commodity());
        model.addObject("searchRequest", new SearchRequest());

        //return "redirect:/goods/" + commodity.getName();
        return model;
    }

    @RequestMapping(value="/{name}", method=GET)
    public ModelAndView showCommodity(@PathVariable String name, Model model) {
        List<Commodity> commodities = commodityRepository.findByName(name);
        //model.addAttribute(commodities);
        ModelAndView model2 = new ModelAndView("catalog");
        model2.addObject("lists", commodities);
        model2.addObject("searchRequest", new SearchRequest());
        //return "catalog";
        return model2;
    }

    @RequestMapping(value="forCategory/{name}", method=GET)//,params = {""}
    public ModelAndView showForCategory(Category category) {
        List<Commodity> commodities = commodityRepository.findByCategory(category.getName());
        ModelAndView model2 = new ModelAndView("catalog");
        model2.addObject("lists", commodities);
        model2.addObject("searchRequest", new SearchRequest());
        return model2;
    }

    @RequestMapping(value = "all", method = GET)
    public String showAll(Model model) {
        List<Commodity> commodities = commodityRepository.all();
        model.addAttribute("lists", commodities);
        model.addAttribute("searchRequest", new SearchRequest());
        return "catalog";
    }

    @RequestMapping(value="searchRequest", method=POST)
    public String processFindCommodity(SearchRequest commodity, Model model) {
        //TODO: add validation
        List<Commodity> commodities = commodityRepository.findByName(commodity.getName());
        model.addAttribute("lists", commodities);
        return "catalog";
    }

    final static Map<String, Boolean> RADIO_ITEMS = Collections.unmodifiableMap(
            new LinkedHashMap<String, Boolean>() {
                {
                    put("Yes", true);
                    put("No", false);
                }
            });
}
