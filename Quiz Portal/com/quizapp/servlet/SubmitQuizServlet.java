package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

import model.Question;
import model.User;
import dao.AttemptDao;
import dao.QuestionDao;
import dao.QuizDao;

public class SubmitQuizServlet extends HttpServlet {
    private AttemptDao attemptDao = new AttemptDao();
    private QuizDao quizDao = new QuizDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Question> questions = quizDao.getQuestionsByQuizId(quizId);
        int score = 0;

        for(Question q : questions){
            String selected = request.getParameter("q" + q.getId());
            if(selected != null && selected.equalsIgnoreCase(q.getCorrectOption())) {
                score++;
            }
        }

        attemptDao.saveAttempt(quizId, user.getId(), score, questions.size());

        request.setAttribute("quizId", quizId);
        request.setAttribute("score", score);
        request.setAttribute("total", questions.size());
        request.setAttribute("questions", questions);
        request.setAttribute("userAnswers", request.getParameterMap());
        request.getRequestDispatcher("quiz-result.jsp").forward(request, response);
    }
    

}
