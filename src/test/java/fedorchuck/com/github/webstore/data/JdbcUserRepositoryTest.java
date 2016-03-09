package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.Config;
import fedorchuck.com.github.webstore.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    public void test5(){
        try {
            User testUser = getNewUser();
            assertEquals(true, jdbc.deleteByUser_id(testUser.getUser_id()));
        } catch (Throwable throwable) {
            Assert.fail(throwable.getMessage());
        }
    }

    @Test       //DeleteByUsername
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