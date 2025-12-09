package servlet;

import dao.QuizDao;
import dao.QuizQuestionDao;
import model.Quiz;
import model.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewQuizQuestionsServlet extends HttpServlet {

    private QuizQuestionDao qqDao = new QuizQuestionDao();
    private QuizDao quizDao = new QuizDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(req.getParameter("quizId"));

        Quiz quiz = quizDao.getQuizById(quizId);
        List<Question> questions = qqDao.getQuestionsByQuiz(quizId);

        req.setAttribute("quiz", quiz);
        req.setAttribute("questions", questions);

        req.getRequestDispatcher("quiz-questions.jsp").forward(req, resp);
    }
}
