<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<c:set var="member" value="${sessionScope.member}"></c:set>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="TemplateMo">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900"
	rel="stylesheet">

<title>부귀를 담다, BoogiMovie</title>

<!-- Bootstrap core CSS -->
<link
	href="${contextPath }/resources/boogie/edu-meeting/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Additional CSS Files -->
<link rel="stylesheet"
	href="${contextPath }/resources/boogie/edu-meeting/assets/css/fontawesome.css">
<link rel="stylesheet"
	href="${contextPath }/resources/boogie/edu-meeting/assets/css/templatemo-edu-meeting.css">
<link rel="stylesheet"
	href="${contextPath }/resources/boogie/edu-meeting/assets/css/owl.css">
<link rel="stylesheet"
	href="${contextPath }/resources/boogie/edu-meeting/assets/css/lightbox.css">
<link rel="stylesheet"
	href="${contextPath }/resources/boogie/edu-meeting/assets/css/login.css">

</head>

<body>
	<!-- ***** Header Area Start ***** -->
	<header class="header-area header-sticky">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="main-nav">
					
						<!-- ***** Logo Start ***** -->
						<a href="${contextPath }/main.do" class="logo"> Boogie Movie </a>
						<!-- ***** Logo End ***** -->
						
						<!-- ***** Menu Start ***** -->
						<ul class="nav">
							<li><a href="${contextPath }/movieList.do">영화</a></li>
							<li><a href="${contextPath }/eventList">이벤트</a></li>
							<li><a href="${contextPath }/store.do">스토어</a></li>
							<li><a href="${contextPath }/board.do">게시판</a></li>
							<c:choose>
								<c:when test="${not empty member}">
									<li><a href="${contextPath}/mypage.do">마이페이지</a></li>
									<li><a href="${contextPath}/storeCart.do">장바구니</a></li>
									<li><a href="${contextPath}/logout.do">로그아웃</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/login.do">로그인</a></li>
								<li><a href="${contextPath}/join.do">회원가입</a></li>
							</c:otherwise>
							</c:choose>
						</ul>

				<!-- ***** Menu End ***** -->
				</nav>
			</div>
		</div>
		</div>
	</header>

	<!-- 기타 스크립트 -->
	<script
		src="${contextPath }/resources/boogie/edu-meeting/vendor/jquery/jquery.min.js"></script>
		
<!-- 		파일 없다는 경고문 뜸 -->
	<script
		src="${contextPath }/resources/boogie/edu-meeting/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		
<%-- 스크립트 오류발생으로 주석처리함 23.10.4 --%>
	<%-- <script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/custom.js"></script> --%>
		
<%--  <script src="${contextPath }/resources/boogie/edu-meeting/assets/js/script.js"></script> --%>

	<!-- ***** Header Area End ***** -->
	
		
		<!-- 		파일 없다는 경고문 뜸 -->
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/isotope.min.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/owl-carousel.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/lightbox.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/tabs.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/video.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/slick-slider.js"></script>
	<script>
    //according to loftblog tut
    $('.nav li:first').addClass('active');

    var showSection = function showSection(section, isAnimate) {
      var
        direction = section.replace(/#/, ''),
        reqSection = $('.section').filter('[data-section="' + direction + '"]'),
        reqSectionPos = reqSection.offset().top - 0;

      if (isAnimate) {
        $('body, html').animate({
          scrollTop: reqSectionPos
        },
          800);
      } else {
        $('body, html').scrollTop(reqSectionPos);
      }

    };

    var checkSection = function checkSection() {
      $('.section').each(function () {
        var
          $this = $(this),
          topEdge = $this.offset().top - 80,
          bottomEdge = topEdge + $this.height(),
          wScroll = $(window).scrollTop();
        if (topEdge < wScroll && bottomEdge > wScroll) {
          var
            currentId = $this.data('section'),
            reqLink = $('a').filter('[href*=\\#' + currentId + ']');
          reqLink.closest('li').addClass('active').
            siblings().removeClass('active');
        }
      });
    };

    $('.main-menu, .responsive-menu, .scroll-to-section').on('click', 'a', function (e) {
      e.preventDefault();
      showSection($(this).attr('href'), true);
    });

    $(window).scroll(function () {
      checkSection();
    });
  </script>
</body>

	