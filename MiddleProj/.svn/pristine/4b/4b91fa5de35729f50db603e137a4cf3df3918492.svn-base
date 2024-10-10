<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: black !important;;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
        }
        form div {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], textarea, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            height: 150px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>게시글 수정</h1>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form action="boardModify.do" method="post">
            <input type="hidden" name="boardNo" value="${board.board_no}">
            <div>
                <label for="board_title">제목:</label>
                <input type="text" id="board_title" name="board_title" required value="${board.board_title}">
            </div>
            <div>
                <label for="board_content">내용:</label>
                <textarea id="board_content" name="board_content" required>${board.board_content}</textarea>
            </div>
            <div>
                <label for="board_code">게시판:</label>
                <select id="board_code" name="board_code" required>
                    <c:forEach var="category" items="${categoryList}">
                        <c:choose>
                            <c:when test="${category.board_code eq '1' and (memCode eq '2' or memCode eq '3')}">
                                <option value="${category.board_code}" ${category.board_code == board.board_code ? 'selected' : ''}>${category.code_name}</option>
                            </c:when>
                            <c:when test="${category.board_code ne '1'}">
                                <option value="${category.board_code}" ${category.board_code == board.board_code ? 'selected' : ''}>${category.code_name}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <button type="submit">수정 완료</button>
        </form>
    </div>
</body>
</html>