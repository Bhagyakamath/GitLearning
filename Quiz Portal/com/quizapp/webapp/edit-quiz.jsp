<%@ page import="model.Quiz" %>
<%
    Quiz quiz = (Quiz)request.getAttribute("quiz");
%>

<h2>Edit Quiz</h2>

<form action="EditQuizServlet" method="post">
    <input type="hidden" name="id" value="<%= quiz.getId() %>">

    Title:
    <input type="text" name="title" value="<%= quiz.getTitle() %>" class="form-control"><br>

    Category:
    <input type="text" name="category" value="<%= quiz.getCategory() %>" class="form-control"><br>

    <button type="submit" class="btn btn-primary">Update Quiz</button>
</form>
