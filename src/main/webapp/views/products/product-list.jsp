<%@ page import="com.murach.dto.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Register - Email List</title>
    <%@ include file="../common/common-css.jsp" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<%@ include file="../common/ui/navbar.jsp" %>
<!-- Product list with Add to Cart buttons -->
<div class="container">
    <div class="row">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product product : products) {
        %>
        <div class="col-md-4 mt-4">
            <div class="card m-2" style="width: 18rem;">
                <img src="<%= product.getImageUrl() %>" class="card-img-top" alt="<%= product.getName() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <p class="card-text"><strong>Price:</strong> $<%= product.getPrice() %></p>

                    <!-- Add to Cart form -->
                    <form id="addToCartForm" data-product-id="<%= product.getId() %>">
                        <input type="hidden" name="productId" value="<%= product.getId() %>" />
                        <input type="hidden" name="action" value="add" />
                        <button type="button" class="btn btn-primary add-to-cart">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<%@ include file="../common/common-js.jsp" %>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        // Attach event listener to the Add to Cart button
        document.querySelectorAll('.add-to-cart').forEach(button => {
            button.addEventListener('click', async (e) => {
                const form = e.target.closest('form');
                const productId = form.querySelector('input[name="productId"]').value;
                const action = form.querySelector('input[name="action"]').value;

                try {
                    // Send the data via fetch to the /cart servlet
                    const response = await fetch('/products', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: new URLSearchParams({
                            productId: productId,
                            action: action
                        }),
                    });



                    // Parse the JSON response
                    const data = await response.json();

                    // Check if the response contains the updated cart count
                    if (data.itemCount !== undefined) {
                        // Update the cart item count on the page
                        document.getElementById('cart-item-count').textContent = data.itemCount;
                    } else {
                        alert('Failed to update the cart.');
                    }
                } catch (error) {
                    if(error.response.status == 401) {
                        window.location.href = "/login";
                    }
                    console.error('Error:', error);
                    alert('An error occurred while adding the item to the cart.');
                }
            });
        });
    });

</script>

</body>
</html>
