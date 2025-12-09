package servlet;

import dao.QuestionDao;
import model.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditQuestionServlet extends HttpServlet {

    private QuestionDao qdao = new QuestionDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Question question = qdao.getById(id);

        request.setAttribute("question", question);
        request.getRequestDispatcher("edit-question.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Question q = new Question();
        q.setId(id);
        q.setText(request.getParameter("text"));
        q.setOptionA(request.getParameter("optionA"));
        q.setOptionB(request.getParameter("optionB"));
        q.setOptionC(request.getParameter("optionC"));
        q.setOptionD(request.getParameter("optionD"));
        q.setCorrectOption(request.getParameter("correctOption"));

        boolean updated = qdao.updateQuestion(q);

        if(updated){
            response.sendRedirect("AdminDashboardServlet?action1=question-list");
        } else {
            response.getWriter().write("Update Failed!");
        }
    }
}
