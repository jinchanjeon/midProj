<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="member" value="${sessionScope.member}"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>영화 수정</title>
<style>
body, html {
	background-color: black !important;
	color: white !important;
	font-family: Arial, sans-serif;
}

form {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	background-color: #222;
	border-radius: 10px;
}

label {
	display: block;
	margin: 10px 0 5px;
}

input[type="text"], input[type="date"], textarea, select {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	border: 1px solid #444;
	background-color: #333;
	color: white !important;
}

button {
	padding: 10px 15px;
	background-color: #FF0000;
	color: white !important;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #d40000;
}
</style>
</head>
<body>
	<%@include file="../../includes/top.jsp"%>

	<section class="heading-page">
		<form id="updateMovieForm" action="${contextPath}/updateMovie.do"
			method="post">
			<input type="hidden" name="movieNo" value="${movieInfo.movieNo}">

			<label for="movieName">영화 제목</label> 
			<input type="text" id="movieName" name="movieName" value="${movieInfo.movieName}" required> '
			<label for="movieCompany">배급사</label> 
			<input type="text" id="movieCompany" name="movieCompany" value="${movieInfo.movieCompany}" required> 
			<label for="movieRelease">개봉일</label>
<input type="text" id="movieRelease" name="movieRelease" 
       value="${movieInfo.movieRelease}" 
       placeholder="yyyyMMdd 형식으로 입력하세요" 
       required>

			<label for="movieCountry">국가</label> 
			<input type="text" id="movieCountry" name="movieCountry" value="${movieInfo.movieCountry}" required>
			<label for="movieRunningtime">상영시간 (분)</label> 
			<input type="text" id="movieRunningtime" name="movieRunningtime" value="${movieInfo.movieRunningtime}" required> 
			<label for="movieLevel">관람 등급</label> 
			<select id="movieLevel" name="movieLevel">
				<option value="전체관람가"
					<c:if test="${movieInfo.movieLevel.contains('전체')}">selected</c:if>>
					전체관람가</option>
				<option value="12세관람가"
					<c:if test="${movieInfo.movieLevel.contains('12세')}">selected</c:if>>
					12세관람가</option>
				<option value="15세관람가"
					<c:if test="${movieInfo.movieLevel.contains('15세')}">selected</c:if>>
					15세관람가</option>
				<option value="18세관람가"
					<c:if test="${movieInfo.movieLevel.contains('18세')}">selected</c:if>>
					18세관람가(청소년관람불가)</option>
				<option value="미성년자관람불가"
					<c:if test="${movieInfo.movieLevel.contains('미성년')}">selected</c:if>>
					미성년자관람불가</option>

			</select> <label for="movieState">상영 상태</label> <select id="movieState"
				name="movieState">
				<option value="상영중"
					<c:if test="${movieInfo.movieState == '상영중'}">selected</c:if>>상영중</option>
				<option value="개봉예정작"
					<c:if test="${movieInfo.movieState == '개봉예정작'}">selected</c:if>>개봉예정작</option>
				<option value="상영안함"
					<c:if test="${movieInfo.movieState == '상영안함'}">selected</c:if>>상영안함</option>
			</select> 
			<label for="moviePoster">포스터 URL</label> 
			<input type="text"
				id="moviePoster" name="moviePoster" value="${movieInfo.moviePoster}"
				required>
				<label for="movieContent">줄거리</label> 
			<input type="text"
				id="movieContent" name="movieContent" value="${movieInfo.movieContent}"
				required> 
				<label for="movieTrailer">예고편 URL</label> <input
				type="text" id="movieTrailer" name="movieTrailer"
				value="${movieInfo.movieTrailer}" required>

			<button type="submit" class="edit">수정하기</button>
		</form>
	</section>
</body>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$('.edit').on('click', function(event){
    event.preventDefault(); // 폼 제출 방지

    var movieName = $('#movieName').val();
    var movieCompany = $('#movieCompany').val();
    var movieRelease = $('#movieRelease').val();
    var movieCountry = $('#movieCountry').val();
    var movieRunningtime = $('#movieRunningtime').val();
    var movieLevel = $('#movieLevel').val();
    var movieState = $('#movieState').val();
    var moviePoster = $('#moviePoster').val();
    var movieTrailer = $('#movieTrailer').val();
    var movieContent = $('#movieContent').val();
    var movieNo = $("input[name='movieNo']").val(); 

    $.ajax({
        url: "${contextPath}/updateMovie.do",
        type: "POST",
        data: {
            movieNo: movieNo,
            movieName: movieName,
            movieCompany: movieCompany,
            movieRelease: movieRelease,
            movieCountry: movieCountry,
            movieRunningtime: movieRunningtime,
            movieLevel: movieLevel,
            movieState: movieState,
            moviePoster: moviePoster,
            movieTrailer: movieTrailer,
            movieContent: movieContent
        },
        success: function(response) {
        	alert(response);
            location.href = "${contextPath}/movieDetail.do?movie_no=" + movieNo; // 성공 후 이동
        },
        error: function(xhr, status, error) {
            console.error("Error Status: " + status);
            console.error("Error Message: " + error);
            alert("영화 정보 수정에 실패했습니다.");
        }
    });

});
</script>

</html>
