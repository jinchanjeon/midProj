<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>영화 예약 페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootStrap/css/theaterSelect.css">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/reserve.css">
<link rel="stylesheet" href="css/header.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel='stylesheet'
	href='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css' />
<script
	src='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js'></script>
<link rel="stylesheet"
	href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

</head>

<body>

	<div class="reserve-container">
		<div class="movie-part">
			<div class="reserve-title">영화</div>
			<ul id="movieList" class="movie-list"></ul>
		</div>
		<div class="theater-part">
			<div class="reserve-title">극장</div>
			<div class="list-container">
				<!-- 추가된 div -->
				<ul id="regionList" class="region-list"></ul>
				<!-- 지역 목록 -->
				<ul id="theaterList" class="theater-list"></ul>
				<!-- 극장 목록 -->
			</div>
		</div>

		<div class="day-part">
			<div class="reserve-title">날짜</div>
			<div class="reserve-date"></div>
		</div>
		<div class="time-part">
			<div class="reserve-title">스케줄</div>
			<ul id="scheduleList" class="schedule-list"></ul>
			<!-- 스케줄 목록 -->
		</div>
	</div>
	<!-- 예약하기 버튼 -->
	<div class="reserve-button-container">
		<button id="reserveButton">예약하기</button>
	</div>


	<script>
        let selectedMovie = ''; // 선택된 지역
        let selectedRegion = ''; // 선택된 지역
        let selectedTheater = ''; // 선택된 극장
        let selectedDate = ''; // 선택된 날짜
        let selectedSchedule = ''; // 선택된 스케줄
        let selectedDateFormatted = ''; // 선택된 날짜 포맷 변수 초기화


        $(document).ready(function () {
            loadMovies(); // 페이지 로드 시 영화 목록 가져오기
            loadRegions(); // 페이지 로드 시 지역 목록 가져오기
            
            // URL에서 movieNo 가져오기
            const urlParams = new URLSearchParams(window.location.search);
            const movieNoFromUrl = urlParams.get('movieNo');

         // 영화 목록 가져오기
            function loadMovies() {
                $.ajax({
                    url: 'getMovieList.do', // 영화 목록을 가져오는 서블릿
                    method: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        let movieHtml = '';
                        if (data.length === 0) {
                            movieHtml = "<p>현재 선택 가능한 영화가 없습니다.</p>";
                        } else {
                            data.forEach(function(movie, index) {
                                movieHtml += '<li class="movie-item" data-movie-no="' + movie.movieNo + '">' + movie.movieName + '</li>';
                            });
                        }
                        $("#movieList").html(movieHtml);

                        // URL에서 가져온 movieNo와 일치하는 항목 선택
                        if (movieNoFromUrl) {
                            $('.movie-item').each(function () {
                                if ($(this).data('movie-no') === movieNoFromUrl) {
                                    $(this).addClass('selected'); // 해당 영화 선택
                                    selectedMovie = movieNoFromUrl; // 선택된 영화 번호 저장
                                }
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('영화 목록을 가져오는 데 실패했습니다.');
                    }
                });
            }

            // 선택된 영화에 대한 이벤트 리스너 추가
            $(document).on('click', '.movie-item', function() {
                $('.movie-item').removeClass('selected'); // 기존 선택 해제
                $(this).addClass('selected'); // 클릭한 영화 선택
                selectedMovie = $(this).data('movie-no'); // 선택된 영화 번호 저장
                console.log("Selected Movie: ", selectedMovie); // 추가된 로그
                loadSchedules(); // 스케줄 로드

            });


            
            // 지역 목록 가져오기
            function loadRegions() {
                $.ajax({
                    url: 'getRegions.do',  // 지역 목록을 가져오는 서블릿
                    method: 'GET',
                    dataType: 'json',
                    success: function (data) {
                        let regionHtml = '';
                        data.forEach(function (region) {
                            regionHtml += '<li class="region-item" data-regno="' + region.regNo + '">' + region.regName + '</li>';
                        });
                        $("#regionList").html(regionHtml);  // 지역 목록 업데이트

                        // 첫 번째 지역을 자동으로 클릭하여 극장 목록 로드
                        if (data.length > 0) {
                            $(".region-item").first().click(); // 첫 번째 지역을 클릭
                        }
                    },
                    error: function (xhr, status, error) {
                        alert('지역 목록을 가져오는 데 실패했습니다.');
                    }
                });
            }

            // 지역 클릭 시 극장 목록 가져오기
            $(document).on('click', '.region-item', function () {
                selectedRegion = $(this).data('regno'); // 선택된 지역 저장
                loadTheaters(selectedRegion);
                $('.region-item').removeClass('selected'); // 기존 선택 해제
                $(this).addClass('selected'); // 클릭한 지역 선택
            });

            // 극장 목록 가져오기
            function loadTheaters(regNo) {
                $.ajax({
                    url: 'getTheatersByRegion.do',
                    method: 'GET',
                    data: { regNo: regNo },
                    dataType: 'json',
                    success: function (data) {
                        let theaterHtml = '';
                        data.forEach(function (theater) {
                            theaterHtml += '<li class="theater-item" data-spotcode="' + theater.spotCode + '">' + theater.spotName + '</li>';
                        });
                        $("#theaterList").html(theaterHtml);  // 극장 목록 업데이트
                    },
                    error: function (xhr, status, error) {
                        alert('극장 목록을 가져오는 데 실패했습니다.');
                    }
                });
            }

            // 극장 클릭 시 선택 저장
            $(document).on('click', '.theater-item', function () {
                selectedTheater = $(this).data('spotcode'); // 선택된 극장 저장
                $('.theater-item').removeClass('selected'); // 기존 선택 해제
                $(this).addClass('selected'); // 클릭한 극장 선택
                console.log("Selected Theater: ", selectedTheater); // 추가된 로그
                loadSchedules(); // 스케줄 로드

            });

         // 날짜 선택 기능
            const date = new Date();
            const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
            const reserveDate = $(".reserve-date");

            const weekOfDay = ["일", "월", "화", "수", "목", "금", "토"];
            const year = date.getFullYear();
            const month = date.getMonth();

            for (let i = date.getDate(); i <= lastDay.getDate(); i++) {
                const button = $("<button></button>");
                const spanWeekOfDay = $("<span class='movie-week-of-day'></span>");
                const spanDay = $("<span class='movie-day'></span>");

                const dayOfWeek = weekOfDay[new Date(year, month, i).getDay()];
                spanWeekOfDay.text(dayOfWeek);
                spanDay.text(i);

             // 요일에 따라 클래스 추가
                if (dayOfWeek === "토") {
                    button.addClass("saturday");
                } else if (dayOfWeek === "일") {
                    button.addClass("sunday");
                }
                
                button.append(spanWeekOfDay).append(spanDay).addClass("movie-date-wrapper");

                // 클릭 이벤트
                button.click(function () {
                    selectedDate = new Date(year, month, i); // 선택된 날짜 객체 저장

                    // JavaScript에서 포맷팅
                    const formattedDate = selectedDate.getFullYear() + '/' + 
                                          String(selectedDate.getMonth() + 1).padStart(2, '0') + '/' + 
                                          String(selectedDate.getDate()).padStart(2, '0');
                    selectedDateFormatted = formattedDate; // 포맷된 날짜를 변수에 저장
                    console.log("Selected Date: ", selectedDateFormatted); // 추가된 로그
                    $(".movie-date-wrapper").removeClass("selected"); // 기존 선택 해제
                    $(this).addClass("selected"); // 클릭한 날짜 선택
                    
                    loadSchedules(); // 선택한 날짜로 스케줄 로드

                });

                reserveDate.append(button);
            }
         // 스케줄 로드 함수
           function loadSchedules() {
		    if (!selectedMovie || !selectedDate || !selectedTheater) {
		        console.log("Skipping schedule load: Missing one or more parameters.");
		        return; // 선택이 모두 되어야 스케줄 로드
		    }
		    console.log("Loading schedules with: ", {
		        movieNo: selectedMovie,
		        schDate: selectedDateFormatted,
		        theater: selectedTheater
		    });
		
		    $.ajax({
		        url: 'getSchedules.do', // 스케줄을 가져오는 서블릿
		        method: 'GET',
		        data: {
		            movieNo: selectedMovie,  // 선택된 영화 번호
		            schDate: selectedDateFormatted, // 선택된 날짜
		            spotCode: selectedTheater // 선택된 극장
		        },
		        dataType: 'json',
		        success: function(data) {
		            let scheduleHtml = '';
		            data.forEach(function(schedule) {
		                console.log("Schedule Data: ", schedule); // 추가된 로그

		             // 남은 좌석 수 계산
		                const availableSeats = schedule.totalSeats - schedule.soldOutSeats;
		                
// 		            	console.log(schedule); // 각 스케줄 객체 확인
		                scheduleHtml += `
		                    <li>
		                	<span class="room-name">` + schedule.roomName + `</span> (총 ` + schedule.totalSeats + `석)<br>
		                        <button class="schedule-select" 
		                            data-schedule-no="` + schedule.schNo + `" 
		                            data-end-time="` + schedule.movieEndTime + `">` + schedule.movieStartTime + `</button>
		                        ` + availableSeats + `석
		                    </li>`;
		            });
		            $("#scheduleList").html(scheduleHtml); // 스케줄 목록 업데이트
		         
		            // 마우스 오버 이벤트 리스너 추가
		            $(".schedule-select").hover(
		                function() {
		                    // 마우스가 버튼 위에 올라왔을 때
		                    const endTime = $(this).data("end-time");
		                    $(this).attr("title", "종료 시간: " + endTime);
		                }
		            );
		        },
		        error: function(xhr, status, error) {
		            alert('스케줄을 가져오는 데 실패했습니다.');
		        }
		    });
		    $(document).on('click', '.schedule-select', function() {
		        // 기존에 선택된 버튼이 있으면 selected 클래스를 제거
		        $('.schedule-select').removeClass('selected');
		        
		        // 클릭한 버튼에 selected 클래스 추가
		        $(this).addClass('selected');

		        // 선택된 스케줄 번호 저장
		        selectedSchedule = $(this).data('schedule-no');
		        console.log('Selected Schedule No: ' + selectedSchedule); // 콘솔 출력
		    });
		}
        // 예약하기 버튼 클릭 시 좌석 선택 페이지로 데이터 전송
           $("#reserveButton").click(function () {
        	   
        	   console.log("Selected Region: " + selectedRegion);
        	    console.log("Selected Theater: " + selectedTheater);
        	    console.log("Selected Date: " + selectedDate);
        	    console.log("Selected Schedule: " + selectedSchedule);
               if (!selectedRegion || !selectedTheater || !selectedDate || !selectedSchedule) {
                   alert("지역, 극장, 날짜, 스케줄을 모두 선택해야 합니다.");
                   return;
               }

               // AJAX 요청을 통해 데이터 전송
               $.ajax({
                   url: 'getSeats.do', // 요청할 서블릿 경로
                   method: 'GET', // GET으로 변경
                   data: {
                       regNo: selectedRegion,
                       spotCode: selectedTheater,
                       date: selectedDate,
                       scheduleNo: selectedSchedule // 선택한 스케줄 번호 추가
                   },
                   dataType: 'html', // 응답으로 HTML을 기대
                   success: function (data) {
                       // 응답으로 받은 HTML을 원하는 DOM 요소에 삽입
                        $('.reserve-container').html(data); // 예를 들어, '#yourContainer' 요소에 삽입
                        $("#reserveButton").hide(); // 예약하기 버튼 숨기기
                   },
                   error: function (xhr, status, error) {
                       alert('좌석 선택 페이지를 불러오는 데 실패했습니다.');
                   }
               });
           });
        })
        

    </script>
</body>
</html>
