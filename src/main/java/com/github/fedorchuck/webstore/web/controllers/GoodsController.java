/*
 * The GNU General Public Licence
 *
 * Copyright (c) 2016 by Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>
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
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */

package com.github.fedorchuck.webstore.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.github.fedorchuck.webstore.domainmodels.Commodity;
import com.github.fedorchuck.webstore.domainmodels.Category;
import com.github.fedorchuck.webstore.web.models.UserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.fedorchuck.webstore.dao.CommodityRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller

@Scope("session")
@RequestMapping("/goods")
public class GoodsController {

    private CommodityRepository commodityRepository;

    @Autowired
    public GoodsController(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @RequestMapping(value = "add", method = GET)
    public ModelAndView showGoodsForm(ModelAndView model){
        model.addObject(new Commodity());
        model.addObject("userActions", new UserActions());
        model.addObject("radioItems", RADIO_ITEMS);
        model.setViewName("goodsForm");
        return model;
    }

    @RequestMapping(value = "add", method = POST)
    public ModelAndView processAddCommodities(
            @Valid Commodity commodity,
            Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView model = new ModelAndView("goodsForm");
            model.addObject("userActions", new UserActions());
            model.addObject("radioItems", RADIO_ITEMS);
            return model;
        }
        commodityRepository.save(commodity);
        ModelAndView model = new ModelAndView("redirect:/goods/" + commodity.getName());
        model.addObject(new Commodity());
        model.addObject("userActions", new UserActions());

        return model;
    }

    @RequestMapping(value="/{name}", method=GET)
    public ModelAndView showCommodity(@PathVariable String name) {
        List<Commodity> commodities = commodityRepository.findByName(name);
        ModelAndView model2 = new ModelAndView("catalog");
        model2.addObject("lists", commodities);
        model2.addObject("userActions", new UserActions());
        return model2;
    }

    @RequestMapping(value="forCategory/{name}", method=GET)
    public ModelAndView showForCategory(Category category) {
        List<Commodity> commodities = commodityRepository.findByCategory(category.getName());
        List<Category> categories = commodityRepository.findByCategory();
        ModelAndView model = new ModelAndView("catalog");
        model.addObject("lists", commodities);
        model.addObject("userActions", new UserActions());
        model.addObject("categories", categories);

        return model;
    }

    @RequestMapping(value = "all", method = GET)
    public ModelAndView showAll() {
        ModelAndView model = new ModelAndView("catalog");
        List<Commodity> commodities = commodityRepository.all();
        List<Category> categories = commodityRepository.findByCategory();
        model.addObject("lists", commodities);
        model.addObject("userActions", new UserActions());
        model.addObject("categories", categories);

        return model;
    }

    @RequestMapping(value="searchRequest", method=POST)
    public ModelAndView processFindCommodity(UserActions commodity, ModelAndView model) {
        //TODO: add validation
        List<Commodity> commodities = commodityRepository.findByName(commodity.getSearchRequest());
        model.addObject("lists", commodities);
        model.setViewName("catalog");
        return model;
    }

    private final static Map<String, Boolean> RADIO_ITEMS = Collections.unmodifiableMap(
            new LinkedHashMap<String, Boolean>() {
                {
                    put("Yes", true);
                    put("No", false);
                }
            });
}
