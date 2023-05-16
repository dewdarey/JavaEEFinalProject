package finalproject.servlets;

import finalproject.db.DBM;
import finalproject.models.NewsContent;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/addnewsto")
public class AddNewsToServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addnewsto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId=request.getParameter("categoryId");
        int id = DBM.addNews(Integer.parseInt(categoryId));
        NewsContent newsContents = new NewsContent();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String languageId = request.getParameter("languageId");
        newsContents.setId(id);
        newsContents.setTitle(title);
        newsContents.setContent(content);
        newsContents.setLanguageId(Integer.parseInt(languageId));
        DBM.addNewsContent(newsContents);
        response.sendRedirect("/addnewsto?success");
    }
}
