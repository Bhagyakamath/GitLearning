<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Register</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
</head>
<body>

<div class="container" style="max-width:600px;margin-top:50px;">
    <h3 class="mb-4">Register</h3>

    <form method="post" action="RegisterServlet">
        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required/>
        </div>

        <button class="btn btn-success w-100">Register</button>

        <c:if test="${not empty msg}">
            <div class="alert alert-success mt-3">${msg}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </form>
</div>

</body>
</html>

