<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매출 목록</title>
</head>
<body>
    <h1>매출 목록</h1>
    <table>
        <thead>
            <tr>
                <th>매출 ID</th>
                <th>상품 ID</th>
                <th>판매된 수량</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sale" items="${sales}">
                <tr>
                    <td>${sale.saleId}</td>
                    <td>${sale.productId}</td>
                    <td>${sale.quantitySold}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
