package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.vo.GenreVo;
import kr.or.ddit.vo.MovieVo;

@WebServlet("/movieList.do")
public class MovieListController extends HttpServlet {
    iMovieService movieService = MovieServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	List<MovieVo> boxInfo = movieService.boxInfo();
    	System.out.println("BoxInfo Size: " + boxInfo.size());

		req.setAttribute("boxInfo", boxInfo);
		req.getRequestDispatcher("/WEB-INF/view/movieList.jsp").forward(req, resp);
    }
}