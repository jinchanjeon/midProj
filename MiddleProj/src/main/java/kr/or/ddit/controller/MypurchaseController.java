package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.MypurchaseServiceImpl;
import kr.or.ddit.service.iMypurchaseService;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.MypurchaseVo;
@WebServlet("/mypurchase.do")
public class MypurchaseController extends HttpServlet {
	
	iMypurchaseService mypurchaseService = MypurchaseServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo) session.getAttribute("member");
		
		int memNo = member.getMemNo();
		System.out.println("memNo: " + memNo);
		
		List<MypurchaseVo> mypurchase = mypurchaseService.getmy(memNo);
		System.out.println("mypurchase: "+ mypurchase);
		
		req.setAttribute("mypurchase", mypurchase);
		req.getRequestDispatcher("/WEB-INF/view/mypurchase.jsp").forward(req, resp);;
	
	}
	
	

}
