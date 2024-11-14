<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach Exercise - Email List</title>
    <%@include file="../common/common-css.jsp"%>
    <style>
        /* Loading spinner styles */
        .loading-spinner {
            display: none; /* Initially hidden */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 8px solid #f3f3f3;
            border-radius: 50%;
            border-top: 8px solid #3498db;
            width: 50px;
            height: 50px;
            animation: spin 1s linear infinite;
            z-index: 1000;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>
<%@include file="../common/ui/navbar.jsp"%>
<div class="mx-auto p-2 d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div style="width: 800px;">
        <p class="text-danger"><i>${errorMessage}</i></p>
        <form action="survey" method="post" class="border p-4 shadow rounded">
            <h1 class="text-primary text-center mb-4">Survey</h1>
            <p class="text-center">If you have a moment, we'd appreciate it if you would fill out this survey.</p>

            <div class="form-group">
                <label for="firstName" class="pad_top">First Name</label>
                <input type="text" name="firstName" id="firstName" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="lastName" class="pad_top">Last Name</label>
                <input type="text" name="lastName" id="lastName" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="email" class="pad_top">Email</label>
                <input type="email" name="email" id="email" class="form-control" required>
            </div>

            <h2 class="h5 mt-4">How did you hear about us?</h2>
            <div class="form-check">
                <input type="radio" name="heardFrom" id="heardFrom1" value="Search Engine" class="form-check-input">
                <label for="heardFrom1" class="form-check-label">Search engine</label>
            </div>
            <div class="form-check">
                <input type="radio" name="heardFrom" id="heardFrom2" value="Friend" class="form-check-input">
                <label for="heardFrom2" class="form-check-label">Word of mouth</label>
            </div>
            <div class="form-check">
                <input type="radio" name="heardFrom" id="heardFrom3" value="Other" class="form-check-input">
                <label for="heardFrom3" class="form-check-label">Other</label>
            </div>

            <h2 class="h5 mt-4">Would you like to receive announcements about new CDs and special offers?</h2>
            <div class="form-check mb-3">
                <input type="checkbox" name="wantsUpdates" id="wantsUpdates" class="form-check-input" checked>
                <label for="wantsUpdates" class="form-check-label">YES, I'd like that.</label>
            </div>

            <div class="form-group">
                <label for="contactVia">Please contact me by:</label>
                <select name="contactVia" id="contactVia" class="form-control">
                    <option value="Both" selected>Email or postal mail</option>
                    <option value="Email">Email only</option>
                    <option value="Postal Mail">Postal mail only</option>
                </select>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary mt-3">Submit</button>
            </div>
        </form>
    </div>
</div>

<!-- Loading spinner -->
<div class="loading-spinner" id="loadingSpinner"></div>

<%@include file="../common/common-js.jsp"%>

<script>
    // JavaScript to show loading spinner on form submit
    document.getElementById('emailForm').addEventListener('submit', function () {
        // Show the loading spinner
        document.getElementById('loadingSpinner').style.display = 'block';
    });
</script>
</body>
</html>
