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
            String urlhead = movieTrailer.split("trailerPlayPop")[0] + "play/";

            // urlbody를 안전하게 얻기 위한 코드
            String urlbody = "";
            String[] splitTrailer = movieTrailer.split("pFileNm=");
            if (splitTrailer.length > 1) {
                urlbody = splitTrailer[1];
            } else {
                // pFileNm이 없는 경우 기본값 설정하거나 에러 처리
                urlbody = "defaultValue"; // 적절한 기본값으로 설정
            }

            String url = urlhead + urlbody;
            System.out.println("Movie Trailer URL: " + url);
%>
    
    <!-- ***** Main Banner Area Start ***** -->
    <section class="section main-banner" id="top" data-section="section1">
        <video id="videoPlayer" preload="metadata" autoplay muted loop controls width="100%">
            <source src="<%=url %>">
        </video>

        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="caption">
                        <h6>상영</h6>
                        <h2><%=validMovies.get(randomIndex).getMovieName()%></h2>
                        <div class="main-button-red">
                            <a href="${contextPath}/reservation.do">예매하기</a>
                        </div>
                        <p><%=validMovies.get(randomIndex).getMovieContent()%></p>
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
