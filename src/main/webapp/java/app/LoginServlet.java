package app;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import main.webapp.UsersDAO;

import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    boolean loginStatus=false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("login");
        if (name!=null) {
            if (!UsersDAO.users.add(name)){
                loginStatus=true;
            }
            else loginStatus=false;
        }
        else resp.sendRedirect("/ChatApp_war_exploded/error.jsp");
        if (!loginStatus)
        {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("name", name);
            resp.sendRedirect("/ChatApp_war_exploded/login.jsp");
        }
        else {
            resp.sendRedirect("/ChatApp_war_exploded/error.jsp");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
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
