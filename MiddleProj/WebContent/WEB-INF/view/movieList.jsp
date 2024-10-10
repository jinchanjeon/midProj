<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="member" value="${sessionScope.member}"></c:set>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<style>
.heading-page{
	padding-bottom:50px !important; 
}

button {
	background-color: #FF0000 !important;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px;
	font-size: 1rem;
}
.more-container {
    display: flex;              
    justify-content: center;   
    margin-bottom: 100px;         
}

button:hover {
	background-color: #d40000 !important;
}

.meetings-page{
	padding-top:0px !important;
}
</style>
<body>
	<%@include file="../../includes/top.jsp"%>


	<section class="heading-page header-text" id="top">
		<div class="container">
				<div class="col-lg-12">
					<h6>부귀롭게 한편을 보다.</h6>
					<h2>BoogieMovie</h2>
					<h2>상영작</h2>
			</div>
		</div>
	</section>

	<section class="meetings-page" id="meetings">
		 <div class="more-container"> <!-- 여기서 flexbox 설정 -->
        <button class="more" onclick="gotoMore()">더보기</button>
    </div>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row grid">
						<c:forEach var="movie" items="${boxInfo}" varStatus="status">
							<div class="col-lg-4 templatemo-item-col all soon">
								<div class="meeting-item">
									<div class="thumb">
										<%-- <p>영화 수: ${fn:length(boxInfo)}</p> --%>

										<a
											href="${contextPath }/movieDetail.do?movie_no=${movie.movieNo}"><img
											src="${ movie.moviePoster}" alt="${movie.movieName }"></a>
									</div>
									<div class="down-content">
										<div class="date">
											<h6>${status.index + 1}위</h6>
										</div>
										<a
											href="${contextPath }/movieDetail.do?movie_no=${movie.movieNo}"><h4>${movie.movieName }</h4></a>
										<div class="main-button-red">
    										<a href="${contextPath}/theaterSelect.do?movieNo=${movie.movieNo}">예매하기</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
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
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/isotope.min.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/owl-carousel.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/lightbox.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/tabs.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/isotope.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/video.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/slick-slider.js"></script>
	<script
		src="${contextPath }/resources/boogie/edu-meeting/assets/js/custom.js"></script>
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
              scrollTop: reqSectionPos },
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
        
        function gotoMore(){
        	location.href="${contextPath}/movieMore.do";
        }
    </script>


</body>

</html>
