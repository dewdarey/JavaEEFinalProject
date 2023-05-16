<%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 31.03.2023
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NEWS.COM</title>
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
<%
    String success = request.getParameter("success");
    String wrongEmailOrPassword = request.getParameter("wrongEmailOrPassword");
    if (wrongEmailOrPassword != null) {
%>
<div class="container" style="width: 500px">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Incorrect <strong>email</strong> and/or <strong>password</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>
    <%
        }
        if (success != null) {
    %>
    <div class="container" style="width: 500px">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            User succefully registered!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>
    <%
        }
    %>

    <div class="container" style="width: 500px">
        <form action="/auth" method="post">
            <div class="mb-3">
                <label for="Input1" class="form-label">Your email</label>
                <input type="email" class="form-control" id="Input1" placeholder="Insert email" name="email">
            </div>
            <div class="mb-3">
                <label for="Input2" class="form-label">Password</label>
                <input type="password" class="form-control" id="Input2" placeholder="Insert password" name="password">
            </div>
            <button class="btn btn-success">SIGN IN</button>
        </form>
        First time on NEWS.com? <a href="/signup">Register</a>
    </div>

</body>
</html>
