package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.vo.ManageVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/updateManager.do")
public class updateManagerController extends HttpServlet {
	iMemberService memberService = MemberServiceImpl.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String memId = req.getParameter("memId");
	    int memNo = Integer.parseInt(req.getParameter("memNo"));
	    MemberVo existingMember = memberService.selectByNo(memNo);
	    
	    String spotCode = req.getParameter("spotCode");
	    ManageVo manage = new ManageVo();
	    manage.setMemNo(memNo);
	    manage.setSpotCode(spotCode);
	    
	    String memPw = req.getParameter("memPw");
	    if (memPw == null || memPw.isEmpty()) {
	        memPw = existingMember.getMemPw();
	    }

	    String memName = req.getParameter("memName");
	    String memMail = req.getParameter("memMail");
	    String memPhone = req.getParameter("memPhone");
	    String memBirth = req.getParameter("memBirth");

	    if (memBirth == null || memBirth.isEmpty()) {
	        memBirth = existingMember.getMemBirth(); 
	    }
	    
//	    String[] parts = memBirth.split("-");
//	    if (parts.length == 3) {
//	    	memBirth = parts[0].substring(2) + "/" + parts[1] + "/" + parts[2];
//	    	memBirth=memBirth.split(" ")[0];
//	    }
//	    System.out.println(memBirth);

	    String memGender = req.getParameter("memGender");

	    MemberVo member = new MemberVo();
	    member.setMemNo(memNo);
	    member.setMemId(memId);
	    member.setMemPw(memPw);
	    member.setMemName(memName);
	    member.setMemMail(memMail);
	    member.setMemPhone(memPhone);
	    member.setMemBirth(memBirth);
	    member.setMemGender(memGender);

	    memberService.updateManager(member);
	    memberService.updateMng(manage);
	    System.out.println(member);

	    resp.sendRedirect(req.getContextPath() + "/mypage.do");
	}

}
