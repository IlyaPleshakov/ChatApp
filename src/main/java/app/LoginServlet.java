package app;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name="LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {


    boolean loginStatus=false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        //resp.sendRedirect("/ChatApp_war_exploded/error.jsp");

        if (!loginStatus)
        {
            //HttpSession httpSession = req.getSession();
            resp.sendRedirect("./login.jsp");
        }
        else {
            resp.sendRedirect("./error.jsp");
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
