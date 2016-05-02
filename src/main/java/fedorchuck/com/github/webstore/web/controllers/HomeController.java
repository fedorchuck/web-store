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

import fedorchuck.com.github.webstore.domainmodels.Category;
import fedorchuck.com.github.webstore.domainmodels.Commodity;
import fedorchuck.com.github.webstore.web.models.UserActions;
import fedorchuck.com.github.webstore.dao.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@Scope("session")
@RequestMapping("/")
public class HomeController {

    private CommodityRepository commodityRepository;

    @Autowired
    public HomeController(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @RequestMapping(method = GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("index");
        model.addObject(new Commodity());
        model.addObject("userActions", new UserActions());

        List<Category> categories = commodityRepository.findByCategory();
        model.addObject("categories", categories);

        return model;
    }

    @RequestMapping(value="searchRequest", method = POST)
    public ModelAndView processSearchRequest(UserActions searchRequest) {
        //TODO: add validation
        List<Commodity> response = commodityRepository.findByName(searchRequest.getSearchRequest());
        ModelAndView model = new ModelAndView("catalog");
        model.addObject("lists", response);
        return model;
    }
}
