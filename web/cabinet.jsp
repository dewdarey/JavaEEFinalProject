<%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 02.04.2023
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="finalproject.models.News" %>
<%@ page import="finalproject.models.User" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Cabinet</title>
    <%@include file="source.jsp" %>
</head>
<body>
<div class="container py-3">
    <header>
        <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">NEWS.com</a>
            </div>
            <div class="collapse navbar-collapse justify-content-end">
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</div>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String successfullname = request.getParameter("successfullname");
    String successpassword = request.getParameter("successpassword");
    String error = request.getParameter("error");
    if (successfullname != null){
%>
<div class="container" style="width: 500px">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        Full Name successfully changed!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>
<%
    }
    if (successpassword!=null){
%>
<div class="container" style="width: 500px">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        Password successfully changed!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>
<%
    }
    if(error != null){
%>
<div class="container" style="width: 500px">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        New psaaword and re-inserted new password not equals!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>
<%
    }
%>
<div class="container" style="width: 500px">
    <form action="/cabinet" method="post">
        <div>
            <input type="hidden" name="id" value="<%=currentUser.getId()%>">
            <div class="mb-3">
                <label for="Input1" class="form-label">Change Full name</label>
                <input type="text" class="form-control" id="Input1" placeholder="Insert new Full Name"
                       name="new_fullname">
            </div>
            <div class="mb-3">
                <label for="Input2" class="form-label">Change Password</label>
                <input type="password" class="form-control" id="Input2" placeholder="Insert new password"
                       name="new_password">
            </div>
            <div class="mb-3">
                <label for="Input3" class="form-label">Repeat new Password</label>
                <input type="password" class="form-control" id="Input3" placeholder="Re-Insert new password"
                       name="re_new_password">
            </div>
            <button class="btn btn-success">Save</button>
        </div>
    </form>
    <%
        if (currentUser.getRoleID() == 1) {
    %>
    <div>
        <a href="/addnewsto" class="btn btn-success">Add News</a>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
