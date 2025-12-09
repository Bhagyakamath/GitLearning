package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dao.QuizDao;
import dao.UserDao;
import model.Question;
import model.Quiz;

/**
 * Servlet implementation class AdminDashboardServlet
 */
public class AdminDashboardServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession s = req.getSession(false);
//        if (s == null || s.getAttribute("admin")==null) {
//            resp.sendRedirect(req.getContextPath() + "/admin/login");
//            return;
//        }
//        try {
//            int totalQuestions = new QuestionDao().listAll().size();
//            int totalQuiz = new QuizDao().listAll().size();
//            int totalUsers = 0;
//            try { totalUsers = new UserDao().listAll().size(); } catch (Exception ex) { /* optional */ }
//
//            req.setAttribute("totalQuestions", totalQuestions);
//            req.setAttribute("totalQuiz", totalQuiz);
//            req.setAttribute("totalUsers", totalUsers);
//            req.getRequestDispatcher("admin-dashboard.jsp").forward(req, resp);
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
		QuizDao dao = new QuizDao();

		try {
            List<Quiz> quizzes = dao.listAll();
            QuestionDao qdao = new QuestionDao();
            List<Question> questionList = qdao.listAll();
            req.setAttribute("quizList", quizzes);
            req.setAttribute("view1", "createQuestion");
            req.setAttribute("questionList", questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("admin-dashboard.jsp").forward(req, resp);
        
    }
        
        
    }

