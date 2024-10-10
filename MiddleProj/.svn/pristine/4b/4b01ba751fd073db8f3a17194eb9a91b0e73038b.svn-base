<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>영화 스케줄 목록</title>
</head>
<body>
    <h1>영화 스케줄 목록</h1>
    <table>
        <thead>
            <tr>
                <th>스케줄 ID</th>
                <th>영화 ID</th>
                <th>상영 날짜</th>
                <th>시작 시간</th>
                <th>종료 시간</th>
                <th>상영관 번호</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="schedule" items="${schedules}">
                <tr>
                    <td>${schedule.scheduleId}</td>
                    <td>${schedule.movieId}</td>
                    <td>${schedule.screeningDate}</td>
                    <td>${schedule.movieStart}</td>
                    <td>${schedule.movieEnd}</td>
                    <td>${schedule.roomNum}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
