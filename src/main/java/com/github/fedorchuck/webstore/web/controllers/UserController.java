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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.github.fedorchuck.webstore.domainmodels.Commodity;
import com.github.fedorchuck.webstore.domainmodels.AuthorizeUser;
import com.github.fedorchuck.webstore.web.models.Session;
import com.github.fedorchuck.webstore.web.models.UserActions;
import com.github.fedorchuck.webstore.dao.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.fedorchuck.webstore.domainmodels.User;
import com.github.fedorchuck.webstore.dao.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@Scope("session")
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private CommodityRepository commodityRepository;
    private Session session;

    @Autowired
    public UserController(UserRepository userRepository,
                          CommodityRepository commodityRepository,
                          Session session) {
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
        this.session = session;
    }

    @RequestMapping(value="register", method=GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView model;
        if (session.getUser()!=null) {
            model = new ModelAndView("redirect:/user/" + session.getUser().getUsername());
            model.addObject("userActions", new UserActions());
        } else {
            model = new ModelAndView("registerForm");
            model.addObject("user", new User());
            model.addObject("userActions", new UserActions());
            model.addObject("radioItems", RADIO_ITEMS);
        }
        return model;
    }

    @RequestMapping(value = "authorize", method = GET)
    public ModelAndView showAuthorizationForm() {
        if (session.getUser()!=null)
            return new ModelAndView("redirect:/user/" + session.getUser().getUsername());
        else {
            ModelAndView model = new ModelAndView("authorizeForm");
            model.addObject(new AuthorizeUser());
            model.addObject("userActions", new UserActions());
            return model;
        }
    }

    @RequestMapping(value = "logout", method = GET)
    public ModelAndView showLogOut(HttpServletRequest request){
        ModelAndView model = new ModelAndView("authorizeForm");
        model.addObject(new AuthorizeUser());
        model.addObject("userActions", new UserActions());
        session.setUser(null);
        request.getSession().setAttribute("session", session);
        model.setViewName("redirect:authorize");
        return model;
    }

    @RequestMapping(value="register", method=POST)
    public ModelAndView processRegistration(
            @Valid User user,
            Errors errors,
            HttpServletRequest request) {
        ModelAndView model;
        if (errors.hasErrors()) {
             model = new ModelAndView("registerForm");
            model.addObject("userActions", new UserActions());
            model.addObject("radioItems", RADIO_ITEMS);
            return model;
        }
        userRepository.save(user);
        session.setUser(user);
        request.getSession().setAttribute("session",session);
        model = new ModelAndView("redirect:/user/" + user.getUsername());
        model.addObject("userActions", new UserActions());
        return model;
    }

    @RequestMapping(value="searchRequest", method=POST)
    public ModelAndView processFindCommodity(UserActions commodity) {
        //TODO: add validation
        ModelAndView model = new ModelAndView("catalog");
        List<Commodity> commodities = commodityRepository.findByName(commodity.getSearchRequest());
        model.addObject("lists", commodities);
        return model;
    }

    @RequestMapping(value="authorize", method=POST)//j_spring_security_check
    public ModelAndView processAuthorization(
            @Valid AuthorizeUser authorizeUser,
            Errors errors,
            HttpServletRequest request) {
        boolean valid = false;
        if (errors.hasErrors()) {
            valid = false;
        }
        if (authorizeUser.check(userRepository)) {
            valid = true;
        }
        if (valid) {
            User user = authorizeUser.getUser(userRepository);
            ModelAndView model = new ModelAndView("redirect:/user/" + user.getUsername());
            session.setUser(user);
            model.addObject("userActions", new UserActions());
            request.getSession().setAttribute("session", session);
            return model;
        } else {
            ModelAndView model = new ModelAndView("authorizeForm");
            model.addObject("userActions", new UserActions());
            return model;
        }
    }

    @RequestMapping(value="/{username}", method=GET)
    public ModelAndView showUserProfile(@PathVariable String username,
                                        HttpServletRequest request) {
        //TODO: fix it. just authorize user can see user-profile
//        if (!Objects.equals(session.getUser().getUsername(), username))
//            return new ModelAndView("authorizeForm");
//        else {
            User user = userRepository.findByUsername(username);
            ModelAndView model = new ModelAndView("profile");
            model.addObject(user);
            model.addObject("userActions", new UserActions());

            session.setUser(user);
            request.getSession().setAttribute("session", session);
            return model;
//        }
    }

    final static Map<String, Integer> RADIO_ITEMS = Collections.unmodifiableMap(
            new LinkedHashMap<String, Integer>() {
                {
                    put("Admin", 0);
                    put("Customer", 1);
                    put("Seller", 2);
                }
            });

}
