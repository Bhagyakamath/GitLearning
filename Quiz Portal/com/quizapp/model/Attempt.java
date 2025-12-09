package model;

import java.sql.Timestamp;

public class Attempt {
	 private int id;
	    private int quizId;
	    private int userId;
	    private int score;
	    private int totalQuestions;
	    private Timestamp createdAt;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getQuizId() {
			return quizId;
		}
		public void setQuizId(int quizId) {
			this.quizId = quizId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public int getTotalQuestions() {
			return totalQuestions;
		}
		public void setTotalQuestions(int totalQuestions) {
			this.totalQuestions = totalQuestions;
		}
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
	    
}
