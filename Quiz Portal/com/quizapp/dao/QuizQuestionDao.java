package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import util.DBUtil;

public class QuizQuestionDao {
	public void mapQuestionToQuiz(int quizId, int questionId){

	    String sql = "INSERT INTO quiz_questions (quiz_id, question_id) VALUES (?, ?)";

	    try(Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, quizId);
	        ps.setInt(2, questionId);
	        ps.executeUpdate();
	        
	        


	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public List<Question> getQuestionsByQuiz(int quizId) {
	    List<Question> list = new ArrayList<>();

	    String sql = "SELECT q.* FROM questions q " +
	                 "INNER JOIN quiz_questions qq ON q.id = qq.question_id " +
	                 "WHERE qq.quiz_id = ? ORDER BY qq.q_order ASC";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

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

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


}
