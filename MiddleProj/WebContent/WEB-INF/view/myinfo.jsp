<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.rq {
	color: red
}

.myinfo {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	width: 400px;
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

.gender-options {
	display: flex; /* Flexbox 사용 */
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
}

.gender-options input[type="radio"] {
	margin: 10px; /* 라디오 버튼 사이의 간격 */
}
</style>
</head>
<body>
	<%@include file="../../includes/top.jsp"%>

	<section class="upcoming-meetings">
		<div class="myinfo">
			<h4>나의 정보</h4>
			<hr>
			<form class="form-horizontal" method="post"
				onsubmit="return validateForm()">
				<div class="form-group">
					<p>아이디 <span class="rq"> 수정불가</span></p>
						<input type="text" class="form-control" id="id" name="memId"
							value="${member.memId}" readonly>
				</div>

				<div class="form-group">
					<p>비밀번호<span
						class="rq"> *</span></p>
						<input type="password" class="form-control" id="pass" name="memPw"
							value="${member.memPw}" placeholder="영문자, 숫자, 특수문자 포함, 최소 8자 이상"
							required
							pattern="(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#\$%\^&amp;\*\(\)_\+\|]).{8,}"
							onblur="trimInput(this)">
				</div>

				<div class="form-group">
					<p>비밀번호
						확인<span class="rq"> *</p>
					</label>
						<input type="password" class="form-control" id="passConfirm"
							name="mem_passConfirm" value="${member.memPw}" required
							pattern="(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#\$%\^&amp;\*\(\)_\+\|]).{8,}"
							onblur="trimInput(this)">
				</div>

				<div class="form-group">
					<p>이름</p>

						<input type="text" class="form-control" id="name" name="memName"
							value="${member.memName}" required pattern="[가-힣]{2,10}"
							onblur="trimInput(this)">
				</div>


				<div class="form-group">
					<p>휴대폰 번호<span
						class="rq"> *</span></p>
						<input type="text" class="form-control" id="hp" name="memPhone"
							value="${member.memPhone }" required
							pattern="[0-9]{2,3}-\d{3,4}-\d{4}" onblur="trimInput(this)">
				</div>

				<div class="form-group">
					<p>이메일<span
						class="rq"> *</span></p>
						<input type="email" class="form-control" id="mail" name="memMail"
							required value="${member.memMail}" onblur="trimInput(this)">
						<!-- 	        pattern="[0-9a-zA-Z]+@[0-9a-zA-Z]+(\.[a-z]+){1,2}"> -->
						<!-- 	        pattern="\w+@\w+(\.[a-z]+){1,2}"> -->
				</div>

				<div class="form-group">
					<p>
						성별:
						<c:choose>
							<c:when test="${member.memGender == 'male'}"> 남성</c:when>
							<c:otherwise>여성</c:otherwise>
						</c:choose>

					</p>
				</div>



				<div class="form-group">
						<button type="submit">수정하기</button>
						<span id="joinspan"></span>
				</div>
			</form>

		</div>
		<%@include file="../../includes/bottom.jsp"%>
	</section>

	<script>
		function trimInput(input) {

			input.value = input.value.replace(/\s+/g, '');
		}

		// 비밀번호 확인(점검 필요)
		function validateForm() {

			let password = document.getElementById("pass").value;
			let confirmPassword = document.getElementById("passConfirm").value;

			//비밀번호 일치여부 확인
			if (password != confirmPassword) {
				alert("비밀번호와 비밀번호 확인이 다릅니다.");
				return false; // 제출을 막음
			}

			return confirm("회원정보가 변경됩니다. 계속하시겠습니까?"); // 제출 가능

		};

		// 		$("#id").on("blur", function(){

		// 			//입력된 id정보 가져와서 ajax로 중복인지 체크
		// 			let $idVal = $("#id").val();

		// 				$.ajax({
		// 					//web-inf내부 문서는 직접적인 접근 불가
		// 					//servlet으로 연결해서 이동해야함 
		// 					url: "idChk.jsp?memId="+ $idVal, 
		// 					type: "get", 
		// 					success: function(data){

		// 						if(data.rst == "ok"){
		// 							$("#disp").text("사용가능").css("color","green");
		// 						}
		// 						else{
		// 							$("#disp").text("사용불가").css("color","red");

		// 						}
		// 					}
		// 				,error: function(xhr){
		// 					alert("오류상태: "+xhr.status);
		// 				}
		// 				,dataType: "json"
		// 				})

		// 		});
	</script>
</body>
</html>