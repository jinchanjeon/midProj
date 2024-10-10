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
.board-table {
	width: 80%;
	border-collapse: collapse;
	margin: 0 auto;
}

.board-table th, .board-table td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
	color: white;
}

.board-table th {
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
		<div >
			<table class="board-table">
				<thead>
					<tr>
						<th>순번</th>
						<th>문의 제목</th>
						<th>문의 내용</th>
						<th>문의 날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty boardVo}">
						<c:forEach var="board" items="${boardVo}" varStatus="status">
						<c:if test="${board.board_state != 2}">
							<tr>
								<td>${status.index + 1}</td>
								<td>${board.board_title}</td>
								<td>${board.board_content}</td>
								<td>${board.board_write_date}</td>
								<td class="no-border">
									<button onclick="edit('${board.board_no}','${board.board_code}','${board.board_title}')">수정</button>
									<button onclick="deleteReview('${board.board_no}')">삭제</button>
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
					<form id="boardForm">
						<input type="hidden" name="board_no" value="${board.board_no}">
						<label for="boardContent">내용</label>
						<textarea id="boardContent" placeholder="문의 내용을 입력하세요"
							name="boardContent"></textarea>
					    <input type="button" id="submitButton" value="등록"
							onclick="submitReview('${board.board_no}')">
						<div class="close-button" onclick="closeModal()">닫기</div>
					</form>
				</div>
			</div>
	</section>
	<!-- jQuery 및 스크립트 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		crossorigin="anonymous"></script>

	<script>
    // 문의 작성 모달 열기
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
 // 문의 등록 함수
    function submitReview(board_no, boardCode, boardTitle) {
        const boardContent = document.getElementById("boardContent").value;
  
        
        $.ajax({
            url: "${contextPath}/myboardInsert.do", 
            type: "POST",
            data: {
                boardNo: board_no,
                boardCode: boardCode,
                boardTitle: boardTitle,
                boardContent: boardContent
            },
            success: function() {
                alert("문의사항이 등록되었습니다.");
                location.reload();
            },
            error: function() {
                alert("문의사항 등록 실패!");
            }
        });
        closeModal();
    }
    // 문의 삭제
    function deleteReview(board_no) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                url: "${contextPath}/myboardDelete.do",
                type: "POST",
                data: { boardNo: board_no },
                success: function() {
                    alert("문의사항이 삭제되었습니다.");
                    location.reload();
                },
                error: function() {
                    alert("문의사항 삭제 실패!");
                }
            });
        }
    }

    // 문의 수정
    function edit(board_no, boardCode, boardTitle) {
        $.ajax({
            url: "${contextPath}/getmyboard.do",
            type: "GET",
            data: { boardNo: board_no,
            		boardCode: boardCode,
            		boardTitle: boardTitle},
            
            success: function(response) {
                document.getElementById("boardContent").value = response.board_content;

                document.getElementById("submitButton").value = "수정";
                document.getElementById("submitButton").onclick = function() {
                    updateReview(board_no, boardCode, boardTitle);
                };
          
                modal.style.display = "flex";
            },
            error: function() {
                alert("문의사항을 불러오는데 실패했습니다.");
            }
        });
    }
    

    // 문의 수정 함수
    function updateReview(board_no, boardCode, boardTitle) {
        const boardContent = document.getElementById("boardContent").value;

        $.ajax({
            url: "${contextPath}/myboardUpdate.do",
            type: "POST",
            data: {
            	boardNo: board_no,
            	boardContent: boardContent,
            	boardCode: boardCode,
            	boardTitle: boardTitle
            	},
            success: function() {
                alert("문의사항이 수정되었습니다.");
                location.reload();
            },
            error: function() {
                alert("문의사항 수정 실패!");
            }
        });
    }
    </script>
</body>
</html>