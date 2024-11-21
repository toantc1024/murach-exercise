package com.murach.controller;

import com.murach.dao.CartDAO;
import com.murach.dao.LineItemDAO;
import com.murach.dao.ProductDAO;
import com.murach.dao.UserDAO;
import com.murach.dto.Cart;
import com.murach.dto.LineItem;
import com.murach.dto.Product;
import com.murach.dto.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    // Handle GET request for displaying cart page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Get user from session

        if (user == null) {
            // If user is not logged in, redirect to login page
            response.sendRedirect("/login");
            return;
        }
        // Forward to the JSP page to display the cart
        request.getRequestDispatcher("views/cart/cart-page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        CartDAO cartDAO = new CartDAO();
        LineItemDAO lineItemDAO = new LineItemDAO();
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = user.getCart();
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        String action = request.getParameter("action");
        if(action.equals("update_quantity")) {
            int item_id = Integer.parseInt(request.getParameter("itemId"));

            LineItem item = lineItemDAO.find(item_id);

            String type = request.getParameter("type");
                if((type.equals("decrease") && item.getQuantity() == 1) || type.equals("remove")) {
                    cart.getItems().removeIf(line_item -> line_item.getId() == item_id);
                    cart = cartDAO.update(cart);
                    user.setCart(cart);
                    userDAO.update(user);
                }
            else if (type.equals("decrease")) {
                    lineItemDAO.updateQuantity(item_id, item.getQuantity() - 1);
            } else {
                    lineItemDAO.updateQuantity(item_id, item.getQuantity() + 1);
            }
        }

        user = userDAO.find(user.getId());
        // Update the cart in the session after modification
        session.setAttribute("user", user);
        // Redirect back to the cart page to show updated cart
        response.sendRedirect("/cart");
    }
}
