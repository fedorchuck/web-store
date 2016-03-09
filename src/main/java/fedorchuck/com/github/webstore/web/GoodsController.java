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
