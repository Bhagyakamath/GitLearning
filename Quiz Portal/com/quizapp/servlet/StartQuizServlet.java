package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import dao.QuizDao;
import model.Question;
import model.User;

public class StartQuizServlet extends HttpServlet {
    private QuizDao quizDao = new QuizDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Question> questions = quizDao.getQuestionsByQuizId(quizId);
        request.setAttribute("questions", questions);
        request.setAttribute("quizId", quizId);
        request.getRequestDispatcher("quiz-attempt.jsp").forward(request, response);
    }
}
