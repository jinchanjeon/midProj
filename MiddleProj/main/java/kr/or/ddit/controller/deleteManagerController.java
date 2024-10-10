package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/deleteManager.do")
public class deleteManagerController extends HttpServlet {
	iMemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		MemberVo mem = new MemberVo();
		mem.setMemId(memId);
		memberService.withdraw(mem);
		resp.sendRedirect(req.getContextPath() + "/mypage.do"); 
																												// 페이지로
																												// 리다이렉트
	}
}
