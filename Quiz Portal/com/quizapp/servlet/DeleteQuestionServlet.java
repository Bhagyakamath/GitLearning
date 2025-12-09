package servlet;

import dao.QuestionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteQuestionServlet extends HttpServlet {

    private QuestionDao qdao = new QuestionDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        qdao.deleteQuestion(id);

        response.sendRedirect("AdminDashboardServlet?action1=question-list");
    }
}
