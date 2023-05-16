package finalproject.servlets;

import finalproject.db.DBM;
import finalproject.models.Comment;
import finalproject.models.User;
import finalproject.models.News;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/addcomment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsid = request.getParameter("newsId");
        String comment = request.getParameter("comment");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (!comment.isEmpty()) {
            Comment newcomment = new Comment();
            newcomment.setComment(comment);
            News news = DBM.getNewsById(Integer.parseInt(newsid));
            newcomment.setNewsId(news);
            newcomment.setUserId(currentUser);
            DBM.addComment(newcomment);
            response.sendRedirect("/newsdetails?newsId=" + newsid);
        } else {
            response.sendRedirect("/newsdetails?newsId=" + newsid + "&error=commentEmpty");
        }

    }
}
