<%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 27.03.2023
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="source.jsp" %>
</head>
<body>
<div class="container py-3">
    <header>
        <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">NEWS.com</a>
            </div>
        </nav>
    </header>
</div>
<div class="container" style="width: 500px">
    <%
        String error = request.getParameter("error");
        String success = request.getParameter("success");
        if (error != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Incorrect <strong>email</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
        }
        if (success != null) {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        User succefully registred!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
        }
    %>
    <form action="/signup" method="post">
        <div>
            <div class="mb-3">
                <label for="Input1" class="form-label">Your email</label>
                <input type="email" class="form-control" id="Input1" placeholder="Insert email" name="email">
            </div>
            <div class="mb-3">
                <label for="Input2" class="form-label">Password</label>
                <input type="password" class="form-control" id="Input2" placeholder="Insert password" name="password">
            </div>
            <div class="mb-3">
                <label for="Input3" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="Input3" placeholder="Insert Full Name" name="fullname">
            </div>
            <button class="btn btn-success">Register</button>
        </div>
    </form>
</div>
</body>
</html>
