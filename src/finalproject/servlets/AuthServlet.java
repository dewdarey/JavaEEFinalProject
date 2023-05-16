package finalproject.servlets;


import finalproject.db.DBM;
import finalproject.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            request.getRequestDispatcher("/auth.jsp").forward(request, response);
        }else response.sendRedirect("/main");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = DBM.getUserByEmail(email);
        String redirect = "/auth?wrongEmailOrPassword";
        if (user != null) {
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);

                redirect = "/main";
            }
        }
        response.sendRedirect(redirect);
    }
}
