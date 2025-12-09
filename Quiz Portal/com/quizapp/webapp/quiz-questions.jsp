<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>
<%@ page import="model.Quiz" %>

<%
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    List<Question> list = (List<Question>) request.getAttribute("questions");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Quiz Questions</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">
<div class="container mt-4">
    <div class="card p-4 shadow">
        <h3>Questions for Quiz: <strong><%=quiz.getTitle()%></strong></h3>
        <hr>

       <!--  <a href="AdminDashboardServlet?action1=add-question&quizId=<%=quiz.getId()%>"
           class="btn btn-success mb-3">Add Question to This Quiz</a>--> 

        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Question</th>
                <th>Opt A</th>
                <th>Opt B</th>
                <th>Opt C</th>
                <th>Opt D</th>
                <th>Correct</th>
            </tr>
            </thead>
            <tbody>

            <%
                int i = 1;
                if(list != null && !list.isEmpty()) {
                    for(Question q : list){
            %>

            <tr>
                <td><%=i++%></td>
                <td><%=q.getText()%></td>
                <td><%=q.getOptionA()%></td>
                <td><%=q.getOptionB()%></td>
                <td><%=q.getOptionC()%></td>
                <td><%=q.getOptionD()%></td>
                <td><%=q.getCorrectOption()%></td>
            </tr>

            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7" class="text-center text-danger">
                    No Questions Added Yet!
                </td>
            </tr>
            <% } %>

            </tbody>
        </table>

        <a href="AdminDashboardServlet?action1=quiz-list" class="btn btn-secondary">
            Back to Quiz List
        </a>
    </div>
</div>
</body>
</html>
