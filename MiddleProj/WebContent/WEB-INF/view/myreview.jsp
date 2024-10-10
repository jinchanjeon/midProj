<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	width: 80%; /* 너비를 줄임 */
	border-collapse: collapse;
	margin: 0 auto; /* 페이지 가운데 정렬 */
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

.no-border {
	border: none !important;
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

	<section class="upcoming-meetings">
		<div>
			<table class="review-table">
				<thead>
					<tr>
						<th>순번</th>
						<th>영화 제목</th>
						<th>내용</th>
						<th>등록 날짜</th>
						<th>평점</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty myreview}">
						<c:forEach var="review" items="${myreview}" varStatus="status">
							<c:if test="${review.reviewState != 2}">
								<tr>
									<td>${status.index + 1}</td>
									<td>${review.movieName}</td>
									<td>${review.reviewContent}</td>
									<td>${review.reviewDate}</td>
									<td>${review.reviewStar}</td>
									<td class="no-border">
										<button onclick="edit('${review.reviewCode}')">수정</button>
										<button onclick="deleteReview('${review.reviewCode}')">삭제</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<%@include file="../../includes/bottom.jsp"%>

		<!-- 리뷰 작성 모달 -->
		<div class="modal">
			<div class="modal-body">
				<form id="reviewForm">
					<input type="hidden" name="memNo" value="${memNo}"> <input
						type="hidden" name="movieNo" value="${memberReview.movieNo}">
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
						onclick="submitReview('${memNo}', '${memberReview.movieNo}')">
					<div class="close-button" onclick="closeModal()">닫기</div>
				</form>
			</div>
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
    </script>
</body>
</html>