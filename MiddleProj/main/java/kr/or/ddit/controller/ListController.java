package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.iBoardService;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/boardList")
public class ListController extends HttpServlet{

	iBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cood_noStr = req.getParameter("code_no");
		System.out.println(cood_noStr);
		if(cood_noStr !=null) {
			int code_no = Integer.parseInt(cood_noStr);
			System.out.println("codeNo : "+code_no);
			List<BoardVo> boardList = boardService.boardList(code_no);
			System.out.println(boardList);
			
			req.setAttribute("boardList", boardList);
			
			req.getRequestDispatcher("WEB-INF/view/list.jsp").forward(req, resp);
			
		} else {
			
		}
	}
}
