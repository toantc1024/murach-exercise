<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Register - Email List</title>
    <%@ include file="../common/common-css.jsp" %>
</head>
<body>

<%@ include file="../common/ui/navbar.jsp" %>
<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="card shadow" style="max-width: 400px; width: 100%;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">Register</h3>
            <form method="post" action="register">
                <!-- Email input -->
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" class="form-control <%= request.getAttribute("emailError") != null ? "is-invalid" : "" %>"
                           id="email" name="email" placeholder="Enter your email"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
                    <div class="invalid-feedback">
                        <%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %>
                    </div>
                </div>

                <!-- Password input -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <!-- Last Name input -->
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input name="lastName" type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter your last name"
                           value="<%= request.getAttribute("lastName") != null ? request.getAttribute("lastName") : "" %>" required>
                </div>

                <p class="text-danger">${errorMessage}</p>

                <!-- Register Button -->
                <button type="submit" class="btn btn-primary w-100">Register</button>
            </form>
            <div class="text-center mt-3">
                <p>Already have an account? <a href="/login">Login here</a></p>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/common-js.jsp" %>

<script>
    // JavaScript to hide the error when the user starts typing
    document.getElementById("email").addEventListener("input", function () {
        var errorMessage = document.querySelector(".invalid-feedback");
        if (errorMessage) {
            errorMessage.style.display = 'none';
        }
    });
</script>

</body>
</html>
