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

import javax.validation.Valid;

import fedorchuck.com.github.webstore.web.models.AuthorizeUser;
import fedorchuck.com.github.webstore.domainmodels.Commodity;
import fedorchuck.com.github.webstore.web.models.UserActions;
import fedorchuck.com.github.webstore.dao.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fedorchuck.com.github.webstore.domainmodels.User;
import fedorchuck.com.github.webstore.dao.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private CommodityRepository commodityRepository;

    @Autowired
    public UserController(UserRepository userRepository, CommodityRepository commodityRepository) {
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
    }

    @RequestMapping(value="register", method=GET)
    public ModelAndView showRegistrationForm(ModelAndView model) {
        model.addObject(new User());
        model.addObject("userActions", new UserActions());
        model.addObject("radioItems", RADIO_ITEMS);

        model.setViewName("registerForm");
        return model;
    }

    @RequestMapping(value = "authorize", method = GET)
    public ModelAndView showAuthorizationForm(ModelAndView model) {
        model.addObject(new AuthorizeUser());
        model.addObject("userActions", new UserActions());
        model.setViewName("authorizeForm");
        return model;
    }

    @RequestMapping(value="register", method=POST)
    public ModelAndView processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView model = new ModelAndView("registerForm");
            model.addObject("userActions", new UserActions());
            model.addObject("radioItems", RADIO_ITEMS);
            return model;
        }
        userRepository.save(user);
        ModelAndView model = new ModelAndView("redirect:/user/" + user.getUsername());
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

    @RequestMapping(value="authorize", method=POST)
    public ModelAndView processAuthorization(
            @Valid AuthorizeUser authorizeUser,
            Errors errors) {
        boolean valid = false;
        if (errors.hasErrors()) {
            valid = false;
        }
        if (authorizeUser.check(userRepository)) {
            valid = true;
        }
        if (valid) {
            ModelAndView model = new ModelAndView("redirect:/user/" + authorizeUser.getUser(userRepository).getUsername());
            model.addObject("userActions", new UserActions());
            return model;
        } else {
            ModelAndView model = new ModelAndView("authorizeForm");
            model.addObject("userActions", new UserActions());
            return model;
        }
    }

    @RequestMapping(value="/{username}", method=GET)
    public ModelAndView showUserProfile(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        ModelAndView model = new ModelAndView("profile");
        model.addObject(user);
        model.addObject("userActions", new UserActions());
        return model;
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
