<%@ page import="java.util.*, model.Quiz, model.User, model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include header + sidebar only ONCE -->
<%@ include file="header.jsp" %>
<%@ include file="sidebar1.jsp" %>



<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {background-color: #f4f6f9;}

        /* Because sidebar is now below navbar */
        .content-area {
            margin-left: 260px;
            padding: 20px;
            margin-top: 70px; /* Ensures content is below header */
        }

        .table thead {background-color: #2d3e50; color: white;}
        .form-container {
            background: #fff; padding: 20px; border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>

<body>

<div class="content-area">
<%
    String view = request.getParameter("view");
    String view1 = request.getParameter("view1");
    String action = request.getParameter("action");
    String action1 = request.getParameter("action1");

    if("createQuiz".equals(view)) {
%>

    <!-- CREATE QUIZ -->
    <div class="form-container">
        <h3 class="mb-4">Create New Quiz</h3>
        <form action="CreateQuizServlet" method="post">
            <div class="mb-3">
                <label class="form-label">Quiz Title</label>
                <input type="text" name="title" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Category</label>
                <select name="category" class="form-select" required>
                    <option value="">--Select Category--</option>
                    <option value="Math">Math</option>
                    <option value="Science">Science</option>
                    <option value="History">History</option>
                </select>
            </div>

            <input type="hidden" name="createdBy" value="<%= user.getId() %>">

            <input type="submit" class="btn btn-primary" value="Create Quiz">
        </form>
    </div>

<%
    } else if("create-question".equals(action1)) {
%>

    <!-- ADD QUESTION -->
    <div class="form-container">
        <h3 class="mb-4">Add New Question</h3>

        <form action="CreateQuestionServlet" method="post">

            <div class="mb-3">
                <label class="form-label">Select Quiz</label>
                <select name="quizId" class="form-select" required>
                    <option value="">--Select Quiz--</option>
                    <%
                        List<Quiz> quizList = (List<Quiz>) request.getAttribute("quizList");
                        if(quizList != null){
                            for(Quiz q : quizList){
                    %>
                        <option value="<%= q.getId() %>">
                            <%= q.getTitle() +" ("+ q.getCategory() +")" %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="mb-3"><label>Question</label>
                <input type="text" name="text" class="form-control" required></div>

            <div class="mb-3"><label>Option A</label>
                <input type="text" name="optionA" class="form-control" required></div>

            <div class="mb-3"><label>Option B</label>
                <input type="text" name="optionB" class="form-control" required></div>

            <div class="mb-3"><label>Option C</label>
                <input type="text" name="optionC" class="form-control" required></div>

            <div class="mb-3"><label>Option D</label>
                <input type="text" name="optionD" class="form-control" required></div>

            <div class="mb-3">
                <label>Correct Option (A/B/C/D)</label>
                <input type="text" name="correctOption" maxlength="1" class="form-control" required>
            </div>

            <input type="submit" class="btn btn-success" value="Add Question">
        </form>
    </div>

<%
    } else if("question-list".equals(action1)) {
%>

    <!-- QUESTION LIST TABLE -->
    <div class="card">
        <div class="card-header">Question List</div>
        <div class="card-body">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Sr No.</th>
                        <th>Question</th>
                        <th>Option-1</th>
                        <th>Option-2</th>
                        <th>Option-3</th>
                        <th>Option-4</th>
                        <th>Correct Option</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<Question> questionList = (List<Question>) request.getAttribute("questionList");
                    int i = 1;
                    if(questionList != null){
                        for(Question q : questionList){
                %>
                    <tr>
                        <td><%= i++ %></td>
                        <td><%= q.getText() %></td>
                        <td><%= q.getOptionA() %></td>
                        <td><%= q.getOptionB() %></td>
                        <td><%= q.getOptionC() %></td>
                        <td><%= q.getOptionD() %></td>
                        <td><%= q.getCorrectOption() %></td>
                        <td>
                            <a href="EditQuestionServlet?id=<%= q.getId() %>" class="text-primary">Edit</a> |
                            <a href="DeleteQuestionServlet?id=<%= q.getId() %>" class="text-danger">Delete</a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

<%
    } else {
        List<Quiz> list = (List<Quiz>) request.getAttribute("quizList");
%>

    <!-- DEFAULT: QUIZ LIST -->
    <div class="card">
        <div class="card-header">Quiz List</div>
        <div class="card-body">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Sr No.</th>
                    <th>Quiz Title</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <%
                    int i = 1;
                    if(list != null){
                        for(Quiz q : list){
                %>
                    <tr>
                        <td><%= i++ %></td>
                        <td><%= q.getTitle() %></td>
                        <td><%= q.getCategory() %></td>
                        <td>
                            <a href="ViewQuizQuestionsServlet?quizId=<%= q.getId() %>" class="text-info">View Questions</a> |
                            <a href="EditQuizServlet?id=<%= q.getId() %>" class="text-primary">Edit</a> |
                            <a href="DeleteQuizServlet?id=<%= q.getId() %>" class="text-danger">Delete</a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>

            </table>
        </div>
    </div>

<%
    }
%>
</div>

</body>
</html>
