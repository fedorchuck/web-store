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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fedorchuck.com.github.webstore.User;
import fedorchuck.com.github.webstore.data.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value="register", method=GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registerForm";
    }

    @RequestMapping(value = "authorize", method = GET)
    public String showAuthorizationForm(Model model) {
        model.addAttribute(new AuthorizeUser());
        return "authorizeForm";
    }

    @RequestMapping(value="register", method=POST)
    public String processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        userRepository.save(user);
        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value="authorize", method=POST)
    public String processAuthorization(
            @Valid AuthorizeUser authorizeUser,
            Errors errors) {
        if (errors.hasErrors()) {
            return "authorizeForm";
        }
        if (authorizeUser.check(userRepository)) return "redirect:/user/" + authorizeUser.getUser(userRepository).getUsername();
        else {
            errors.reject("username or password is incorrect");
            return "authorizeForm";
        }
    }

    @RequestMapping(value="/{username}", method=GET)
    public String showUserProfile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute(user);
        return "profile";
    }

}
