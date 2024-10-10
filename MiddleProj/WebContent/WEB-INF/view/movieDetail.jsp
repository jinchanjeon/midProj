<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="member" value="${sessionScope.member}"></c:set>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
section.heading-page {
	padding-top: 200px !important;
}

body {
	background-color: black !important;
	color: white !important;
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

#videoPlayer {
	margin-top: 100px;
	width: 80%;
	margin-bottom: 100px;
	margin-left: 0px;
}

.no-border {
	border: none !important;
}

.info {
	width: 90%;
	margin: 0 auto; /* Adjusted from 20px to 0 to remove space */
	display: flex;
	flex-wrap: wrap;
	justify-content: flex-start;
	align-items: flex-start;
}

.movie-details {
	flex: 1 1 28%;
	margin-bottom: 0;
}

.movie-details img {
	width: 50% !important;
	max-width: 300px;
	border-radius: 10px;
	display: block;
	margin: 0 auto; /* Keep this to center the image */
}

.movie-info h2 {
	font-size: 2rem;
	margin-bottom: 10px;
}

.movie-info p {
	margin: 5px 0;
	color: white !important;
}

.movie-info {
	flex: 1 1 28%; 
	text-align: left;
	margin-bottom: 20px;
	margin-top: 100px;
	margin-right: 0px;
	margin-left: 200px;
}

/* .genre, .actor, .director {
	display: inline-block;
	padding: 5px;
	background-color: #444 !important;
	border-radius: 5px;
	margin-right: 5px;
	font-size: 0.9rem;
}
 */
.button-container {
	text-align: center;
	margin-top: 20px;
}

button {
	background-color: #FF0000 !important;
	color: white;
	border: none;
	padding: 6px 10px;
	cursor: pointer;
	border-radius: 5px;
	font-size: 1rem;
}

button:hover {
	background-color: #d40000 !important;
}

.manager {
	background-color: #FF0000 !important;
	color: white;
	border: none;
	padding: 6px 10px;
	cursor: pointer;
	border-radius: 5px;
	font-size: 1rem;
}

.manager :hover {
	background-color: #d40000 !important;
}

.video-player {
	width: 100%;
	margin: 0 auto;
}

.review-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 30px;
}

.review-table th, .review-table td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
}

.review-table th {
	background-color: #333;
	color: white;
}

.modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.8) !important;
	justify-content: center;
	align-items: center;
}

.modal-body {
	background-color: #222;
	padding: 20px;
	border-radius: 10px;
	max-width: 400px; /* 원하는 너비로 수정 */
	width: 90%; /* 모바일 화면에 맞춰 반응형으로 조정 */
}

.modal-body form {
	display: flex;
	flex-direction: column;
}

.modal-body label {
	margin-top: 10px;
}

.modal-body textarea {
	height: 100px;
}

.close-button {
	background-color: #444 !important;
	color: white;
	padding: 5px;
	margin-top: 10px;
	text-align: center;
	cursor: pointer;
}

.close-button:hover {
	background-color: #666 !important;
}
</style>
</head>
<body>
	<%@include file="../../includes/top.jsp"%>
	<section class="heading-page">
		<div class="info">
			<!-- 영화 포스터 및 제목 -->
			<div class="movie-details">
				<c:if test="${not empty member && member.memCode == 3}">
					<button class="manager" id="updateButton">수정</button>
				</c:if>

				<input type="hidden" id="movieNo" value="${movieDetail.movieNo}">
				<h2>${movieDetail.movieName}</h2>
				<img src="${movieDetail.moviePoster}" alt="Movie Poster">
				<div class="button-container">
					<button class="gotores">예매하기</button>
				</div>
			</div>

			<!-- 영화 정보 -->
			<div class="movie-info">
				<p>배급사: ${movieDetail.movieCompany}</p>
				<p>
					장르:
					<c:forEach var="gen" items="${genreList}" varStatus="status">
						<span class="genre">${gen.genName}</span>
						<c:if test="${!status.last}">, </c:if>
					</c:forEach>
				</p>
				<p>
					출연진:
					<div>
					<c:forEach var="act" items="${actorList}" varStatus="status">
						
						<span class="actor">${act.actName}</span>
						<c:if test="${!status.last}">, </c:if>
					</c:forEach>
				</p>
				<p>
					감독:
					<c:forEach var="dir" items="${DirectorList}" varStatus="status">
						<span class="director">${dir.dirName}</span>
						<c:if test="${!status.last}">, </c:if>
					</c:forEach>
				</p>
				<p>개봉일: ${movieDetail.movieRelease}</p>
				<p>상영시간: ${movieDetail.movieRunningtime}분</p>
				<p>관람 등급: ${movieDetail.movieLevel}</p>
				<p>상태: ${movieDetail.movieState}</p>
				<p>평점: ${review }</p>
				
			</div>

			
		</div>
<div class="movie-info">
				<h4>추천 영화</h4><br>
				<ul>
					<c:forEach var="recommendedMovie" items="${recommendedMovies}"
						varStatus="status">
						<a href="movieDetail.do?movie_no=${recommendedMovie.movieNo}">${recommendedMovie.movieName}</a>
						<c:if test="${!status.last }">,</c:if>
					</c:forEach>
				</ul>
			</div>


		<!-- 동영상 플레이어 -->
		<section class="container">
			<div class="video-player">
				<c:set var="movieTrailer" value="${movieDetail.movieTrailer}" />
				<c:set var="urlhead"
					value="${fn:substringBefore(movieTrailer, 'trailerPlayPop')}play/" />
				<c:set var="urlbody"
					value="${fn:substringAfter(movieTrailer, 'pFileNm=')}" />
				<c:set var="url" value="${urlhead}${urlbody}" />
				<video id="videoPlayer" preload="metadata" autoplay muted loop
					controls width="30%">
					<source src="${url}">
				</video>

			</div>
			<button class="btn-open-modal" onclick="checkLogin()">리뷰
				작성하기</button>
			<!-- 리뷰 테이블 -->

			<div class="container">
				<table class="review-table">
					<thead>
						<tr>
							<th>회원아이디</th>
							<th>내용</th>
							<th>등록 날짜</th>
							<th>평점</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="review" items="${reviewList}">
							<tr>
								<td>${review.memId}</td>
								<td>${review.reviewContent}</td>
								<td>${review.reviewDate}</td>
								<td>${review.reviewStar}</td>
								<c:choose>
									<c:when test="${not empty member}">
										<c:if test="${member.memId eq review.memId}">
											<td class="no-border">
												<button onclick="edit('${review.reviewCode}')">수정</button>
												<button onclick="deleteReview('${review.reviewCode}')">삭제</button>
											</td>

										</c:if>
									</c:when>
								</c:choose>


							</tr>
						</c:forEach>
					</tbody>
				</table>
		</section>

		<!-- 리뷰 작성 모달 -->
		<c:if test="${not empty member}">
			<div class="modal">
				<div class="modal-body">
					<form id="reviewForm">
						<input type="hidden" name="memNo" value="${member.memId}">
						<input type="hidden" name="movieNo" value="${movieDetail.movieNo}">
						<label for="reviewContent">내용</label>
						<textarea id="reviewContent" placeholder="리뷰 내용을 입력하세요"
							name="reviewContent"></textarea>
						<label for="reviewStar">평점</label> <select id="reviewStar"
							name="reviewStar">
							<option value="1">1점</option>
							<option value="2">2점</option>
							<option value="3">3점</option>
							<option value="4">4점</option>
							<option value="5">5점</option>
						</select> <input type="button" id="submitButton" value="등록"
							onclick="submitReview('${member.memNo}', '${movieDetail.movieNo}')">
						<div class="close-button" onclick="closeModal()">닫기</div>
					</form>
				</div>
			</div>
		</c:if>
		</div>
	</section>

	<!-- jQuery 및 스크립트 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		crossorigin="anonymous"></script>

	<script>
    // 리뷰 작성 모달 열기
const modal = document.querySelector('.modal');
const btnOpenModal = document.querySelector('.btn-open-modal');

//모달 열기
btnOpenModal.addEventListener("click", () => {
 modal.style.display = "flex";
});


   modal.addEventListener("click", (event) => {
        if (event.target === modal) {
            closeModal();
        }
    }); 

    function closeModal() {
        modal.style.display = "none";
    }

    // 리뷰 등록 함수
    function submitReview(memNo, movieNo) {
        const content = document.getElementById("reviewContent").value;
        const reviewStar = document.getElementById("reviewStar").value;
        
        $.ajax({
            url: "${contextPath}/insertReview.do",
            type: "POST",
            data: {
                reviewContent: content,
                memNo: memNo,
                movieNo: movieNo,
                reviewStar: reviewStar
            },
            success: function() {
                alert("리뷰가 등록되었습니다.");
                location.reload();
            },
            error: function() {
                alert("리뷰 등록 실패!");
            }
        });
        closeModal();
    }

    // 로그인 여부 확인 후 리뷰 작성 모달 열기
    function checkLogin() {
        const isLoggedIn = "${not empty member}";
        if (isLoggedIn === "true") {
            modal.style.display = "flex";
        } else {
            alert("로그인 후 이용해주세요.");
            location.href = "${contextPath}/login.do";
        }
    }

    // 리뷰 삭제
    function deleteReview(reviewCode) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                url: "${contextPath}/deleteReview.do",
                type: "POST",
                data: { reviewCode: reviewCode },
                success: function() {
                    alert("리뷰가 삭제되었습니다.");
                    location.reload();
                },
                error: function() {
                    alert("리뷰 삭제 실패!");
                }
            });
        }
    }

    // 리뷰 수정
    function edit(reviewCode) {
        $.ajax({
            url: "${contextPath}/getReview.do",
            type: "GET",
            data: { reviewCode: reviewCode },
            success: function(response) {
                document.getElementById("reviewContent").value = response.reviewContent;
                document.getElementById("reviewStar").value = response.reviewStar;

                document.getElementById("submitButton").value = "수정";
                document.getElementById("submitButton").onclick = function() {
                    updateReview(reviewCode);
                };

                modal.style.display = "flex";
            },
            error: function() {
                alert("리뷰 정보를 불러오는데 실패했습니다.");
            }
        });
    }

    // 리뷰 수정 함수
    function updateReview(reviewCode) {
        const content = document.getElementById("reviewContent").value;
        const reviewStar = document.getElementById("reviewStar").value;

        $.ajax({
            url: "${contextPath}/updateReview.do",
            type: "POST",
            data: {
                reviewCode: reviewCode,
                reviewContent: content,
                reviewStar: reviewStar
            },
            success: function() {
                alert("리뷰가 수정되었습니다.");
                location.reload();
            },
            error: function() {
                alert("리뷰 수정 실패!");
            }
        });
    }

    // 예매하기
  /*   function reservation() {
        location.href = "${contextPath}/reservation.do?movie_no="+${movieNo};
    } */
    
    $('.gotores').on('click',function(){
    	 const isLoggedIn = "${not empty member}"; // 세션에서 로그인 상태 확인
    	    if (isLoggedIn === "true") {
    	        location.href = "${contextPath}/theaterSelect.do"; // 로그인 상태이면 예매 페이지로 이동
    	    } else {
    	        alert("로그인 후 이용해주세요."); // 로그인 안 되어 있으면 알림
    	        location.href = "${contextPath}/login.do"; // 로그인 페이지로 이동
    	    }
    })
    
 $('#updateButton').on('click', function() {
    location.href = "${contextPath}/getMovie.do?movieNo=" + $('#movieNo').val();
});


        
</script>