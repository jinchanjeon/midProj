package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.iBoardService;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;
@WebServlet("/myqna.do")
public class MyqnaController extends HttpServlet {
	iBoardService boardService = BoardServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo) session.getAttribute("member");
		
		int memNo = member.getMemNo();
		
		List<BoardVo> boardVo = boardService.myboard(memNo);
//		BoardVo board = (BoardVo) session.getAttribute("board");
	
		req.setAttribute("boardVo", boardVo);
		req.getRequestDispatcher("/WEB-INF/view/myqna.jsp").forward(req, resp);;
	
	}
	
	

}
