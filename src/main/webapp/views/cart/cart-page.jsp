<%@ page import="com.murach.dto.Cart, com.murach.dto.LineItem" %>
<%@ page import="java.util.List" %>
<%@ include file="../common/common-css.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="../common/ui/navbar.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="container mt-4">
    <h2>Your Cart</h2>
    <!-- Check if the cart has items -->


    <!-- Check if the cart has items -->
    <c:if test="${empty items}">
        <p>Your cart is empty.</p>
    </c:if>

    <form method="POST" action="/cart">

        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th width="115px">Product</th>
                <th width="80px">Price</th>
                <th width="200px">Quantity</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!-- Iterate over lineItems -->
            <c:forEach var="lineItem" items="${user.getCart().getItems()}">


                <tr>
                    <td>${lineItem.getProduct().getName()}</td>
                    <td>$${lineItem.getProduct().getPrice()}</td>
                    <td>
                        <div class="d-flex bd-highlight mb-3">
                            <div class="p-2 bd-highlight">
                                <form action method="post">
                                    <input type="hidden" name="action" value="update_quantity" />
                                    <input type="hidden" name="type" value="decrease"/>
                                    <input type="hidden" name="itemId" value="${lineItem.getId()}"/>
                                    <input type="submit" class="btn btn-secondary" name="updateQuantity" value="-"/>
                                </form>
                            </div>
                            <div class="p-2 bd-highlight">
                                <input
                                        type="number"
                                        name="quantity"
                                        id="quantity-input-${lineItem.getProduct().getId()}"
                                        value="${lineItem.getQuantity()}"
                                        min="1"
                                        class="form-control"
                                        readonly>
                            </div>
                            <div class="p-2 bd-highlight">  <form action method="post">
                                <input type="hidden" name="action" value="update_quantity" />
                                <input type="hidden" name="type" value="increase"/>
                                <input type="hidden" name="itemId" value="${lineItem.getId()}"/>
                                <input type="submit" class="btn btn-secondary" name="updateQuantity" value="+"/>
                            </form></div>
                        </div>
                    </td>
                    <td>${lineItem.getProduct().getPrice() * lineItem.getQuantity()}</td>
                    <td>
                        <form action method="post">
                            <input type="hidden" name="action" value="update_quantity" />
                            <input type="hidden" name="type" value="delete"/>
                            <input type="hidden" name="itemId" value="${lineItem.getId()}"/>
                            <input type="submit" class="btn btn-secondary" name="updateQuantity" value="Remove item"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit" name="action" value="checkout" class="btn btn-success">Proceed to Checkout</button>
    </form>

    <script>
        function updateQuantity(item_id, quantity) {
            // Ensure the quantity is valid
            const form = document.getElementById(`update-form-${item_id}`);
            if (form) {
                // Update the quantity field in the form
                const quantityInput = form.querySelector('input[name="quantity"]');
                quantityInput.value = quantity;

                // Submit the form
                form.submit();
            }
        }
    </script>


</div>


<%@ include file="../common/common-js.jsp" %>

<script>

</script>

</body>
</html>
