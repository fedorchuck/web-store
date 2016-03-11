package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.Category;
import fedorchuck.com.github.webstore.Commodity;
import fedorchuck.com.github.webstore.Config;
import org.junit.Assert;
import org.junit.Before;
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
    public void test00() {
        try {
            Commodity commodity = getNewCommodity();

            assertEquals(commodity.toString(), jdbc.save(commodity).toString());
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByName
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
    public void test04() {
        try {
            Commodity testCommodity = getNewCommodity();
            assertEquals(testCommodity, jdbc.findByCommodity_id(testCommodity.getCommodity_id()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //findByQuantity
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