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

import com.github.fedorchuck.webstore.domainmodels.Commodity;
import com.github.fedorchuck.webstore.Config;
import com.github.fedorchuck.webstore.domainmodels.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author fedorchuck
 */
public class JdbcCommodityRepositoryTest {

    private JdbcCommodityRepository jdbc;

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
            jdbc = new JdbcCommodityRepository(new JdbcTemplate(dataSource));
            //TODO: run creating scripts.
            Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //Save
    @Ignore
    public void test00() {
        try {
            Commodity commodity = getNewCommodity();

            assertEquals(commodity.toString(), jdbc.save(commodity).toString());
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByName
    @Ignore
    public void test01() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> goodsList = jdbc.findByName(testCommodity.getName());
            boolean result = false;
            for (Commodity commodity : goodsList) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByCost
    @Ignore
    public void test02() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findByCost(testCommodity.getCost());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByManufacturer {
    @Ignore
    public void test03() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findByManufacturer(testCommodity.getManufacturer());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByCommodity_id
    @Ignore
    public void test04() {
        try {
            Commodity testCommodity = getNewCommodity();
            assertEquals(testCommodity, jdbc.findByCommodity_id(testCommodity.getCommodity_id()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByQuantity
    @Ignore
    public void test05() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findByQuantity(testCommodity.getQuantity());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findBySell_out
    @Ignore
    public void test06() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findBySell_out(testCommodity.getSell_out());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }
    @Test       //findByCategory
    @Ignore
    public void test07() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findByCategory(testCommodity.getCategory());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }
    @Test       //findByAddedBy
    @Ignore
    public void test08() {
        try {
            Commodity testCommodity = getNewCommodity();
            List<Commodity> commodities = jdbc.findByAddedBy(testCommodity.getAddedBy());
            boolean result = false;
            for (Commodity commodity : commodities) {
                if (testCommodity.equals(commodity)) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByCategory
    @Ignore
    public void test09() {
        try {
            test00();
            Commodity testCommodity = getNewCommodity();
            List<Category> categories = jdbc.findByCategory();
            boolean result = false;
            for (Category category : categories) {
                if (Objects.equals(testCommodity.getCategory(), category.getName())) {
                    result = true;
                    break;
                }
            }
            Assert.assertTrue(result);
        } catch (Throwable throwable){
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //deleteByCommodity_id
    @Ignore
    public void test10() {
        try {
            //test1();
            Commodity testCommodity = getNewCommodity();
            assertEquals(true, jdbc.deleteByCommodity_id(testCommodity.getCommodity_id()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    private Commodity getNewCommodity(){
        return new Commodity(
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),
                "testNameCommodity",
                "testManufacturerCommodity",
                789.789,
                5,
                false,
                "testCharacteristics",
                "testDescription",
                "testCategory",
                UUID.fromString("38400000-0000-11bd-b23e-10b96e4ef00d")
        );
    }
}