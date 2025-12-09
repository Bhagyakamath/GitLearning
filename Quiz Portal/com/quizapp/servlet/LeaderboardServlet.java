package servlet;

import dao.AttemptDao;
import dao.UserDao;
import model.Attempt;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class LeaderboardServlet extends HttpServlet {

    private AttemptDao attemptDao = new AttemptDao();
    private UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(req.getParameter("quizId"));
        List<Attempt> attempts = null;
		try {
			attempts = attemptDao.getLeaderboardForQuiz(quizId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Map<Integer, String> userMap = new HashMap<>();
        for(Attempt a : attempts){
            User u = null;
			try {
				u = userDao.findById(a.getUserId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            userMap.put(u.getId(), u.getName());
        }

        req.setAttribute("attempts", attempts);
        req.setAttribute("userMap", userMap);
        req.getRequestDispatcher("leaderboard.jsp").forward(req, resp);
    }
}
