//package servlet;
//
//import java.io.IOException;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;
//import dao.QuizDao;
//import model.Question;
//import model.User;
//
//public class StartQuizServlet extends HttpServlet {
//    private QuizDao quizDao = new QuizDao();
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int quizId = Integer.parseInt(request.getParameter("quizId"));
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        List<Question> questions = quizDao.getQuestionsByQuizId(quizId);
//        request.setAttribute("questions", questions);
//        request.setAttribute("quizId", quizId);
//        request.getRequestDispatcher("quiz-attempt.jsp").forward(request, response);
//    }
//}
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

        // Get full question list
        List<Question> questions = quizDao.getQuestionsByQuizId(quizId);

        // Pagination
        int currentPage = 1;
        if(request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int questionsPerPage = 1; // one question per page
        int totalPages = (int) Math.ceil((double) questions.size() / questionsPerPage);
        int start = (currentPage - 1) * questionsPerPage;
        int end = Math.min(start + questionsPerPage, questions.size());
        List<Question> currentQuestion = questions.subList(start, end);

        // Set attributes for JSP
        request.setAttribute("questions", currentQuestion);
        request.setAttribute("quizId", quizId);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("quiz-attempt.jsp").forward(request, response);
    }
}
