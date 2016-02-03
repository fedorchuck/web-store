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
public class RegistrationController extends HttpServlet {

    /*private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

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
                //userRepository.save(user);
                //request.getRequestDispatcher( "redirect:/user/" + user.getUsername()).forward(request, response);

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
