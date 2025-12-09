<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
</head>
<body>

<div class="container" style="max-width:600px;margin-top:50px;">
    <h3 class="mb-4">Login</h3>

    <form method="post" action="LoginServlet">
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required/>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <button class="btn btn-primary w-100">Login</button>

        <p class="mt-3">Don’t have an account? <a href="registerjsp.jsp">Register here</a></p>
    </form>
</div>

</body>
</html>
