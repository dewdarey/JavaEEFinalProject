package finalproject.servlets;

import finalproject.db.DBM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/cabinet")
public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/cabinet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newFullName = request.getParameter("new_fullname");
        String newPassword = request.getParameter("new_password");
        String reNewPassword = request.getParameter("re_new_password");
        Long id = Long.valueOf(request.getParameter("id"));
        String redirect = "/cabinet?error";
        if (!newFullName.isEmpty()) {
            DBM.changeFullName(id, newFullName);
            redirect = "/cabinet?successfullname";
        }
        if (!newPassword.isEmpty()) {
            if (newPassword.equals(reNewPassword)) {
                DBM.changePassword(id, newPassword);
                redirect = "/cabinet?successpassword";
            } else redirect = "/cabinet?error";
        }
        response.sendRedirect(redirect);
    }
}
