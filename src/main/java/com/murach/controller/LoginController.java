package com.murach.controller;

import com.murach.dao.UserDAO;
import com.murach.dto.User;
import com.murach.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login JSP page
        request.getRequestDispatcher("views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findUserByEmail(email);
            if(user != null) {
                if(PasswordUtil.validatePassword(password, user.getPassword())) {
                    throw new ServletException("Wrong email or password");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(15 * 60);
                    response.sendRedirect(request.getContextPath() + "/");
                }
            } else {
                throw new ServletException("User not found");
            }
        } catch (ServletException e) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("views/auth/login.jsp").forward(request, response);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
