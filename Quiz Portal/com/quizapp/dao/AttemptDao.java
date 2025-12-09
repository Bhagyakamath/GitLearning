package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attempt;
import util.DBUtil;

public class AttemptDao {
	

    public List<Attempt> getLeaderboardForQuiz(int quizId) throws SQLException {
        String sql = "SELECT * FROM attempts WHERE quiz_id=? ORDER BY score DESC, created_at ASC";
        List<Attempt> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Attempt a = new Attempt();
                    a.setId(rs.getInt("id"));
                    a.setQuizId(rs.getInt("quiz_id"));
                    a.setUserId(rs.getInt("user_id"));
                    a.setScore(rs.getInt("score"));
                    a.setTotalQuestions(rs.getInt("total_questions"));
                    a.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(a);
                }
            }
        }
        return list;
    }
    public boolean saveAttempt(int quizId, int userId, int score, int totalQuestions) {
        String sql = "INSERT INTO attempts (quiz_id, user_id, score, total_questions) VALUES (?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ps.setInt(2, userId);
            ps.setInt(3, score);
            ps.setInt(4, totalQuestions);

            return ps.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); return false; }
    }
}
