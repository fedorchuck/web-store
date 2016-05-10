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

import com.github.fedorchuck.webstore.domainmodels.User;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;


/**
 * @author fedorchuck
 */
public class UserTest {

    @Test       //does equals correct work?
    public void testEquals1() {
        User userTest1 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(true, userTest1.equals(userTest2));
    }

    @Test       //does equals correct work?
    public void testEquals2() {
        User userTest1 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                "test",
                "test",
                "test",
                "test",
                "test",
                2,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(false, userTest1.equals(userTest2));
    }

    @Test       //ignoring id field
    public void testEquals3() {
        User userTest1 = new User(
                100500,
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                100501,
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(true, userTest1.equals(userTest2));
    }

    @Test       //does equals correct work?
    public void testHashCode1() {
        User userTest1 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(true, userTest1.hashCode()==userTest2.hashCode());
    }

    @Test       //does equals correct work?
    public void testHashCode2() {
        User userTest1 = new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                "test",
                "test",
                "test",
                "test",
                "test",
                2,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(false, userTest1.hashCode()==userTest2.hashCode());
    }

    @Test       //ignoring id field
    public void testHashCode3() {
        User userTest1 = new User(
                100500,
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        User userTest2 = new User(
                100501,
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(true, userTest1.hashCode()==userTest2.hashCode());
    }
}