<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.movie.Inventory" %>
<html>
<head><title>Inventory Management</title></head>
<body>
<h2>Inventory Management</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Item Name</th>
        <th>Quantity</th>
    </tr>
    <%
        List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventories");
        if (inventories == null || inventories.isEmpty()) {
    %>
        <tr>
            <td colspan="3">No inventories found.</td>
        </tr>
    <%
        } else {
            for (Inventory inventory : inventories) {
    %>
        <tr>
            <td><%= inventory.getId() %></td>
            <td><%= inventory.getItemName() %></td>
            <td><%= inventory.getQuantity() %></td>
        </tr>
    <%
            }
        }
    %>
</table>
<a href="/manager?action=addInventory">Add Inventory</a>
</body>
</html>
