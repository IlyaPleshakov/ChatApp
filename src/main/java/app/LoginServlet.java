package app;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = "/servlet")
public class LoginServlet extends HttpServlet {


    boolean loginStatus = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.sendRedirect("./jsp/index.jsp");
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
