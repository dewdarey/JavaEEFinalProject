package finalproject.servlets;

import finalproject.models.User;
import finalproject.db.DBM;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(value = "/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String fullname=request.getParameter("fullname");
        int roleId= 2;
        String redirect = "/signup?error";
        User user = DBM.getUserByEmail(email);
        if (user == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFullName(fullname);
            newUser.setRoleID(roleId);
            DBM.addUser(newUser);
            redirect = "/auth?success";
        }
        response.sendRedirect(redirect);
    }
}
