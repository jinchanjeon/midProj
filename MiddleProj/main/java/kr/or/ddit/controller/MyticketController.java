package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.ReviewServiceImpl;
import kr.or.ddit.service.iReviewService;
import kr.or.ddit.vo.MemberReview;
import kr.or.ddit.vo.MemberVo;
@WebServlet("/mypurchase.do")
public class MyticketController extends HttpServlet {
	iReviewService reviewService = ReviewServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo) session.getAttribute("member");
		
		int memNo = member.getMemNo();
		
	
		req.getRequestDispatcher("/WEB-INF/view/mypurchase.jsp").forward(req, resp);;
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}

}
