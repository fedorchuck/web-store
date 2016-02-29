package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.assertEquals;

/**
 * Created by v on 26/02/16.
 */
public class JdbcUserRepositoryTest {

    private JdbcUserRepository jdbc;

    @Before
    public void setUp() {
        try {
            //TODO: should be rewritten.
        String driverClassName = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/webstore";
        String username = "postgres";
        String password = "lucky strike";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbc = new JdbcUserRepository(new JdbcTemplate(dataSource));
            //TODO: run creating scripts.
        Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.assertTrue(throwable.getMessage(), false);
        }
    }

    @Test
    public void testSave() {
        try {
            User user = new User("mynameis",
                    "itismysecondname",
                    "myemail@mail.com",
                    "myusername",
                    "mypassworDis");

            assertEquals("User{id=0, username='mynameis', password='itismysecondname', " +
                    "firstName='myemail@mail.com', lastName='myusername', email='mypassworDis'}", jdbc.save(user).toString());

            //Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.assertTrue(throwable.getMessage(), false);
        }
    }

    @Test
    public void testFindByUsername()  {
        try {
            User user = jdbc.findByUsername("mynameis");
            assertEquals("mynameis", user.getUsername());
        } catch (Throwable throwable) {
            Assert.assertTrue(throwable.getMessage(), false);
        }
    }

    @Test
    public void ztestDeleteBtUsername(){
        try {
            assertEquals(true, jdbc.deleteByUsername("mynameis"));
        } catch (Throwable throwable) {
            Assert.assertTrue(throwable.getMessage(), false);
        }
    }

    @After
    public void tearDown() {

    }
}