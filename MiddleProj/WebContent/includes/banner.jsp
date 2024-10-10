<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.or.ddit.vo.MovieVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>


<!DOCTYPE html>
<html>
<body>

	<%
	List<MovieVo> boxInfo = (List<MovieVo>) request.getAttribute("boxInfo");
	Random random = new Random();

	if (boxInfo != null && !boxInfo.isEmpty()) {
		// 필터링: movieTrailer가 null이 아닌 것만 포함한 리스트 생성
		List<MovieVo> validMovies = new ArrayList<>();
		for (MovieVo movie : boxInfo) {
			if (movie.getMovieTrailer() != null) {
		validMovies.add(movie);
			}
		}

		if (!validMovies.isEmpty()) {
			int randomIndex = random.nextInt(validMovies.size());
			String movieTrailer = validMovies.get(randomIndex).getMovieTrailer();
			String urlhead= movieTrailer.split("trailerPlayPop")[0]+"play/";
			String urlbody=movieTrailer.split("pFileNm=")[1];
			String url = urlhead+urlbody;
			System.out.println("Movie Trailer URL: " + url);
	%>
	
	<!-- ***** Main Banner Area Start ***** -->
	<section class="section main-banner" id="top" data-section="section1">
		<video id="videoPlayer" preload="metadata" autoplay muted loop controls width="100%">
			<source src=<%=url %>>
		</video>

		<div class="container">
			<!-- Add margin if needed -->
			<div class="row">
				<div class="col-lg-12">
					<div class="caption">
						<h6>상영</h6>
						<h2><%=validMovies.get(randomIndex).getMovieName()%></h2>
						<!-- 영화 제목 출력 -->
						<div class="main-button-red">
							<a href="${contextPath}/reservation.do">예매하기</a>
						</div>
						<p><%=validMovies.get(randomIndex).getMovieContent()%></p>
						<!-- 영화 설명 출력 -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** Main Banner Area End ***** -->
	<%
	} else {
	%>
	<p>No valid movies available with trailers.</p>
	<%
	}
	} else {
	%>
	<p>No movies available.</p>
	<%
    }
%>
</body>
</html>
