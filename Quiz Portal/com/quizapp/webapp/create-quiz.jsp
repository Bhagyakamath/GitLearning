<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if(user == null || !"ADMIN".equals(user.getRole())){
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Quiz</title>
<style>
    body {font-family: Arial, sans-serif; background-color: #f4f4f4; margin:0; padding:0;}
    .container {width: 50%; margin: 50px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);}
    h2 {text-align: center; color: #333;}
    form {display: flex; flex-direction: column;}
    label {margin: 10px 0 5px;}
    input[type=text], select {padding: 8px; border-radius: 4px; border: 1px solid #ccc;}
    input[type=submit] {margin-top: 20px; padding: 10px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;}
    input[type=submit]:hover {background: #45a049;}
    .admin-info {text-align: right; margin-bottom: 20px; color: #555;}
</style>
</head>
<body>
<div class="container">
    <div class="admin-info">
        Logged in as: <strong><%=user.getName()%></strong>
    </div>
    <h2>Create New Quiz</h2>
    <form action="CreateQuizServlet" method="post">
        <label for="title">Quiz Title</label>
        <input type="text" name="title" id="title" required>

        <label for="category">Category</label>
        <select name="category" id="category" required>
            <option value="">--Select Category--</option>
            <option value="Math">Math</option>
            <option value="Science">Science</option>
            <option value="History">History</option>
        </select>

        <!-- Hidden field to send admin ID -->
        <input type="hidden" name="createdBy" value="<%=user.getId()%>">

        <input type="submit" value="Create Quiz">
    </form>
</div>
</body>
</html>
