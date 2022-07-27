package app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.webapp.Message;
import main.webapp.MessagesDAO;

import java.io.IOException;

@WebServlet(name="MessageServlet", urlPatterns = "/messageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text=req.getParameter("message");
        Message message= new Message(text, (String)req.getSession().getAttribute("name"));
        MessagesDAO.messages.add(message);
        req.getSession().setAttribute("message",message);
        resp.sendRedirect("/ChatApp_war_exploded/login.jsp");
    }
}
