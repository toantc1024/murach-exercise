<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach Exercise - Email List</title>
    <%@include file="../common/common-css.jsp"%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<%@include file="../common/ui/navbar.jsp"%>
<div class="mx-auto p-2 d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div style="width: 800px;">

        <h1 class="text-success">Thanks for taking our survey <i class="fas fa-heart text-danger"></i></h1>
        <p>Here is the information that you entered:</p>
        <label>Email:</label>
        <span>${user.email}</span><br>
        <label>First Name:</label>
        <span>${user.firstName}</span><br>
        <label>Last Name:</label>
        <span>${user.lastName}</span><br>
        <label>Heard From:</label>
        <span>${user.heardFrom}</span><br>
        <label>Updates:</label>
        <span>${user.wantsUpdates}</span><br>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:if test="${user.wantsUpdates=='Yes'}">
            <label>Contact Via:</label>
            <span>${user.contactVia}</span><br>
        </c:if>

    </div>
</div>

<%@include file="../common/common-js.jsp"%>
</body>
</html>
