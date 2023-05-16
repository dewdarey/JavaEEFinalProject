package finalproject.servlets;

import finalproject.db.DBM;
import finalproject.models.Language;
import finalproject.models.News;
import finalproject.models.NewsContent;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/editnews")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        News news = DBM.getNewsById(id);
        request.setAttribute("news",news);
        request.getRequestDispatcher("/editnews.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("new_title");
        String content = request.getParameter("new_content");
        NewsContent newsContent = new NewsContent();
        newsContent.setTitle(title);
        newsContent.setContent(content);
        newsContent.setId(id);
        DBM.editNewsContent(newsContent);
        response.sendRedirect("/main");
    }
}
