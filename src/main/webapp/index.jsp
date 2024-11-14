<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach Exercise - Email List</title>
    <%@include file="./views/common/common-css.jsp"%>
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
<%@include file="./views/common/ui/navbar.jsp"%>
<div class="mx-auto p-2 d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div style="width: 800px;">
        <div class="card">
            <div class="card-header">
                Hello there,
            </div>
            <div class="card-body">
                <h5 class="card-title"></h5>
                <p class="card-text">It's my course work series from Website development course at HCMUTE. My teacher is a wonderful women, Mai Anh Tho, she helped me and my friends learn not only about web development but also about design thinking pattern.
                <br/>
                    This is what I've gained and learned during the course.
                </p>
                <p>
                    <b><span class="text-danger">Func facts</span>: <i>Music, cart, admin page are linked to each other!</i></b>
                </p>
            </div>
        </div>
    </div>
</div>

<!-- Loading spinner -->
<div class="loading-spinner" id="loadingSpinner"></div>

<%@include file="./views/common/common-js.jsp"%>

<script>
    // JavaScript to show loading spinner on form submit
    document.getElementById('emailForm').addEventListener('submit', function () {
        // Show the loading spinner
        document.getElementById('loadingSpinner').style.display = 'block';
    });
</script>
</body>
</html>
