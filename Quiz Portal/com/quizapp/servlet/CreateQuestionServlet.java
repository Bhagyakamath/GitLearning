package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import dao.QuestionDao;
import dao.QuizQuestionDao;
import model.Question;

public class CreateQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuestionDao questionDao = new QuestionDao();
    private QuizQuestionDao qqDao=new QuizQuestionDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        String a = request.getParameter("optionA");
        String b = request.getParameter("optionB");
        String c = request.getParameter("optionC");
        String d = request.getParameter("optionD");
        String correct = request.getParameter("correctOption");
        int quizId = Integer.parseInt(request.getParameter("quizId"));

        Question q = new Question();
        q.setText(text);
        q.setOptionA(a);
        q.setOptionB(b);
        q.setOptionC(c);
        q.setOptionD(d);
        q.setCorrectOption(correct);

        
        int questionId = questionDao.createQuestion(q);

        // link question to quiz
        QuizQuestionDao qqDao = new QuizQuestionDao();
        
        System.out.println("QUIZ ID = " + quizId + ", QUESTION ID = " + questionId);

        qqDao.mapQuestionToQuiz(quizId, questionId);

       
        response.sendRedirect("AdminDashboardServlet?action1=question-list");
        
    }
}
