package finalproject.servlets;

import finalproject.db.DBM;
import finalproject.models.News;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean langCookieFound = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("language")) {
                    ArrayList<News> news = DBM.getNewsByLang(Integer.parseInt(cookie.getValue()));
                    request.setAttribute("news", news);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                    langCookieFound = true;
                    break;
                }
            }
            if (!langCookieFound) {
                ArrayList<News> news = DBM.getNews();
                request.setAttribute("news", news);
                request.getRequestDispatcher("/main.jsp").forward(request, response);
            }
        } else {
            ArrayList<News> news = DBM.getNews();
            request.setAttribute("news", news);
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }
    }
}


