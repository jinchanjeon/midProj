package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.SpotServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.service.iSpotService;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.MovieVo;
import kr.or.ddit.vo.SpotVo;
@WebServlet("/mypage.do")
public class MypageController extends HttpServlet{
	iMemberService memberService = MemberServiceImpl.getInstance();
	iMovieService movieService = MovieServiceImpl.getInstance();
	iSpotService spotService = SpotServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo)session.getAttribute("member");

		//사용자가 로그인하지 않았을 때 로그인 페이지로 보냅
		if(member == null) {
			resp.sendRedirect("/MiddleProj/login.do");
			return;
		}
		
		
		String memId = member.getMemId();	
		String memName = member.getMemName();
		String memMail = member.getMemMail();
		int memCode = member.getMemCode();
		List<SpotVo> spotList = spotService.selSpot();

		
		
		if(memCode==3) {
			req.setAttribute("memId", memId);
			req.setAttribute("memName", memName);
			req.setAttribute("memMail", memMail);
			req.setAttribute("spotList", spotList);
			req.getRequestDispatcher("/WEB-INF/view/managerPage.jsp").forward(req, resp);
		}
		else if(memCode==1) {
			req.setAttribute("memId", memId);
			req.setAttribute("memName", memName);
			req.setAttribute("memMail", memMail);
			req.getRequestDispatcher("/WEB-INF/view/mypage2.jsp").forward(req, resp);
		}
		else if(memCode==2) {
			req.setAttribute("memId", memId);
			req.setAttribute("memName", memName);
			req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(req, resp);
		}
		

	}
	
	
}
