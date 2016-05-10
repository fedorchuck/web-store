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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Bogdan Mart on 03.05.2016.
 */
@Controller
@RequestMapping("/sysinfo")
public class SystemInfoController {

    @RequestMapping(value = "", method = GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("sysinfo");

        final double mb = 1024*1024;
        Runtime runtime = Runtime.getRuntime();
        long usedRam = runtime.totalMemory() - runtime.freeMemory();

        Map<String,Double> mem = new TreeMap<>();
        mem.put("Used memory", usedRam/mb);
        mem.put("Total memory", runtime.totalMemory()/mb);
        mem.put("Free memory",  runtime.freeMemory()/mb);

        model.getModel().put("mem",mem);

        //model.addObject("userActions", new UserActions());

        return model;
    }
}
