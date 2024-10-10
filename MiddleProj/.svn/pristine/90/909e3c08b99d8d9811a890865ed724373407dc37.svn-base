package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.SpotServiceImpl;
import kr.or.ddit.service.iSpotService;
import kr.or.ddit.vo.SpotVo;

@WebServlet("/spotList.do")
public class spotController extends HttpServlet {

	iSpotService spotService = SpotServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<SpotVo> spotList = spotService.selSpot();

		req.setAttribute("spotList", spotList);
		System.out.println(spotList);

		// 페이지 이동
		req.getRequestDispatcher("/WEB-INF/view/managerPage.jsp").forward(req, resp);
	}
}
