package fedorchuck.com.github.webstore;

import fedorchuck.com.github.webstore.domainmodels.User;
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