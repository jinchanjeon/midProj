package kr.or.ddit.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.api.ApiExplorer2;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.vo.GenreVo;
import kr.or.ddit.vo.MovieVo;

@WebServlet("/movieMore.do")
public class MovieMoreController extends HttpServlet {
	iMovieService movieService = MovieServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 장르 번호 가져오기
			String genNo = req.getParameter("genNo");

			List<MovieVo> movieList;

			int currentPage = 1;
			String page = req.getParameter("page");
			currentPage = (page == null) ? 1 : Integer.parseInt(page);
			int totalCount = movieService.getTotal();

			// 화면에 표시될 페이지 수
			int perPage = 5;

			// 한 페이지당 출력할 글 개수
			int perList = 10;

			// 전체 페이지수 = 전체 글 개수(totalCount) / 페이지당 글 개수(perList)
			int totalPage = (int) Math.ceil(((double) totalCount / (double) perList));

			// 페이지 별 게시글 범위(start ~ end) 구하기
			int start = (currentPage - 1) * perList + 1;

			int end = start + perList - 1;
			if (end > totalCount)
				end = totalCount;

			// 페이지 범위(starPage ~ endPage) 구하기
			int startPage = ((currentPage - 1) / perPage * perPage) + 1;

			int endPage = startPage + perPage - 1;
			if (endPage > totalPage)
				endPage = totalPage;

			// 범위별 게시물 조회
//    		MovieVo paging = new MovieVo();
//    		paging.setStart(start);
//    		paging.setEnd(end);

			String search = req.getParameter("search");
			
			MovieVo searchMovie = new MovieVo();
			
			searchMovie.setStart(start);
			searchMovie.setEnd(end);
			searchMovie.setSearch(search);
			
			System.out.println("검색어: " + search);
			movieList = movieService.movieList(searchMovie);

			if (movieList == null || movieList.isEmpty()) {
				ApiExplorer2 apiExplorer = new ApiExplorer2();
				apiExplorer.getAPI(search);
				movieList = movieService.movieList(searchMovie);
			}

			req.setAttribute("list", movieList);
			req.setAttribute("cPage", currentPage);
			req.setAttribute("sPage", startPage);
			req.setAttribute("tPage", totalPage);
			req.setAttribute("ePage", endPage);

			MovieVo pageger = new MovieVo();
			pageger.setStart(start);
			pageger.setEnd(end);
			pageger.setGenNo(genNo);

			// 장르 번호에 따른 영화 목록 조회
			if (genNo != null && !genNo.isEmpty()) {
				movieList = movieService.getMoviesByGenre(pageger);
			} else {
				movieList = movieService.movieList(searchMovie);
			}

			req.setAttribute("movieList", movieList);

			// 장르 목록 가져오기
			List<GenreVo> genList = movieService.selectAllGen();
			req.setAttribute("genList", genList);

			req.getRequestDispatcher("/WEB-INF/view/movieMore.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
	}
}