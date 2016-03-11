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

import javax.validation.Valid;

import fedorchuck.com.github.webstore.AuthorizeUser;
import fedorchuck.com.github.webstore.Commodity;
import fedorchuck.com.github.webstore.SearchRequest;
import fedorchuck.com.github.webstore.data.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fedorchuck.com.github.webstore.User;
import fedorchuck.com.github.webstore.data.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("searchRequest", new SearchRequest());

        return "registerForm";
    }

    @RequestMapping(value = "authorize", method = GET)
    public String showAuthorizationForm(Model model) {
        model.addAttribute(new AuthorizeUser());
        model.addAttribute("searchRequest", new SearchRequest());
        return "authorizeForm";
    }

    @RequestMapping(value="register", method=POST)
    public /*String*/ModelAndView processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView model = new ModelAndView("registerForm");
            model.addObject("searchRequest", new SearchRequest());
            return model;//return "registerForm";
        }
        userRepository.save(user);
        ModelAndView model = new ModelAndView("redirect:/user/" + user.getUsername());
        model.addObject("searchRequest", new SearchRequest());
        return model;//return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value="searchRequest", method=POST)
    public String processFindCommodity(SearchRequest commodity, Model model) {
        //TODO: add validation
        List<Commodity> commodities = commodityRepository.findByName(commodity.getName());
        model.addAttribute("lists", commodities);
        return "catalog";
    }

    @RequestMapping(value="authorize", method=POST)
    public /*String*/ModelAndView processAuthorization(
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
            //return "redirect:/user/" + authorizeUser.getUser(userRepository).getUsername();
            ModelAndView model = new ModelAndView("redirect:/user/" + authorizeUser.getUser(userRepository).getUsername());
            model.addObject("searchRequest", new SearchRequest());
            return model;//return "authorizeForm";
        } else {
            //errors.reject("username or password is incorrect");
            ModelAndView model = new ModelAndView("authorizeForm");
            model.addObject("searchRequest", new SearchRequest());
            return model;//return "authorizeForm";
        }
    }

    @RequestMapping(value="/{username}", method=GET)
    public String showUserProfile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute(user);
        model.addAttribute("searchRequest", new SearchRequest());
        return "profile";
    }

}
