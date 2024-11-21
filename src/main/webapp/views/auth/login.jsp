
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach Exercise - Email List</title>
    <%@include file="../common/common-css.jsp"%>
</head>
<body>

<%@include file="../common/ui/navbar.jsp"%>
<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="card shadow" style="max-width: 400px; width: 100%;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">Login</h3>
            <form method="post" action="login">
                <!-- Email input -->
                <%-- Check if the successMessage exists in the session --%>
                <%
                    String successMessage = (String) session.getAttribute("successMessage");
                    if (successMessage != null) {
                %>
                <!-- Display the success message with Bootstrap's text-success class -->
                <div class="text-success">
                    <%= successMessage %>
                </div>

                <%-- Remove the successMessage from the session after displaying it --%>
                <%
                        session.removeAttribute("successMessage");
                    }
                %>

                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input name="email" type="email" value="${email}" class="form-control" id="email" placeholder="Enter your email" required>
                </div>

                <!-- Password input -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" value="${password}" class="form-control" id="password" placeholder="Enter your password" required>
                </div>

                <p class="text-danger">${errorMessage}</p>

                <!-- Login Button -->
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>
            <div class="text-center mt-3">
                <p>Don't have an account? <a href="/register">Register here</a></p>
            </div>
        </div>
    </div>
</div>





<%@include file="../common/common-js.jsp"%>
</body>
</html>


