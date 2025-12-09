package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import dao.UserDao;
import model.User;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole("USER");

        try {
            UserDao dao = new UserDao();

            boolean ok = dao.register(u);
            if (ok) {
                req.setAttribute("msg", "Registration successful! You can now log in.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Registration failed. Email may already exist.");
                req.getRequestDispatcher("registerjsp.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
