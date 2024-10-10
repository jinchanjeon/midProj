<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manager Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.rq {
	color: red
}

.gender-options {
	display: flex; /* Flexbox 사용 */
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
}

.gender-options input[type="radio"] {
	margin: 10px; /* 라디오 버튼 사이의 간격 */
}

.join {
	background-color: #292929;
	border-radius: 8px;
	padding: 20px;
	width: 400px;
	margin: 0 auto; /* 가운데 정렬 */
}

.form-group {
	margin-bottom: 15px; /* 입력란 간격 */
}

body, html {
	background-color: black !important;
	color: white !important;
	height: 100%; /* 전체 높이에도 적용 */
	margin: 0;
	padding: 0;
}

section, .info, .modal-content {
	background-color: black;
	color: white;
}

.info {
	display: flex;
	justify-content: space-around;
	margin: 20px 0;
}

#memberList, #managerList {
	width: 500px;
	height: 300px;
	border: 1px solid #ddd;
	overflow-y: scroll;
	display: none;
}

p {
	color: white !important;
}

#memberList p, #managerList p {
	margin: 10px 0;
	padding: 0 10px;
}

#memberList hr, #managerList hr {
	border: 0;
	height: 1px;
	background-color: #ccc;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 9999;
	left: 0;
	top: 0;
	margin-top:40px;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	padding-top: 100px; /* Location of the box */
}

.modal-content {
	background-color: #fefefe;
	margin-top: 0 !important; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 80%; /* Could be more or less, depending on screen size */
}

input[type="text"], input[type="password"], input[type="date"], input[type="tel"],
	input[type="email"] {
	width: 100%;
	padding: 10px;
	margin: 5px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
}
</style>
</head>
<body>
	<%@ include file="../../includes/top.jsp"%>

	<section class="heading-page">
		<div class="info">
			<div>
				<h3 class="selmem" data-memcode="1">회원정보 조회</h3>
				<div id="memberList"></div>
			</div>
			<div>
				<h3 class="selmem" data-memcode="2" style="display: inline;">매니저
					조회</h3>
				<button id="addManagerBtn" style="margin-left: 10px;">등록</button>
				<div id="managerList"></div>
			</div>
		</div>
	</section>

	<!-- 모달 -->
	<div id="myModal" class="modal">
		<div class="join">
			<form class="form-horizontal" id="managerForm" method="post">
				<select id="spotCode" required>
					<option value="">-- 지점 선택 --</option>
					<c:forEach var="spot" items="${spotList}">
						<option value="${spot.spotCode}">${spot.spotName}</option>
					</c:forEach>
				</select>
				<div class="form-group">
					<p>
						아이디 <span class="rq">*</span>
					</p>
					<input type="text" class="form-control" id="memId" name="memId"
						placeholder="최소 한 자리 이상 입력하세요." required onblur="trimInput(this)">
				</div>
				<div class="col-sm-6" style="text-align: left;">
					<span id="disp"></span>
				</div>
				<div class="form-group">
					<p>
						비밀번호<span class="rq"> *</span>
					</p>
					<input type="password" class="form-control" id="memPw" name="memPw"
						placeholder="영문자, 숫자, 특수문자 포함 최소 8자 이상" required
						pattern="(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#\$%\^&amp;\*\(\)_\+\|]).{8,}"
						onblur="trimInput(this)">
				</div>
				<div class="form-group">
					<p>
						이름<span class="rq"> *</span></label><span class="sp"></span>
					</p>
					<input type="text" class="form-control" id="memName" name="memName"
						required pattern="[가-힣]{2,30}" onblur="trimInput(this)">
				</div>
				<div class="form-group">
					<p>
						생년월일<span class="rq"> *</span>
					</p>
					<input placeholder="yymmdd형식으로 입력해주세요" type="text" class="form-control" id="memBirth"
						name="memBirth" required>
				</div>
				<div class="form-group">
					<p>
						휴대폰 번호<span class="rq"> *</span>
					</p>
					<input type="text" class="form-control" id="memPhone"
						name="memPhone" placeholder="010-0000-0000" required
						pattern="[0-9]{2,3}-\d{3,4}-\d{4}" onblur="trimInput(this)">
				</div>
				<div class="form-group">
					<p>
						이메일<span class="rq"> *</span>
					</p>
					<input type="email" class="form-control" id="memMail"
						name="memMail" required onblur="trimInput(this)">
				</div>
				<div class="gender-options">
					<input type="radio" id="male" name="memGender" value="male"
						required> <label for="male">남성</label> <input type="radio"
						id="female" name="memGender" value="female" required> <label
						for="female">여성</label>
				</div>
				<hr>
				<button id="submitButton" type="submit">가입하기</button>
				<span id="joinspan"></span>
			</form>
		</div>
	</div>

	<script>
        function trimInput(input) {
            input.value = input.value.replace(/\s+/g, '');
        }

        $(document).ready(function() {
            loadMembers(1);
            loadManagers(2);

            // 모달 열기
            $('#addManagerBtn').on('click', function() {
                resetModal();
                $('#myModal').show();
                $('#confirmPasswordGroup').show(); // 비밀번호 확인란 보이기
            });

            // 모달 외부 클릭 시 닫기
            $(window).on('click', function(event) {
                if ($(event.target).is('#myModal')) {
                    $('#myModal').hide();
                }
            });

            
        $('#managerForm').on('submit', function(e) {
                e.preventDefault(); 
                registerManager(); 
            });
        });
         

        function resetModal() {
            // 모든 입력 필드 초기화
            $('#memId').val('');
            $('#memId').val('').prop('readonly', false);
            $('#memPw').val('');
            $('#memName').val('');
            $('#memBirth').val('');
            $('#memPhone').val('');
            $('#memMail').val('');
            $('input[name="memGender"]').prop('checked', false); 
            $('#submitButton').text("가입하기"); 
            $('#spotCode').val('');
            /* $('#confirmPasswordGroup').show();  */
        }

        function loadMembers() {
            $.ajax({
                url: "${contextPath}/selectMember.do",
                type: "GET",
                dataType: "json",
                success: function(resp) {
                    $('#memberList').empty();
                    resp.forEach(function(member) {
                        $('#memberList').append(
                            '<p>회원 ID: ' + member.memId + '</p>' + 
                            '<p>회원 이름: ' + member.memName + '</p>' +
                            '<hr>'
                        );
                    });
                    $('#memberList').show();
                },
                error: function() {
                    alert("회원 조회에 실패했습니다.");
                }
            });
        }


        function loadManagers() {
            $.ajax({
                url: "${contextPath}/selectManager.do",
                type: "GET",
                dataType: "json",
                success: function(resp) {
                    $('#managerList').empty();
                    resp.forEach(function(manager) {
                        $('#managerList').append(
                        		'<p>지점: '+manager.spotName+'</p>'+
                            '<p>매니저 ID: ' + manager.memId + '</p>' +
                            '<p>매니저 이름: ' + manager.memName + '</p>' +
                            '<div class="button-container">' +
                            '<button onclick="moveToMypage(\'' + manager.memNo + '\')">이동</button>' +
                            '<button onclick="editManager(\'' + manager.memNo + '\')">수정</button>' +
                            '<button onclick="deleteManager(\'' + manager.memId + '\')">삭제</button>' +
                            '</div>' +
                            '<hr>'
                        );
                    });
                    
                    
                    $('#managerList').show();
                },
                error: function() {
                    alert("매니저 조회에 실패했습니다.");
                }
            });
        }

        function moveToMypage(memNo) {
            window.location.href = "${contextPath}/main?memNo=" + memNo;
        }

        function editManager(memNo) {
            $.ajax({
                url: "${contextPath}/getManager.do",
                type: "GET",
                data: { memNo: memNo },
                success: function(response) {
                    $('#memId').val(response.memId).prop('readonly', true);
                    $('#memPw').val(''); 
                    $('#memMail').val(response.memMail);
                    $('#memPhone').val(response.memPhone);
                    $('#memName').val(response.memName);
                    
                    // 생년월일 데이터 변환
                    if (response.memBirth) {
                        const birthParts = response.memBirth.split('-'); // YYYY-MM-DD 형식으로 가정
                        if (birthParts.length === 3) {
                            const year = birthParts[0].slice(-2); // 마지막 두 자리
                            const month = String(birthParts[1]).padStart(2, '0'); // 01 ~ 12
                            const day = String(birthParts[2]).padStart(2, '0'); // 01 ~ 31
                            $('#memBirth').val(`${year}${month}${day}`); // yymmdd 형식으로 설정
                        }
                    }

                    // 기존 선택한 지점 설정
                    $('#spotCode').val(response.spotCode); // 기존 지점 코드 설정
                    $('#spotCode option[value="' + response.spotCode + '"]').prop('selected', true); // 선택된 옵션 설정
                    
                    $('input[name="memGender"][value="' + response.memGender + '"]').prop('checked', true); // 성별 초기화
                    $('#submitButton').text("수정");

                    $('#managerForm').off('submit').on('submit', function(e) {
                        e.preventDefault(); // 기본 제출 방지
                        updateManager(memNo);
                    });

                    $('#myModal').show();
                },
                error: function() {
                    alert("매니저 정보를 불러오는데 실패했습니다.");
                }
            });
        }


        function updateManager(memNo) {
            const managerData = {
            		memNo:memNo,
                memId: $('#memId').val(),
                memPw: $('#memPw').val(), 
                memMail: $('#memMail').val(),
                memPhone: $('#memPhone').val(),
                memName: $('#memName').val(),
                memBirth: $('#memBirth').val(),
                spotCode:$('#spotCode').val(),
                memGender: $('input[name="memGender"]:checked').val()
            };

            $.ajax({
                url: "${contextPath}/updateManager.do",
                type: "POST",
                data: managerData,
                success: function(response) {
                    alert("수정되었습니다.");
                    $('#myModal').hide(); // 모달 닫기
                    loadManagers(2);
                },
                error: function() {
                    alert("수정 실패");
                }
            });
        }

        function deleteManager(memId) {
            if (confirm("정말 삭제하시겠습니까?")) {
                $.ajax({
                    url: "${contextPath}/deleteManager.do",
                    type: "POST",
                    data: { memId: memId },
                    success: function(response) {
                        alert("매니저 삭제되었습니다.");
                        loadManagers(2); // 매니저 리스트 새로고침
                    },
                    error: function() {
                        alert("삭제 실패!");
                    }
                });
            }
        }

        function registerManager() {
            const managerData = {
            	spotCode:$('#spotCode').val(),
                memId: $('#memId').val(),
                memPw: $('#memPw').val(),
                memName: $('#memName').val(),
                spotCode:$('#spotCode').val(),
                memBirth: $('#memBirth').val(),
                memPhone: $('#memPhone').val(),
                memGender: $('input[name="memGender"]:checked').val(),
                memMail: $('#memMail').val()
            };

            $.ajax({
                url: "${contextPath}/insertManager.do", 
                type: "POST",
                data: managerData,
                success: function(response) {
                    alert("매니저 등록이 완료되었습니다.");
                    $('#myModal').hide(); 
                    loadManagers(2);
                },
                error: function() {
                    alert("매니저 등록에 실패했습니다.");
                }
            });
        }

        // ID 중복 확인
        $("#memId").on("blur", function() {
            let $idVal = $("#memId").val();
            if ($idVal.trim() == "") {
                $("#disp").text("아이디를 입력하세요.").css("color", "red");
                return;
            }
            $.ajax({
                url: "idChk.jsp?memId=" + $idVal,
                type: "get",
                success: function(data) {
                    if (data.rst == "ok") {
                        $("#disp").text("사용가능").css("color", "green");
                    } else {
                        $("#disp").text("사용불가").css("color", "red");
                    }
                },
                error: function(xhr) {
                    alert("오류상태: " + xhr.status);
                },
                dataType: "json"
            });
        });
    </script>
</body>
</html>