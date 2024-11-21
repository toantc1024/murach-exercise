package com.murach.controller;

import com.murach.dao.CartDAO;
import com.murach.dao.ProductDAO;
import com.murach.dto.Cart;
import com.murach.dto.LineItem;
import com.murach.dto.Product;
import com.murach.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    ProductDAO productDAO;
    CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
        cartDAO = new CartDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assume you have a service or DAO that fetches the product list
        List<Product> products = productDAO.findAll();  // ProductService is your service class
        request.setAttribute("products", products);  // Set the product list as a request attribute

        // Forward to the JSP page
        request.getRequestDispatcher("views/products/product-list.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle actions like add, remove, etc.
        String action = request.getParameter("action"); // Get action (e.g., add, remove)
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if (user != null) {
            Cart cart = user.getCart();  // Get the user's cart
            long productId = Long.parseLong(request.getParameter("productId"));  // Get productId from the form

            if(action.equals("add")) {
                // find product by id
                Product product = productDAO.find(productId);

                boolean productInCart = false;
                List<LineItem> items = cart.getItems();
                for (LineItem lineItem : items) {
                    if (lineItem.getProduct().getId().equals(product.getId())) {
                        // If the product is found, increase the quantity
                        lineItem.setQuantity(lineItem.getQuantity() + 1);
                        productInCart = true;
                        break;
                    }
                }

                // If product is not in the cart, create a new LineItem
                if (!productInCart) {
                    LineItem newItem = new LineItem(product, 1); // Add product with quantity 1
                    cart.addItem(newItem); // Add the new line item to the cart
                }


                try {
                    cart = cartDAO.update(cart);
                    user.setCart(cart);
                } catch (Exception e) {
                    // show errorMessage
                    request.setAttribute("errorMessage", e.getMessage());
//                    request.getRequestDispatcher("views/cart/cart-page.jsp").forward(request, response);
                }

                request.getSession().setAttribute("user", user);
            }

            // Send back the updated cart count as a JSON response
            response.setContentType("application/json");
            response.getWriter().write("{\"itemCount\": " + cart.getTotalItems() + "}");
        } else {
            // If the user is not logged in, send an error response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }
    }

}
