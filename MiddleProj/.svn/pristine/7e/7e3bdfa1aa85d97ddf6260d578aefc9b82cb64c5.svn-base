package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.ReviewServiceImpl;
import kr.or.ddit.service.iBoardService;
import kr.or.ddit.service.iReviewService;

@WebServlet("/myboardDelete.do")
public class MyBoardDeleteController extends HttpServlet {
    iBoardService boardService = BoardServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardNo = req.getParameter("boardNo");
        boardService.deleteBoard(boardNo); 
//        resp.sendRedirect(req.getContextPath() + "/movieDetail.do?movie_no=" + req.getParameter("movie_no")); // 삭제 후 상세 페이지로 리다이렉트
    }
}