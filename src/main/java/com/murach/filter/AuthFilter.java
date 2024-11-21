package com.murach.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")  // Apply filter to all URLs
public class AuthFilter implements Filter {

    // List of URLs that require login
    private List<String> restrictedPaths = Arrays.asList("/library", "/cart", "/account", "/profile");

    // List of URLs that are public (don't require login)
    private List<String> publicPaths = Arrays.asList("/home", "/about", "/contact", "/login", "/register");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Check if the request is for a restricted URL pattern
        boolean isRestrictedPage = restrictedPaths.stream().anyMatch(requestURI::contains);
        boolean isPublicPage = publicPaths.stream().anyMatch(requestURI::contains);

        // If it's a restricted page and the user is not logged in
        if (isRestrictedPage) {
            if (httpRequest.getSession().getAttribute("user") == null) {
                // User is not logged in, redirect to login page
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                return; // Stop further processing of the request
            }
        }

        // If the user is logged in and tries to access the login page, redirect them to the homepage
        if (httpRequest.getSession().getAttribute("user") != null && requestURI.contains("/login")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            return; // Stop further processing
        }

        // Proceed with the request if the page is public or user is logged in for restricted pages
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
