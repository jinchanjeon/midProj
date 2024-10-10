<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
section {
    display: flex;
   flex-direction: column; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */  
    align-items: center; /* 가로 중앙 정렬 */ 
    height: 100vh; /* 화면 전체 높이 사용 */
}
.review-table {
	width: 80%;
	border-collapse: collapse;
	margin: 0 auto;
}

.review-table th, .review-table td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
	color: white;
}

.review-table th {
	background-color: #333;
}
</style>
</head>
<body>
	<%@include file="../../includes/top.jsp"%>

	<section class="upcoming-meetings">
		<div >
			<table class="review-table">
				<thead>
					<tr>
						<th>순번</th>
						<th>지점</th>
						<th>상영관</th>
						<th>영화</th>
						<th>좌석</th>
						<th>상영 날짜</th>
						<th>시작시간</th>
						<th>종료시간</th>
						<th>가격</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty myticket}">
						<c:forEach var="myticket" items="${myticket}" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${myticket.spotName}</td>
								<td>${myticket.roomName}</td>
								<td>${myticket.movieName}</td>
								<td>${myticket.seatNo}</td>
								<td>${myticket.movieReservDate}</td>
								<td>${myticket.movieStart}</td>
								<td>${myticket.movieEnd}</td>
								<td>${myticket.moviePrice}</td>

							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	<%@include file="../../includes/bottom.jsp"%>
	</section>
</body>
</html>