package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

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

@WebServlet("/getmyboard.do")
public class MyBoardUpdateController extends HttpServlet {
	iBoardService boardService = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		MemberVo member = (MemberVo) session.getAttribute("member");

		int memNo = member.getMemNo();

		BoardVo board = boardService.myqna(memNo);

		// JSON 응답 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// Gson을 사용하여 JSON으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(board);

		resp.getWriter().write(json); // JSON 데이터 전송

	}
}