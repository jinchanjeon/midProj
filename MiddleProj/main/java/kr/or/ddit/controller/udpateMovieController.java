package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.vo.MovieVo;

@WebServlet("/updateMovie.do")
public class udpateMovieController extends HttpServlet {
	 iMovieService movieService = MovieServiceImpl.getInstance();
	 
	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     try {
	         String movieNo = req.getParameter("movieNo");
	         String movieName = req.getParameter("movieName");
	         String movieCompany = req.getParameter("movieCompany");
	         
	         int movieRelease = Integer.parseInt(req.getParameter("movieRelease"));
	         String movieCountry = req.getParameter("movieCountry");
	         int movieRunningtime = Integer.parseInt(req.getParameter("movieRunningtime"));
	         String movieLevel = req.getParameter("movieLevel");
	         String movieState = req.getParameter("movieState");
	         String moviePoster = req.getParameter("moviePoster");
	         String movieTrailer = req.getParameter("movieTrailer");
	         String movieContent = req.getParameter("movieContent");
	         
	         MovieVo movie = new MovieVo();
	         movie.setMovieNo(movieNo);
	         movie.setMovieName(movieName);
	         movie.setMovieCompany(movieCompany);
	         movie.setMovieRelease(movieRelease);
	         movie.setMovieCountry(movieCountry);
	         movie.setMovieRunningtime(movieRunningtime);
	         movie.setMovieLevel(movieLevel);
	         movie.setMovieState(movieState);
	         movie.setMoviePoster(moviePoster);
	         movie.setMovieTrailer(movieTrailer);
	         movie.setMovieContent(movieContent);
	         
	         // 영화 정보 업데이트
	         movieService.updateMovie(movie);

	         // 성공적인 응답
	         resp.setStatus(HttpServletResponse.SC_OK);
	         resp.getWriter().write("영화 정보가 성공적으로 수정되었습니다.");
	         
	     } catch (Exception e) {
	         e.printStackTrace();
	         resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "영화 정보 수정 중 오류가 발생했습니다.");
	     }
	 }

}
