<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>영화 스케줄 추가</title>
</head>
<body>
    <h1>영화 스케줄 추가</h1>
    <form action="${pageContext.request.contextPath}/schedule/add" method="post">
        <label for="scheduleId">스케줄 ID:</label>
        <input type="text" id="scheduleId" name="scheduleId"><br>

        <label for="movieId">영화 ID:</label>
        <input type="text" id="movieId" name="movieId"><br>

        <label for="screeningDate">상영 날짜:</label>
        <input type="date" id="screeningDate" name="screeningDate"><br>

        <label for="movieStart">영화 시작 시간:</label>
        <input type="time" id="movieStart" name="movieStart"><br>

        <label for="movieEnd">영화 종료 시간:</label>
        <input type="time" id="movieEnd" name="movieEnd"><br>

        <label for="roomNum">상영관 번호:</label>
        <input type="text" id="roomNum" name="roomNum"><br>

        <button type="submit">추가</button>
    </form>
</body>
</html>
