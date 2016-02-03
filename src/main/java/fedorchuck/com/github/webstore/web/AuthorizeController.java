package fedorchuck.com.github.webstore.web;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Created by v on 02/02/16.
 */
@Controller
@WebServlet("/authorize")
public class AuthorizeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("cancel") != null)
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

        if (request.getParameter("authorize") != null) throw new IOException("yes! email:" +
                request.getParameter("email") + "; \npassword: " + request.getParameter("password"));

    }
}
