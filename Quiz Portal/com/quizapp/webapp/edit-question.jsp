<%@ page import="model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    Question q = (Question) request.getAttribute("question");
    if(q == null){
        out.println("<h3>Invalid Question ID!</h3>");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Question</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="card p-4 shadow">
        <h3>Edit Question</h3>

        <form action="EditQuestionServlet" method="post">
            <input type="hidden" name="id" value="<%=q.getId()%>">

            <div class="mb-3">
                <label class="form-label">Question</label>
                <input type="text" name="text" class="form-control" value="<%=q.getText()%>" required>
            </div>

            <div class="mb-3">
                <label>Option A</label>
                <input type="text" name="optionA" class="form-control" value="<%=q.getOptionA()%>" required>
            </div>

            <div class="mb-3">
                <label>Option B</label>
                <input type="text" name="optionB" class="form-control" value="<%=q.getOptionB()%>" required>
            </div>

            <div class="mb-3">
                <label>Option C</label>
                <input type="text" name="optionC" class="form-control" value="<%=q.getOptionC()%>" required>
            </div>

            <div class="mb-3">
                <label>Option D</label>
                <input type="text" name="optionD" class="form-control" value="<%=q.getOptionD()%>" required>
            </div>

            <div class="mb-3">
                <label>Correct Option (A/B/C/D)</label>
                <input type="text" name="correctOption" maxlength="1" class="form-control"
                       value="<%=q.getCorrectOption()%>" required>
            </div>

            <input type="submit" class="btn btn-primary" value="Update Question">
            <a href="AdminDashboardServlet?action1=question-list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</div>

</body>
</html>
