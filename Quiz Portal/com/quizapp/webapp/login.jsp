<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>

    <style>
        body {
            background: linear-gradient(135deg, #4c6ef5, #b197fc);
            height: 100vh;
        }
        .login-card {
            margin-top: 80px;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
        }
        .form-label {
            font-weight: 500;
        }
        .login-title {
            font-weight: 600;
            color: #343a40;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="bg-white login-card">
                <h3 class="text-center mb-4 login-title">Login</h3>

                <form method="post" action="LoginServlet">

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control form-control-lg" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control form-control-lg" required/>
                    </div>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <button class="btn btn-primary w-100 btn-lg mt-2">Login</button>

                    <p class="text-center mt-3">
                        Don’t have an account?
                        <a href="registerjsp.jsp" class="text-primary fw-semibold">Register here</a>
                    </p>
                </form>
            </div>

        </div>
    </div>
</div>

</body>
</html>
