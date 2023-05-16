<%@ page import="finalproject.models.News" %><%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 06.04.2023
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News.com</title>
  <%@include file="source.jsp"%>
</head>
<body>
<%@include file="header2.jsp"%>
<%
    News novost = (News) request.getAttribute("news");
    if (novost != null) {
%>
<div class="container">
    <form action="/editnews" method="post">
        <input type="hidden" name="id" value="<%=novost.getId()%>">
        <div class="mb-3">
            <label for="Input1" class="form-label">Title</label>
            <input type="text" class="form-control" id="Input1" placeholder="<%=novost.getTitle().getTitle()%>" name="new_title">
        </div>
        <div class="mb-3">
            <label for="Input2" class="form-label">Content</label>
            <input type="text" class="form-control" id="Input2" placeholder="<%=novost.getContent().getContent()%>" name="new_content">
        </div>
        <button class="btn btn-success">Save changes</button>
    </form>
</div>
<%
    }
%>
</body>
</html>
