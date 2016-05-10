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

package com.github.fedorchuck.webstore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fedorchuck
 */
public final class Config {
    public static final String DRIVERCLASSNAME  =    String.valueOf(value("DRIVERCLASSNAME"));
    public static final String URL              =    String.valueOf(value("URL"));
    public static final String USERNAME         =    String.valueOf(value("USERNAME"));
    public static final String PASSWORD         =    String.valueOf(value("PASSWORD"));

    private static Object value(String key){
        try(InputStream res =
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties")){

            Properties property = new Properties();
            property.load(res);

            switch(key){
                case "DRIVERCLASSNAME":
                    return property.get("jdbc.driverClassName");
                case "URL":
                    return property.get("jdbc.url");
                case "USERNAME":
                    return property.get("jdbc.username");
                case "PASSWORD":
                    return property.get("jdbc.password");
                default: return null;
            }

        } catch (NullPointerException |
                IOException ex) {
            throw new IllegalArgumentException("Problem with resource.properties.", ex);
        }
    }
}
