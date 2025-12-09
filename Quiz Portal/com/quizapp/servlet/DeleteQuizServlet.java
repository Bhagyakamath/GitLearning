package servlet;

import dao.QuizDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteQuizServlet extends HttpServlet {

    private QuizDao quizDao = new QuizDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        quizDao.deleteQuiz(id);

        resp.sendRedirect("AdminDashboardServlet");
    }
}
