<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매출 추가</title>
</head>
<body>
    <h1>매출 추가</h1>
    <form action="${pageContext.request.contextPath}/sales/add" method="post">
        <label for="saleId">매출 ID:</label>
        <input type="text" id="saleId" name="saleId"><br>

        <label for="productId">상품 ID:</label>
        <input type="text" id="productId" name="productId"><br>

        <label for="quantitySold">판매된 수량:</label>
        <input type="number" id="quantitySold" name="quantitySold"><br>

        <button type="submit">추가</button>
    </form>
</body>
</html>
