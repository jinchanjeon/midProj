	
package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.iBoardService;

@WebServlet("/boardDelete.do")
public class BoardDeleteController extends HttpServlet {
    private iBoardService boardService = BoardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("BoardDeleteController: doGet started");

        String boardNo = req.getParameter("boardNo");
        System.out.println("BoardDeleteController: Attempting to delete board with No: " + boardNo);

        boolean result = boardService.deleteBoard(boardNo);

        System.out.println("Delete result for boardNo " + boardNo + ": " + result);
        
        if (result) {
            System.out.println("BoardDeleteController: Board deleted successfully");
            resp.sendRedirect(req.getContextPath() + "/board.do");
        } else {
            System.out.println("BoardDeleteController: Failed to delete board");
            req.setAttribute("error", "게시글 삭제에 실패했습니다.");
            // resp.sendRedirect(req.getContextPath() + "/boardDetail.do?boardNo=" + boardNo);
            resp.sendRedirect(req.getContextPath() + "/board.do");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("BoardDeleteController: doGet completed in " + (endTime - startTime) + "ms");
    }
    
    
}