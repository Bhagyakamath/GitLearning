<%@ page import="java.util.*, model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Quiz Result</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Quiz Result</h2>
    <p>Your Score: <b><%= request.getAttribute("score") %> / <%= request.getAttribute("total") %></b></p>

    <h4>Answers</h4>
    <%
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        Map<String, String[]> userAnswers = (Map<String,String[]>) request.getAttribute("userAnswers");
        int i=1;
        for(Question q : questions){
            String userAns = "";
            if(userAnswers.get("q"+q.getId()) != null){
                userAns = userAnswers.get("q"+q.getId())[0];
            }
    %>
        <div class="mb-3">
            <p><b>Q<%=i++%>: <%=q.getText()%></b></p>
            <p>Your Answer: <%= userAns %></p>
            <p>Correct Answer: <%= q.getCorrectOption() %></p>
        </div>
    <%
        }
    %>
    
    <div class="mt-4">
    <form action="LeaderboardServlet" method="get">
        <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>">
        <button type="submit" class="btn btn-primary">View Leaderboard</button>
    </form>
</div>
    
</div>
</body>
</html>
