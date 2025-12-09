package servlet;

import dao.QuizDao;
import model.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        QuizDao dao = new QuizDao();
        try {
            List<Quiz> quizzes = dao.listAll();
            req.setAttribute("quizzes", quizzes);
            req.getRequestDispatcher("quizz-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
