<%--
  Created by IntelliJ IDEA.
  User: Serik.Taganbergenov
  Date: 04.04.2023
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="finalproject.models.News" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>NEWS.com</title>
    <%@include file="source.jsp" %>
</head>
<body>
<%@include file="header2.jsp"%>
<%
    String success = request.getParameter("success");
    if (success != null) {
%>
    <div class="container" style="width: 750px">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            News succefully added!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>
<%
    }
%>
    <div class="container" style="width: 750px">
        <form action="/addnewsto" method="post">
            <div class="mb-3">
                <select class="form-select" aria-label="News Category" name="categoryId">
                    <option selected>News Category</option>
                    <option value="1">Politics</option>
                    <option value="2">Sport</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="Input1" class="form-label">News Title</label>
                <input type="text" class="form-control" id="Input1" name="title">
            </div>
            <div class="mb-3">
                <label for="Textarea1" class="form-label">News Content</label>
                <textarea class="form-control" id="Textarea1" rows="5" name="content"></textarea>
            </div>
            <div class="mb-3">
                <select class="form-select" aria-label="Language" name="languageId">
                    <option selected>Language</option>
                    <option value="1">English</option>
                    <option value="2">Русский</option>
                </select>
            </div>
    <%--        <select name="categoryId">--%>
    <%--            <option value="1">Politics</option>--%>
    <%--            <option value="2">Sport</option>--%>
    <%--        </select>--%>
    <%--        title--%>
    <%--        <input type="text" name="title">--%>

    <%--        content--%>
    <%--        <textarea name="content" rows="5"></textarea>--%>
    <%--        <input type="number" name="languageId">--%>

            <button class="btn btn-success">SAVE</button>
        </form>
    </div>

</body>
</html>
