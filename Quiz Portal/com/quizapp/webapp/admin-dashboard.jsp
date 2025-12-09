<%@ page import="java.util.*, model.Quiz, model.User, model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if(user == null || !"ADMIN".equals(user.getRole())){
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {background-color: #f4f6f9;}
        .sidebar {width: 250px; height: 100vh; background-color: #2d3e50; color: white; position: fixed; left: 0; top: 0; padding-top: 20px;}
        .sidebar a {color: #b8c7ce; text-decoration: none; display: block; padding: 12px 20px; font-size: 16px;}
        .sidebar a:hover {background-color: #1a252f; color: white;}
        .content-area {margin-left: 260px; padding: 20px;}
        .table thead {background-color: #2d3e50; color: white;}
        .form-container {background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);}
    </style>
</head>

<body>

<!-- Admin profile top-right -->
<div style="position:absolute; right:30px; top:20px; display:flex; align-items:center;">
    <span style="margin-right:10px; font-weight:600;">
        Welcome <%= user.getName() %>
    </span>
    <img src="assets/img/profile.png" style="width:40px; height:40px; border-radius:50%;">
</div>

<!-- Sidebar -->
<div class="sidebar">
    <h4 class="text-center">QUIZ APP</h4>
    <hr>
    <a href="AdminDashboardServlet">Dashboard</a>

    <!-- Quiz Manager with Dropdown -->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="quizManagerDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Quiz Manager
        </a>
        <ul class="dropdown-menu" aria-labelledby="quizManagerDropdown">
            <li><a class="dropdown-item" href="admin-dashboard.jsp?view=createQuiz">Create New Quiz</a></li>
            <li><a class="dropdown-item" href="AdminDashboardServlet?action=quizz-list">Quiz List</a></li>
        </ul>
    </li>

     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="questionManagerDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Question Manager
        </a>
        <ul class="dropdown-menu" aria-labelledby="questionManagerDropdown">
            <li><a class="dropdown-item" href="AdminDashboardServlet?action1=create-question">Add New Question(s)</a></li>

            <li><a class="dropdown-item" href="AdminDashboardServlet?action1=question-list">Question List</a></li>
        </ul>
    </li>
</div>


<div class="content-area">
<%
    String view = request.getParameter("view");    // for quiz
    String view1 = request.getParameter("view1");  // for questions
    String action = request.getParameter("action");    // optional for quiz list
    String action1 = request.getParameter("action1");  // optional for question list

    if("createQuiz".equals(view)) {
%>
    <!-- Create Quiz Form -->
    <div class="form-container">
        <h3 class="mb-4">Create New Quiz</h3>
        <form action="CreateQuizServlet" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Quiz Title</label>
                <input type="text" name="title" id="title" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">Category</label>
                <select name="category" id="category" class="form-select" required>
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
    <!-- Add Question Form -->
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
            <option value="<%= q.getId() %>"><%= q.getTitle()+" "+ q.getCategory() %></option>
        <%
                }
            }
        %>
    </select>
</div>
    <div class="mb-3">
        <label class="form-label">Question</label>
        <input type="text" name="text" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option A</label>
        <input type="text" name="optionA" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option B</label>
        <input type="text" name="optionB" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option C</label>
        <input type="text" name="optionC" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option D</label>
        <input type="text" name="optionD" class="form-control" required>
    </div>
    <!-- Quiz dropdown -->

    
    <div class="mb-3">
        <label class="form-label">Correct Option (A/B/C/D)</label>
        <input type="text" name="correctOption" class="form-control" maxlength="1" required>
    </div>
    <input type="submit" class="btn btn-success" value="Add Question">
</form>

    </div>
<%
    } else if("question-list".equals(action1)) {
%>
    <!-- Show Question List Table -->
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
                        if(questionList != null) {
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
        // Default: Show Quiz List
        List<Quiz> list = (List<Quiz>) request.getAttribute("quizList");
%>
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
                            <a href="ViewQuizQuestionsServlet?quizId=<%= q.getId() %>" class="text-info">
    View Questions
</a> |
                                <a href="EditQuizServlet?id=<%=q.getId()%>" class="text-primary">Edit</a> |
                                <a href="DeleteQuizServlet?id=<%=q.getId()%>" class="text-danger">Delete</a>
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


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
