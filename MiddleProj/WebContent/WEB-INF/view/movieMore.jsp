<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="TemplateMo">
<style>
body, html{
    background-color:black !important; 
}

button {
	background-color: #FF0000;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px !important;
	font-size: 1rem;
	
}

button:hover {
	background-color: #d40000 !important;
}
section.upcoming-meetings {
padding-top:150px !important;
}
.genop {
	margin-bottom: 30px;
}

.movie-list {
	display: grid; /* 그리드 레이아웃을 사용 */
	grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	/* 자동으로 열 수 조정 */
	gap: 20px; /* 영화 간의 간격 */
	justify-items: center; /* 중앙 정렬 */
}

.movie-item {
	text-align: center; /* 텍스트 중앙 정렬 */
}

img {
	width: 100% !important; /* 이미지 너비를 항목 너비에 맞춤 */
	border-radius: 15%; /* 이미지 둥글게 만들기 */
	display: block; /* 블록 요소로 설정 */
}

.pager {
	display: inline-block; /* inline-block으로 변경하여 버튼처럼 보이게 함 */
	margin: 0 5px; /* 버튼 간의 간격을 줌 */
}

.pager a {
	text-decoration: none; /* 링크의 기본 밑줄 제거 */
	padding: 10px 15px; /* 버튼 패딩 */
	background-color: #FF0000; /* 버튼 색상 */
	color: white; /* 글자 색상 */
	border-radius: 5px; /* 버튼 모서리 둥글게 */
	margin: 0 10px;
}

.pager a:hover {
	background-color: #d40000; /* 호버 시 색상 변경 */
}

.active a {
	color: white; /* 현재 페이지 글자 색상 */
}

#pageArea {
	display: flex; /* flex 컨테이너로 설정 */
	justify-content: center; /* 가로 방향으로 중앙 정렬 */
	align-items: center; /* 세로 방향으로 중앙 정렬 */
	margin-top: 50px; /* 상단 여백 */
}
.genop {
    margin-bottom: 30px;
    display: flex; /* 플렉스 레이아웃으로 설정 */
    align-items: center; /* 세로 방향으로 중앙 정렬 */
}

.genop input[type='text'] {
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc; /* 테두리 색상 */
    margin-right: 10px; /* 오른쪽 여백 */
    flex: 1; /* 남은 공간을 차지하도록 설정 */
}

.genop select {
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc; /* 테두리 색상 */
    margin-right: 10px; /* 오른쪽 여백 */
}

.genop input[type='submit']  {
    background-color: #FF0000; /* 버튼 색상 */
    color: white; /* 글자 색상 */
    border: none; /* 테두리 제거 */
    padding: 10px 20px; /* 패딩 */
    border-radius: 5px; /* 둥글게 */
    cursor: pointer; /* 포인터 커서 */
    font-size: 1rem; /* 폰트 크기 */
    margin-right:5px;
}

.genop input[type='submit']:hover {
    background-color: #d40000; /* 호버 시 색상 변경 */
}

.manager{
 background-color: #FF0000; /* 버튼 색상 */
    color: white; /* 글자 색상 */
    border: none; /* 테두리 제거 */
    padding: 10px 20px; /* 패딩 */
    border-radius: 5px; /* 둥글게 */
    cursor: pointer; /* 포인터 커서 */
    font-size: 1rem; /* 폰트 크기 */
    margin-right:5px;
}

.manager:hover {
    background-color: #d40000; /* 호버 시 색상 변경 */
}

</style>

</head>
<body>
	<%@include file="../../includes/top.jsp"%>
	<div>
		<section class="upcoming-meetings">
			<div class="container">
				
				<br> <br> <br>
				<form action="${contextPath}/movieMore.do" method="get"
					class="genop">
					<input type='text' name='search' placeholder="제목 검색" value="${param.search}">

					<select id="genre" name="genNo">
						<option value="">-- 장르 선택 --</option>
						<c:forEach var="gen" items="${genList}">
							<option value="${gen.genNo}"
								<c:if test="${gen.genNo == param.genNo}">selected</c:if>>${gen.genName}</option>
						</c:forEach>
					</select> 
					<input type="submit" value="검색">
					<%-- <c:if test="${not empty member && member.memCode == 3}">
					<input type="button" class="manager" value="등록">
				</c:if> --%>
				</form>

				<ul class="movie-list">
					<c:forEach var="movie" items="${movieList}">
						<li class="movie-item">
							<a href="movieDetail.do?movie_no=${movie.movieNo}"> 
								<img src="${movie.moviePoster != null ? movie.moviePoster : 'https://as2.ftcdn.net/v2/jpg/02/51/95/53/1000_F_251955356_FAQH0U1y1TZw3ZcdPGybwUkH90a3VAhb.jpg'}"
								alt="${movie.movieName}"> 
								<span>${movie.movieName}</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>

			<!-- 페이징 처리 -->
			<div id='pageArea'>
				<ul class="pager">
					<li id="prev"><a href="#">Prev</a></li>
				</ul>
				<ul class="pagination pager">
					<%
                        int cPage = (int)request.getAttribute("cPage");
                        int sPage = (int)request.getAttribute("sPage");
                        int ePage = (int)request.getAttribute("ePage");

                        for(int i=sPage; i<=ePage; i++){
                            if(cPage == i){
                    %>
					<li class="active"><a href="#"><%=i %></a></li>
					<%
                            } else {
                    %>
					<li><a href="#"><%=i %></a></li>
					<%
                            }
                        }
                    %>
				</ul>
				<ul class="pager">
					<li id="next"><a href="#">Next</a></li>
				</ul>
			</div>
		</section>
	</div>
</body>
<script>
    $(document).ready(function() {
        $('.pagination li').on('click', function() {
            let pageNum = $(this).text();
            if (pageNum) {
                location.href = "${contextPath}/movieMore.do?page=" + pageNum + "&search=${param.search}&genNo=${param.genNo}";
            }
        });

        $('#next').on('click', function() {
            let currentPage = <%= request.getAttribute("cPage") %>;
            let totalPages = <%= request.getAttribute("tPage") %>;
            
            // 다음 페이지 그룹의 시작과 끝 페이지 계산
            let nextStartPage = Math.floor((currentPage - 1) / 5) * 5 + 6; // 다음 페이지 그룹의 시작
            let nextEndPage = Math.min(nextStartPage + 4, totalPages); // 다음 페이지 그룹의 끝

            if (nextStartPage <= totalPages) {
                location.href = "${contextPath}/movieMore.do?page=" + nextStartPage + "&search=${param.search}&genNo=${param.genNo}";
            }
        });

        $('#prev').on('click', function() {
            let currentPage = <%= request.getAttribute("cPage") %>;
            let previousStartPage = Math.floor((currentPage - 1) / 5) * 5; // 이전 페이지 그룹의 시작

            if (previousStartPage > 0) {
                location.href = "${contextPath}/movieMore.do?page=" + previousStartPage + "&search=${param.search}&genNo=${param.genNo}";
            }
        });

    });


    $('.manager').on('click', function() {
        location.href = "${contextPath}/insertMovie.do";
    });


</script>
</html>
