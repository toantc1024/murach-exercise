<nav class="navbar navbar-expand-lg p-4 bg-black" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="font-weight-bold navbar-brand" href="/">Healing your self.</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().equals("/") ? "active" : "" %>" href="/">Homepage</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().equals("/products") ? "active" : "" %>" href="/products">Our products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().equals("/survey") ? "active" : "" %>" href="/survey">Survey</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().equals("/email-list") ? "active" : "" %>" href="/email-list">Newsletter</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <div class="container px-4 text-center">
                    <div class="row gx-2">
                        <%@ page import="com.murach.dto.User" %>
                        <%@ page import="com.murach.dto.Cart" %>
                        <%@ page import="jakarta.mail.FetchProfile" %>
                        <%
                            // Retrieve the User object from the session
                            User user = (User) session.getAttribute("user");
                            String errorMessage = "";
                            if (user != null) {
                                // User is logged in, display their last name
                                Cart cart = user.getCart();
                                int ItemCount = 0;
                                if(cart != null) {
                                    try {
                                        ItemCount = cart.getTotalItems();
                                    } catch (Exception e) {
                                        errorMessage = e.getMessage();
                                    }
                                }

                                String lastName = user.getLastName();  // Retrieve the last name
                        %>
                        <%= errorMessage %>
                        <div class="col">
                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Welcome, <%= lastName %>  <!-- Display the last name -->
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">My library</a></li>
                                    <li><a class="dropdown-item" href="/logout">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col">
                            <a href="/cart" type="button" class="btn btn-primary position-relative">
                                Cart
                                <span class="position-absolute top-0 start-100 translate-middle badge text-black rounded-pill bg-white">
                                    <div id="cart-item-count">
<%= ItemCount %>
                                    </div>
                    <span class="visually-hidden">unread messages</span>
                </span>
                            </a>
                        </div>
                        <%
                        } else {
                        %>
                        <!-- User is not logged in, show login link -->
                        <div class="col">
                            <a href="/login" class="btn btn-primary">Login</a>
                        </div>
                        <%
                            }
                        %>

                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>
