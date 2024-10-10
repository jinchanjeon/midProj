<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<c:set var="member" value="${sessionScope.member}"></c:set>
<%@page import="java.util.List"%>

<head>
</head>
<style>
h5 {
	z-index: 9999 !important;
}

#event{
	margin-top:50px;
}

.owl-item{
	margin-bottom:40px;
}

#courses{
padding-top:0px !important;
}
</style>
<body>

	<%@include file="../../includes/top.jsp"%>
	<%@include file="../../includes/banner.jsp"%>
	<section class="services">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="owl-service-item owl-carousel">
						<!-- boxoffice -->
						<c:if test="${not empty boxInfo}">
							<c:forEach var="box" items="${boxInfo}">
								<div class="item">
									<div class="icon">
										<img src="${box.moviePoster}" alt="" width="200px"
											height="300px">
									</div>
									<h5>${box.movieName}</h5>
									<div class="down-content"></div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty boxInfo}">
							<p>Box office data is not available.</p>
						</c:if>

					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="upcoming-meetings" id="meetings">
		<div class="container" id="event">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-heading">
						<h2>이벤트 목록</h2>
					</div>
					<a href="${contextPath }/eventList" class="logo"> 
						이벤트 더보기
					</a>
				
				</div>
			</div>
			<div class="col-lg-8">
				<div class="row">
					<div class="col-lg-6">
						<div class="meeting-item">
							<div class="thumb">
								<a href="meeting-details.html"><img
									src="${contextPath }/resources/boogie/edu-meeting/assets/images/스크린.jpg"
									alt="New Lecturer Meeting"></a>
							</div>
							<div class="down-content">
								<div class="date">
									<h6>
										Event <span>01</span>
									</h6>
								</div>
								<a href="meeting-details.html">
									<h4>[특가] ScreenX 관람팩 판매</h4>
								</a>
								<p>3면 스크린을 스페셜 특가로 만나다!</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="meeting-item">
							<div class="thumb">
								<a href="meeting-details.html"><img
									src="${contextPath }/resources/boogie/edu-meeting/assets/images/imax.jpg"
									alt="Online Teaching"></a>
							</div>
							<div class="down-content">
								<div class="date">
									<h6>
										Event <span>02</span>
									</h6>
								</div>
								<a href="meeting-details.html">
									<h4>CGV일산 IMAX 리뉴얼 오픈</h4>
								</a>
								<p>IMAX 리본 프로젝트[CGV일산 리뉴얼 오픈]</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="meeting-item">
							<div class="thumb">
								<a href="meeting-details.html"><img
									src="${contextPath }/resources/boogie/edu-meeting/assets/images/17267128069630.jpg"
									alt="Online Teaching"></a>
							</div>
							<div class="down-content">
								<div class="date">
									<h6>
										Event <span>02</span>
									</h6>
								</div>
								<a href="meeting-details.html">
									<h4>CGV일산 IMAX 리뉴얼 오픈</h4>
								</a>
								<p>IMAX 리본 프로젝트[CGV일산 리뉴얼 오픈]</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="meeting-item">
							<div class="thumb">
								<a href="meeting-details.html"><img
									src="${contextPath }/resources/boogie/edu-meeting/assets/images/베텥.jpg"
									alt="Student Training"></a>
							</div>
							<div class="down-content">
								<div class="date">
									<h6>
										Event <span>04</span>
									</h6>
								</div>
								<a href="meeting-details.html">
									<h4>[베테랑2] 흥행감사이벤트</h4>
								</a>
								<p>학생 베테랑즈</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</section>


	<section class="our-courses" id="courses">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-heading">
						<h2>인기상품</h2>
						<a href="${contextPath }/store.do" class="logo"> 상품 더보기 </a>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="owl-courses-item owl-carousel">
						<c:forEach var="store" items="${storeList }">
							<div class="item">
								<img src="${store.imgUrl }" alt="Course One">
								<div class="down-content">
									<h4>${store.product_name}</h4>
									<div class="info">
										<div class="row">
											<div class="col-8">
												<ul>
													<li><i class="fa fa-star"></i></li>
													<li><i class="fa fa-star"></i></li>
													<li><i class="fa fa-star"></i></li>
													<li><i class="fa fa-star"></i></li>
													<li><i class="fa fa-star"></i></li>
												</ul>
											</div>
											<div class="col-4">
												<span>${store.price }원</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>



	<section class="contact-us" id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-9 align-self-center">
					<div class="row">
						<div class="col-lg-12">
							<form id="contact" action="" method="post">
								<div class="row">
									<div class="col-lg-12">
										<h2>문의 사항</h2>
									</div>
									<div class="col-lg-4">
										<fieldset>
											<input name="name" type="text" id="name" placeholder="성명*"
												required="">
										</fieldset>
									</div>
									<div class="col-lg-4">
										<fieldset>
											<input name="email" type="text" id="email"
												pattern="[^ @]*@[^ @]*" placeholder="이메일" required="">
										</fieldset>
									</div>
									<div class="col-lg-4">
										<fieldset>
											<input name="subject" type="text" id="subject"
												placeholder="주제*" required="">
										</fieldset>
									</div>
									<div class="col-lg-12">
										<fieldset>
											<textarea name="message" type="text" class="form-control"
												id="message" placeholder="메세지를 입력하세요.." required=""></textarea>
										</fieldset>
									</div>
									<div class="col-lg-12">
										<fieldset>
											<button type="submit" id="form-submit" class="button">작성완료</button>
										</fieldset>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="right-info">
						<ul>
							<li>
								<h6>Phone Number</h6> <span>010-5576-7281</span>
							</li>
							<li>
								<h6>Email Address</h6> <span>wongeunjo74@gmail.com</span>
							</li>
							<li>
								<h6>Street Address</h6> <span>대전광역시 중구 계룡로 846</span>
							</li>
							<li>
								<h6>Website URL</h6> <span>https://www.ddit.or.kr</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../../includes/bottom.jsp"%>
	</section>

	<!-- Scripts -->
	<!-- Bootstrap core JavaScript -->
	<script
		src="${contextPath }/resources/boogie/edu-meeting/vendor/jquery/jquery.min.js"></script>

	<script
		src="${contextPath }/resources/boogie/edu-meeting/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/script.js"></script>
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
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/custom.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/script.js"></script>
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



</html>