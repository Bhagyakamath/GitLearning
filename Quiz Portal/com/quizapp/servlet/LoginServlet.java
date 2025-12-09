package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            UserDao dao = new UserDao();
            User u = dao.findByEmailAndPassword(email, password);

            if (u == null) {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("user", u);

            if ("ADMIN".equals(u.getRole())) {
                resp.sendRedirect("AdminDashboardServlet");
            } else {
                resp.sendRedirect("QuizListServlet");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
