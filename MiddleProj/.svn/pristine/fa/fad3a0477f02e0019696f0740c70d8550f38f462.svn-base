<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<!DOCTYPE html>
<html>
<head>

<style>
section {
    display: flex;
   flex-direction: column; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */  
    align-items: center; /* 가로 중앙 정렬 */ 
    height: 100vh; /* 화면 전체 높이 사용 */
}
.login {
/* 	background-color: white; */
	border-radius: 8px;
	padding: 20px;
	width: 400px;
	text-align: center;
	margin: 0 auto; /* 가운데 정렬 */
	
}
.form-group {
        margin-bottom: 15px; /* 입력란 간격 */
    }
button {
	background-color: #5cb85c;
	color: white;
	border: none;
	padding: 10px;
	border-radius: 8px;
	cursor: pointer;
	width: 100%;
}

</style>
</head>

<body>
<%@include file="../../includes/top.jsp" %>

	<section class="upcoming-meetings">
		<div class="login">
			<c:if test="${not empty message}">
				<div class="alert alert-danger">${message}</div>
			</c:if>
			<form action="${contextPath}/login.do" method="post" class="user">
				<div class="form-group">
					<input type="text" class="form-control form-control-user"
						name="userId" placeholder="아이디">
				</div>
				<div class="form-group">
					<input type="password" class="form-control form-control-user"
						name="userPw" placeholder="비밀번호">
				</div>
					<button type="submit"> 로그인</button>
				<hr>
				<div class="text-center">
					<a href="${contextPath}/findId.do">아이디찾기</a> | <a
						href="${contextPath}/findPw.do">비밀번호찾기</a> | <a
						href="${contextPath}/join.do">회원가입</a>
				</div>
			</form>
		</div>
		<%@include file="../../includes/bottom.jsp" %>
	</section>


</body>
</html>
