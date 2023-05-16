package finalproject.servlets;

import finalproject.db.DBM;
import finalproject.models.News;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/newscategory")
public class NewsCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Cookie[] cookies = request.getCookies();
        boolean langCookieFound = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("language")) {
                    ArrayList<News> news = DBM.getNewsByCategory(categoryId, Integer.parseInt(cookie.getValue()));
                    langCookieFound = true;
                    request.setAttribute("newscategory", news);
                    break;
                }
            }
        }
        if (!langCookieFound) {
            ArrayList<News> news = DBM.getNewsAll(categoryId);
            request.setAttribute("newscategory", news);
        }
        request.getRequestDispatcher("/newscategory.jsp").forward(request,response);
    }

}
