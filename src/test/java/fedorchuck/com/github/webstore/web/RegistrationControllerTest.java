package fedorchuck.com.github.webstore.web;

import fedorchuck.com.github.webstore.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by v on 03/02/16.
 */
public class RegistrationControllerTest {

    @Test
    public void testMakeUser(){
        User user = new User("mynameis",
                                "itismysecondname",
                                "myemail@mail.com",
                                "myusername",
                                "mypassworDis");

        assertEquals("User{id=-369094062, username='mynameis', password='itismysecondname', " +
                "firstName='myemail@mail.com', lastName='myusername', email='mypassworDis'}", user.toString());
    }







}