<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach Exercise - Email List</title>
    <%@include file="../common/common-css.jsp"%>
</head>
<body>

<%@include file="../common/ui/navbar.jsp"%>
<div class="mx-auto p-2 d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div style="width: 800px;">

        <h1 class="text-success">Thanks for joining my email list</h1>
        <p>Here is the information that you entered:</p>

        <label>Email:</label>
        <span>${user.email}</span><br>
        <label>First Name:</label>
        <span>${user.firstName}</span><br>
        <label>Last Name:</label>
        <span>${user.lastName}</span><br>

        <p>I've sent you a confirmation email.</p>


        <p>To enter another email address, click on the Back
            button in your browser or the Return button shown
            below.</p>

        <form action="" method="post">
            <input type="hidden" name="action" value="join">
            <input class="btn btn-primary" type="submit" value="Return">
        </form>

    </div>
</div>




<%@include file="../common/common-js.jsp"%>
</body>
</html>

