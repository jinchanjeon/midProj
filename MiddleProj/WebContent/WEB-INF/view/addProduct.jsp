<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 추가</title>
</head>
<body>
    <h1>상품 추가</h1>
    <form action="${pageContext.request.contextPath}/product/add" method="post">
        <label for="productId">상품 ID:</label>
        <input type="text" id="productId" name="productId"><br>

        <label for="storeId">매장 ID:</label>
        <input type="text" id="storeId" name="storeId"><br>

        <label for="productName">상품 이름:</label>
        <input type="text" id="productName" name="productName"><br>

        <label for="price">가격:</label>
        <input type="number" id="price" name="price"><br>

        <label for="stock">재고 수량:</label>
        <input type="number" id="stock" name="stock"><br>

        <label for="categoryId">카테고리 ID:</label>
        <input type="text" id="categoryId" name="categoryId"><br>

        <label for="imgUrl">이미지 URL:</label>
        <input type="text" id="imgUrl" name="imgUrl"><br>

        <label for="content">상품 설명:</label>
        <textarea id="content" name="content"></textarea><br>

        <button type="submit">추가</button>
    </form>
</body>
</html>
