package com.murach.controller;

import com.murach.dao.UserDAO;
import com.murach.dto.Cart;
import com.murach.dto.User;
import com.murach.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String lastName = req.getParameter("lastName");

        // Basic validation (you can add more)
        if (email == null || email.isEmpty() || password == null || password.isEmpty() || lastName == null || lastName.isEmpty()) {
            req.setAttribute("errorMessage", "All fields are required.");
            req.setAttribute("email", email);
            req.setAttribute("lastName", lastName);
            req.getRequestDispatcher("views/auth/register.jsp").forward(req, resp);
            return;
        }

        User exist_user = userDAO.findUserByEmail(email);

        if (exist_user != null) {
            req.setAttribute("errorMessage", "Email already exists.");
            req.setAttribute("email", email);
            req.setAttribute("lastName", lastName);
            req.getRequestDispatcher("views/auth/register.jsp").forward(req, resp);
            return;
        } else {
            try {
                String hashedPassword = PasswordUtil.hashPassword(password);
                User user = new User(lastName, email, hashedPassword);
                Cart cart = new Cart(user);
                user.setCart(cart);
                    user = userDAO.create(user);
                if(user == null) {
                    throw new RuntimeException("Internal server error");
                }
                req.getSession().setAttribute("successMessage", "Registered successfully.");
                resp.sendRedirect(req.getContextPath() + "/login");
            } catch (RuntimeException e) {
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher("views/auth/register.jsp").forward(req, resp);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
            }
        }


    }


}

