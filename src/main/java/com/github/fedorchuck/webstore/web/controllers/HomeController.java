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

import com.github.fedorchuck.webstore.domainmodels.Commodity;
import com.github.fedorchuck.webstore.web.models.Session;
import com.github.fedorchuck.webstore.domainmodels.Category;
import com.github.fedorchuck.webstore.web.models.UserActions;
import com.github.fedorchuck.webstore.dao.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@Scope("session")
@RequestMapping("/")
public class HomeController {

    private CommodityRepository commodityRepository;
    private Session session;

    @Autowired
    public HomeController(CommodityRepository commodityRepository,
                          Session session) {
        this.commodityRepository = commodityRepository;
        this.session = session;
    }

    @RequestMapping(method = GET)
    public ModelAndView home(HttpServletRequest request) {
        request.getSession().setAttribute("session", session);

        ModelAndView model = new ModelAndView("index");
        model.addObject(new Commodity());
        model.addObject("userActions", new UserActions());

        List<Category> categories = commodityRepository.findByCategory();
        model.addObject("categories", categories);

        return model;
    }

    @RequestMapping(value="/login-error",method = GET)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // Error page
    @RequestMapping(value="/error",method = GET)
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
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
