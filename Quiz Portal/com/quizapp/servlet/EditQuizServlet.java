package servlet;

import dao.QuizDao;
import model.Quiz;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditQuizServlet extends HttpServlet {
    private QuizDao quizDao = new QuizDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quizId = Integer.parseInt(req.getParameter("id"));
        Quiz quiz = quizDao.getQuizById(quizId);
        req.setAttribute("quiz", quiz);
        req.getRequestDispatcher("edit-quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String category = req.getParameter("category");

        quizDao.updateQuiz(id, title, category);

        resp.sendRedirect("AdminDashboardServlet");   // refresh list
    }
}
