package fedorchuck.com.github.webstore;

import fedorchuck.com.github.webstore.domainmodels.Commodity;
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