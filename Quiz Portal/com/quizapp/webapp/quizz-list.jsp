<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Quiz" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quiz List</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
        }

        .container {
            width: 90%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0px 0px 8px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background: #f0f0f0;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        .btn {
            background: #ff5b5b;
            padding: 8px 15px;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }

        .btn:hover {
            background: #e04949;
        }
    </style>

</head>
<body>

<div class="container">
    <h2>Quiz List</h2>

    <table>
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
            List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
            int i = 1;
            for (Quiz q : quizzes) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= q.getTitle() %></td>
            <td><%= q.getCategory() %></td>

            <!-- Start button goes to: /take-quiz?quizId=ID -->
            <td>
                <a class="btn" href="StartQuizServlet?quizId=<%= q.getId() %>">Start</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
