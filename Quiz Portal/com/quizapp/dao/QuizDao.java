package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Quiz;
import util.DBUtil;

public class QuizDao {
	public boolean createQuiz(Quiz quiz) {
        String sql = "INSERT INTO quizzes (title, category, created_by) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, quiz.getTitle());
            ps.setString(2, quiz.getCategory());
            ps.setInt(3, quiz.getCreatedBy());

            int i = ps.executeUpdate();
            return i > 0;

        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Quiz> listAll() throws SQLException {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("id"));
                q.setTitle(rs.getString("title"));
                q.setCategory(rs.getString("category"));
                q.setCreatedBy(rs.getInt("created_by"));
                list.add(q);
            }
        }
        return list;
    }

    public List<Question> getQuestionsForQuiz(int quizId) throws SQLException {
        String sql = "SELECT q.* FROM questions q JOIN quiz_questions qq ON q.id=qq.question_id WHERE qq.quiz_id=? ORDER BY qq.q_order";
        List<Question> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Question q = new Question();
                    q.setId(rs.getInt("id"));
                    q.setText(rs.getString("text"));
                    q.setOptionA(rs.getString("option_a"));
                    q.setOptionB(rs.getString("option_b"));
                    q.setOptionC(rs.getString("option_c"));
                    q.setOptionD(rs.getString("option_d"));
                    q.setCorrectOption(rs.getString("correct_option"));
                    list.add(q);
                }
            }
        }
        return list;
    }
    public Quiz getQuizById(int id) {
        Quiz quiz = null;
        String sql = "SELECT * FROM quizzes WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setTitle(rs.getString("title"));
                quiz.setCategory(rs.getString("category"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quiz;
    }

    public void updateQuiz(int id, String title, String category) {
        String sql = "UPDATE quizzes SET title=?, category=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, category);
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteQuiz(int id) {
        String sql = "DELETE FROM quizzes WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Question> getQuestionsByQuizId(int quizId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT q.* FROM questions q " +
                     "JOIN quiz_questions qq ON q.id = qq.question_id " +
                     "WHERE qq.quiz_id = ? ORDER BY qq.q_order ASC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setText(rs.getString("text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option"));
                list.add(q);
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return list;
    }


}
