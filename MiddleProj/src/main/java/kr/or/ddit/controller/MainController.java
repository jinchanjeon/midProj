package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.api.koficAPI;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.StoreServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.service.iStoreService;
import kr.or.ddit.vo.BoxofficeVo;
import kr.or.ddit.vo.MovieVo;
import kr.or.ddit.vo.StoreVo;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
	iMovieService movieService = MovieServiceImpl.getInstance();
	iStoreService storeService = StoreServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		koficAPI ka = new koficAPI();
		try {
			ka.getAPI();

		} catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Error occurred while fetching box office data.");
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
            return; // 메서드 종료
        }

		
		List<StoreVo> storeList= storeService.getAllProducts();
		List<MovieVo> boxInfo = movieService.boxInfo();

		
        if (boxInfo == null || boxInfo.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/main.do");
            return; 
        }
		req.setAttribute("storeList",storeList);
		req.setAttribute("boxInfo", boxInfo);
		req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);

	}

}