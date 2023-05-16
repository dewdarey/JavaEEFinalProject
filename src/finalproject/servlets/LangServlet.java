package finalproject.servlets;

import finalproject.models.News;
import finalproject.db.DBM;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/lang")
public class LangServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String languageId =request.getParameter("languageId");
        ArrayList<News> news = DBM.getNewsByLang(Integer.parseInt(languageId));
        request.setAttribute("newsbylanguage", news);
        Cookie cookie = new Cookie("language", languageId);
        cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(cookie);
        request.getRequestDispatcher("/main").forward(request,response);
    }
}
