package kr.or.ddit.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.vo.MovieVo;

@WebServlet("/getMovie.do")
public class MovieController extends HttpServlet{
	iMovieService movieService = MovieServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String movieNo= req.getParameter("movieNo");
		MovieVo movie = new MovieVo();
		movie.setMovieNo(movieNo);
		MovieVo movieInfo = movieService.getMovie(movie);
	    
		System.out.println(movieInfo);
		req.setAttribute("movieInfo", movieInfo);
		
		req.getRequestDispatcher("/WEB-INF/view/movieRegisterForm.jsp").forward(req, resp);
	}

}
