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

import com.github.fedorchuck.webstore.domainmodels.Commodity;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author fedorchuck
 */
public class CommodityTest {

    @Test       //does equals correct work?
    public void testEquals1() {
        Commodity commodityTest1 = new Commodity(
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

        Commodity commodityTest2 = new Commodity(
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

        assertEquals(true, commodityTest1.equals(commodityTest1));
    }

    @Test       //does equals correct work?
    public void testEquals2() {
        Commodity commodityTest1 = new Commodity(
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

        Commodity commodityTest2 = new Commodity(
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),
                "test",
                "test",
                789.789,
                5,
                false,
                "test",
                "test",
                "test",
                UUID.fromString("38400000-0000-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(false, commodityTest1.equals(commodityTest2));
    }

    @Test       //ignoring id field
    public void testEquals3() {
        Commodity commodityTest1 = new Commodity(
                123,
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

        Commodity commodityTest2 = new Commodity(
                32,
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

        assertEquals(true, commodityTest1.equals(commodityTest2));
    }

    @Test       //does equals correct work?
    public void testHashCode1() {
        Commodity commodityTest1 = new Commodity(
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

        Commodity commodityTest2 = new Commodity(
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

        assertEquals(true, commodityTest1.hashCode()==commodityTest2.hashCode());
    }

    @Test       //does equals correct work?
    public void testHashCode2() {
        Commodity commodityTest1 = new Commodity(
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

        Commodity commodityTest2 = new Commodity(
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),
                "test",
                "test",
                789.789,
                50,
                true,
                "test",
                "test",
                "test",
                UUID.fromString("38400000-0000-11bd-b23e-10b96e4ef00d")
        );

        assertEquals(false, commodityTest1.hashCode()==commodityTest2.hashCode());
    }

    @Test       //ignoring id field
    public void testHashCode3() {
        Commodity commodityTest1 = new Commodity(
                123,
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

        Commodity commodityTest2 = new Commodity(
                32,
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

        assertEquals(true, commodityTest1.hashCode()==commodityTest2.hashCode());
    }
}