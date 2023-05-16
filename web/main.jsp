<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 31.03.2023
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="finalproject.models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>NEWS.com</title>
    <%@include file="source.jsp" %>
</head>
<body>
<%@include file="header.jsp"%>
<%
    ArrayList<News> novost = (ArrayList<News>) request.getAttribute("news");
    ArrayList<News> newsbylanguage = (ArrayList<News>) request.getAttribute("newsbylanguage");
    if (newsbylanguage != null) {
        for (News n : newsbylanguage) {
%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-12">
            <h3><%=n.getTitle().getTitle()%></h3>
            <p><%=n.getContent().getContent()%></p>
            <p><%=n.getPostDate().format(DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyyy"))%></p>
            <a href="/newsdetails?newsId=<%=n.getId()%>">Details</a>
        </div>
    </div>
</div>
<%
        }
}
    else if (novost != null) {
       for (News n : novost) {
%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-12">
            <h3><%=n.getTitle().getTitle()%></h3>
            <p><%=n.getContent().getContent()%></p>
            <p><%=n.getPostDate().format(DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyyy"))%></p>
            <a href="/newsdetails?newsId=<%=n.getId()%>">Details</a>
        </div>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
