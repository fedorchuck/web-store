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

package com.github.fedorchuck.webstore.dao.impl.postgresql;

import com.github.fedorchuck.webstore.Config;
import com.github.fedorchuck.webstore.domainmodels.User;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author fedorchuck
 */
public class JdbcUserRepositoryTest {

    private JdbcUserRepository jdbc;

    @Before
    @Ignore
    public void setUp() {
        try {
            //TODO: should be rewritten.

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(Config.DRIVERCLASSNAME);
            dataSource.setUrl(Config.URL);
            dataSource.setUsername(Config.USERNAME);
            dataSource.setPassword(Config.PASSWORD);
            jdbc = new JdbcUserRepository(new JdbcTemplate(dataSource));
                //TODO: run creating scripts.
            Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //Save
    @Ignore
    public void test1() {
        try {
            User user = getNewUser();

            assertEquals(user.toString(), jdbc.save(user).toString());

            //Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //FindByUsername
    @Ignore
    public void test2()  {
        try {
            User testUser = getNewUser();
            User user = jdbc.findByUsername(testUser.getUsername());
            assertEquals(testUser.getUsername(), user.getUsername());
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByUser_id
    @Ignore
    public void test3() {
        try {
            User testUser = getNewUser();
            User user = jdbc.findByUser_id(testUser.getUser_id());
            assertEquals(testUser, user);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByUserRole
    @Ignore
    public void test4() {
        try {
            User testUser = getNewUser();
            List<User> userList = jdbc.findByUserRole(testUser.getRole());
            boolean result = false;
            for (User user : userList) {
                if (testUser.equals(user)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //deleteByUser_id
    @Ignore
    public void test5(){
        try {
            User testUser = getNewUser();
            assertEquals(true, jdbc.deleteByUser_id(testUser.getUser_id()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //DeleteByUsername
    @Ignore
    public void test6(){
        try {
            test1();

            User testUser = getNewUser();
            assertEquals(true, jdbc.deleteByUsername(testUser.getUsername()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @After
    public void tearDown() {

    }

    private User getNewUser(){
        return new User(
                "testUserName",
                "testPassword",
                "testUserFirstName",
                "testUserLastName",
                "testUser@email",
                0,
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
        );
    }
}