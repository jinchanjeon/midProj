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
@WebServlet("/myreview.do")
public class MyreviewController extends HttpServlet {
	iReviewService reviewService = ReviewServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo) session.getAttribute("member");
		MemberReview memberReview = (MemberReview) session.getAttribute("memberReview");
		
		int memNo = member.getMemNo();
		
		List<MemberReview> myreview = reviewService.myreview(memNo);
		
		
		
		req.setAttribute("myreview", myreview);
		req.getRequestDispatcher("/WEB-INF/view/myreview.jsp").forward(req, resp);;
	
	}
	


}
