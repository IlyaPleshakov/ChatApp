package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.Set;

@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static Set<Integer> setChatRooms;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String password = req.getParameter("password");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver loading failed");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                "postgres", "admin")) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("login"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*String path="/loginServlet";
        synchronized (this) {
            if (setChatRooms.add(1)) {
                req.setAttribute("numberChatRoom",1);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
            } else resp.sendError(404);
        }
        //ChatRoomsDAO.ChatRooms.put(mail,password);*/

    }
}
