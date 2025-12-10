<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>

    <style>
        body {
            background: linear-gradient(135deg, #4c6ef5, #b197fc);
            height: 100vh;
        }
        .register-card {
            margin-top: 80px;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
            background-color: #ffffff;
        }
        .form-label {
            font-weight: 500;
        }
        .register-title {
            font-weight: 600;
            color: #343a40;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="register-card">
                <h3 class="text-center mb-4 register-title">Register</h3>

                <form method="post" action="RegisterServlet">

                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" name="name" class="form-control form-control-lg" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control form-control-lg" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control form-control-lg" required/>
                    </div>

                    <c:if test="${not empty msg}">
                        <div class="alert alert-success">${msg}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <button class="btn btn-success w-100 btn-lg mt-2">Register</button>

                    <p class="text-center mt-3">
                        Already have an account?
                        <a href="login.jsp" class="text-primary fw-semibold">Login here</a>
                    </p>
                </form>
            </div>

        </div>
    </div>
</div>

</body>
</html>
