<%@ page import="model.Quiz" %>

<%
    Quiz quiz = (Quiz) request.getAttribute("quiz");
%>

<%@ include file="header.jsp" %>
<%@ include file="sidebar1.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Quiz</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        .main-content {
            margin-left: 260px; /* Adjust to your sidebar width */
            padding: 20px;
        }

        .edit-card {
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>

<body class="bg-light">

<div class="main-content">

    <div class="card p-4 shadow edit-card">
        <h3 class="mb-3 text-center">Edit Quiz</h3>
        <hr>

        <form action="EditQuizServlet" method="post">

            <input type="hidden" name="id" value="<%= quiz.getId() %>">

            <div class="mb-3">
                <label class="form-label">Title</label>
                <input type="text" name="title" value="<%= quiz.getTitle() %>" 
                       class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Category</label>
                <input type="text" name="category" value="<%= quiz.getCategory() %>" 
                       class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Update Quiz</button>
        </form>
    </div>

</div>

</body>
</html>
