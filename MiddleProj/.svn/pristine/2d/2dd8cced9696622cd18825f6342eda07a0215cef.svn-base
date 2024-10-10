package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;

@WebServlet("/buy.do")
public class buyController extends HttpServlet{

	iMemberService memberService = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		memberService.selectMembyId("")
		
		req.getRequestDispatcher("/WEB-INF/view/buy.jsp").forward(req, resp);
	}
}
