package fedorchuck.com.github.webstore.web;

import fedorchuck.com.github.webstore.RegistrationErrors;
import fedorchuck.com.github.webstore.User;
import fedorchuck.com.github.webstore.data.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by v on 03/02/16.
 */
@Controller
@WebServlet("/registration")
@Deprecated
public class RegistrationController extends HttpServlet {

    /*
    <%--
    <label class="text-center">Rules: </label>
    <label class="text-center text-left"> Rules: </label><br/>
    <label class="text-center text-left"> firstName have to between 2 and 30 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> lastName have to between 2 and 30 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> email not missed/empty.</label><br/>
    <label class="text-center text-left"> username have to between 5 and 16 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> password have to between 5 and 25 characters and not missed/empty.</label><br/>
    <form action="${pageContext.request.contextPath}/registration" method="POST">
        <label path="firstName" cssErrorClass="has-error">First Name:</label><input type="text" name="firstName" /><br/>
        <label path="lastName" cssErrorClass="has-error">Last Name:</label><input type="text" name="lastName" /><br/>
        <label path="email" cssErrorClass="has-error">Email: </label><input type="email" name="email" /><br/>
        <label path="username" cssErrorClass="has-error">Username: </label><input type="text" name="username" /><br/>
        <label path="password" cssErrorClass="has-error">Password: </label><input type="password" name="password" /><br/>
        <input type="submit" name="register" value="register" />
        <input type="submit" name="cancel" value="cancel" />
    </form>
    --%>
    */

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("cancel") != null) {
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        }

        User user = makeUser(request);

        try {
            if (validation(user)){
                request.getRequestDispatcher("/WEB-INF/views/ok.jsp").forward(request, response);
                userRepository.save(user);
                request.getRequestDispatcher( "redirect:/user/" + user.getUsername()).forward(request, response);

            }

        } catch (RegistrationErrors registrationErrors) {
            request.getRequestDispatcher("/WEB-INF/views/registerFormError.jsp").forward(request, response);
        }

    }

    private User makeUser(HttpServletRequest request){
        //TODO: rewrite this.
        return new User(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                request.getParameter("username"),
                request.getParameter("password"));
    }

    private boolean validation(User user) throws RegistrationErrors{
        if (user.getUsername() == null) throw new RegistrationErrors();
        else {
            if (user.getUsername().length() > 16) throw new RegistrationErrors();
            if (user.getUsername().length() < 5) throw new RegistrationErrors();
        }
        if (user.getPassword() == null) throw new RegistrationErrors();
        else {
            if (user.getPassword().length() > 25) throw new RegistrationErrors();
            if (user.getPassword().length() < 5) throw new RegistrationErrors();
        }
        if (user.getFirstName() == null) throw new RegistrationErrors();
        else {
            if (user.getFirstName().length() > 30) throw new RegistrationErrors();
            if (user.getFirstName().length() < 2) throw new RegistrationErrors();
        }
        if (user.getLastName() == null) throw new RegistrationErrors();
        else {
            if (user.getLastName().length() > 30) throw new RegistrationErrors();
            if (user.getLastName().length() < 2) throw new RegistrationErrors();
        }
        if (user.getEmail() == null) throw new RegistrationErrors(); //I know.
        else {
            if (user.getEmail().length() > 16) throw new RegistrationErrors();
            if (user.getEmail().length() < 5) throw new RegistrationErrors();
        }

        return true;
    }


}
