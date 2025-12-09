package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dao.QuizDao;
import model.Quiz;
import model.User;

public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private QuestionDao questionDAO = new QuestionDao();
    private QuizDao quizDAO = new QuizDao();
    
    public CreateQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setAttribute("questions", questionDAO.listAll());
            request.getRequestDispatcher("create-quiz.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//            String title = request.getParameter("title");
//            String category = request.getParameter("category");
//            String[] qids = request.getParameterValues("questionIds");
//            List<Integer> ids = new ArrayList<>();
//            if (qids != null) {
//                for (String id : qids) ids.add(Integer.parseInt(id));
//            }
//            HttpSession s = request.getSession();
//            User admin = (User) s.getAttribute("admin");
//            Quiz q = new Quiz();
//            q.setTitle(title);
//            q.setCategory(category);
//            q.setCreatedBy(admin.getId());
//            quizDAO.createQuiz(q, ids);
//            response.sendRedirect(request.getContextPath()+"/admin/quiz/list");
//        } catch (Exception e) { throw new ServletException(e); }
		 String title = request.getParameter("title");
	        String category = request.getParameter("category");
	        int createdBy = Integer.parseInt(request.getParameter("createdBy"));

	        Quiz quiz = new Quiz();
	        quiz.setTitle(title);
	        quiz.setCategory(category);
	        quiz.setCreatedBy(createdBy);

	        boolean result = quizDAO.createQuiz(quiz);
	        if(result) {
	            response.sendRedirect("AdminDashboardServlet");
	        } else {
	            response.getWriter().println("Error creating quiz!");
	        }
	}

}
