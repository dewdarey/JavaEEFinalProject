<%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 05.04.2023
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="finalproject.models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="finalproject.models.Comment" %>
<%@ page import="java.sql.SQLOutput" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="finalproject.models.User" %>
<html>
<head>
    <title>Comments</title>
    <%@include file="source.jsp"%>
</head>
<body>
<%@include file="header2.jsp"%>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String error = request.getParameter("error");
    if (error!=null && !error.isEmpty()){
%>
<div class="container">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Comment can not be empty!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>
<%
    }
%>
<%
    News novost = (News) request.getAttribute("newsbyid");
    if (novost != null) {
%>
    <div class="container">
        <div>
            <h3><%=novost.getTitle().getTitle()%></h3>
            <p><%=novost.getContent().getContent()%></p>
            <p><%=novost.getPostDate().format(DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyyy"))%></p>
            <p><b>Comments</b></p>
        </div>
        <%
        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("commentsbynewsid");
        if (comments != null){
            for(Comment c : comments){
        %>
            <h6><%=c.getUserId().getFullName()%></h6>
            <p><%=c.getComment()%></p>
            <p><%=c.getPostDate().format(DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyyy"))%></p>
        <br>
        <%
                }
            }
        %>
    </div>
    <div class ="container">
        <form action="/addcomment" method ="post">
            <input type="hidden" name="newsId" value="<%=novost.getId()%>">
            <div class="mb-3">
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="comment"></textarea>
            </div>
            <button class="btn btn-success">Add new comment</button>
        </form>
    </div>
<%
    if (currentUser.getRoleID() == 1) {
%>
    <div class="container">
            <a href="/editnews?id=<%=novost.getId()%>" class="btn btn-success">Edit News</a>
    </div>
    <form action ="/deletenews" method="post">
        <div class="container mt-3">
            <input type="hidden" name ="newsId" value ="<%=novost.getId()%>">
            <button class="btn btn-danger">Delete News</button>
        </div>
    </form>
<%
        }
    }
%>
</body>
</html>
