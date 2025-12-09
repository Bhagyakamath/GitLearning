<%@ page import="java.util.*, model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Quiz</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Quiz</h2>
    <form action="SubmitQuizServlet" method="post">
        <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>">
        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            int i=1;
            for(Question q : questions) {
        %>
            <div class="mb-3">
                <p><b>Q<%=i++%>: <%=q.getText()%></b></p>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%=q.getId()%>" value="A" required>
                    <label class="form-check-label"><%=q.getOptionA()%></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%=q.getId()%>" value="B">
                    <label class="form-check-label"><%=q.getOptionB()%></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%=q.getId()%>" value="C">
                    <label class="form-check-label"><%=q.getOptionC()%></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%=q.getId()%>" value="D">
                    <label class="form-check-label"><%=q.getOptionD()%></label>
                </div>
            </div>
        <%
            }
        %>
        <input type="submit" class="btn btn-success" value="Submit">
    </form>
</div>
</body>
</html>
