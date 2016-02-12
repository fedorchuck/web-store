package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;
import fedorchuck.com.github.webstore.config.DataConfig;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcOperations;

import javax.sql.DataSource;
import javax.validation.constraints.AssertTrue;

/**
 * Created by v on 11/02/16.
 */
public class JdbcUserRepositoryTest {

    @Test
    @Before
    public void testZero() {
        try {
            User user = new User();
            DataConfig dataConfig = new DataConfig();

            //TODO: learn how works spring resource loader and write tests

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.register(DataConfig.class);

            dataConfig.setResourceLoader(context);
            DataSource dataSource = dataConfig.dataSource();
            JdbcOperations jdbcOperations = dataConfig.jdbcTemplate(dataSource);
            JdbcUserRepository jdbc = new JdbcUserRepository(jdbcOperations);
            Assert.assertTrue(true);
        } catch (Throwable throwable) {
            Assert.assertTrue(throwable.getMessage(), false);
        }
    }


    @Test
    public void testFindByUsername() throws Exception {
        Assert.assertTrue(false);
       /* User user = new User();
        DataSource dataSource = new DataConfig().dataSource();
        JdbcOperations jdbcOperations = new DataConfig().jdbcTemplate(dataSource);
        JdbcUserRepository jdbc = new JdbcUserRepository(jdbcOperations);

        user = jdbc.findByUsername("myusername");*/

        /*select id, username, password, firstname, lastname, email from users
        where username='myusername';*/
    }
}