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
        <div class="mb-3">
            <h1 class="text-primary">Join my email list</h1>
            <p>To join my email list, enter your name and email address below.</p>
        </div>
        <p class="text-danger"><i>${errorMessage}</i></p>
        <form id="emailForm" action="email-list" method="post">
            <div class="mb-3">
                <input type="hidden" name="action" value="add">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label class="form-label">First name</label>
                <input name="firstName" type="text" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Last name</label>
                <input name="lastName" type="text" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Join now</button>
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
