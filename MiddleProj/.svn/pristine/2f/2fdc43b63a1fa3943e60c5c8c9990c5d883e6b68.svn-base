<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 목록</title>
</head>
<body>
    <h1>상품 목록</h1>
    <table>
        <thead>
            <tr>
                <th>상품 ID</th>
                <th>상품 이름</th>
                <th>가격</th>
                <th>재고 수량</th>
                <th>카테고리 ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>${product.stock}</td>
                    <td>${product.categoryId}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
