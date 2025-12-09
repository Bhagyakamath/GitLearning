<%@ page import="java.util.*, model.Attempt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Leaderboard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Leaderboard</h2>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Score</th>
                <th>Total Questions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Attempt> attempts = (List<Attempt>) request.getAttribute("attempts");
                Map<Integer, String> userMap = (Map<Integer,String>) request.getAttribute("userMap");
                int rank = 1;
                for(Attempt a : attempts){
            %>
            <tr>
                <td><%= rank++ %></td>
                <td><%= userMap.get(a.getUserId()) %></td>
                <td><%= a.getScore() %></td>
                <td><%= a.getTotalQuestions() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
