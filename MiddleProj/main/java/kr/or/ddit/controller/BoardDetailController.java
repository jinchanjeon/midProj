package kr.or.ddit.controller;

import java.io.IOException;

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

@WebServlet("/boardDetail.do")
public class BoardDetailController extends HttpServlet {
    private iBoardService boardService = BoardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("BoardDetailController: doGet started");

        String boardNo = req.getParameter("boardNo");
        System.out.println("BoardDetailController: Fetching detail for boardNo = " + boardNo);

        if (boardNo != null && !boardNo.isEmpty()) {
            long incrementStartTime = System.currentTimeMillis();
            boardService.incrementBoardCount(boardNo);
            System.out.println("BoardDetailController: Incremented board count in " + (System.currentTimeMillis() - incrementStartTime) + "ms");
            
            long getBoardStartTime = System.currentTimeMillis();
            BoardVo board = boardService.getBoard(boardNo);
            System.out.println("BoardDetailController: Retrieved board in " + (System.currentTimeMillis() - getBoardStartTime) + "ms");
            
            if (board != null) {
                HttpSession session = req.getSession();
                Object memNoObj = session.getAttribute("mem_no");
                Object memNameObj = session.getAttribute("mem_name");
                
                String memNo = (memNoObj != null) ? memNoObj.toString() : null;
                String memName = (memNameObj != null) ? memNameObj.toString() : null;
                
                System.out.println("Logged in user no: " + memNo);
                System.out.println("Logged in user name: " + memName);
                
                req.setAttribute("board", board);
                req.setAttribute("mem_no", memNo);
                req.setAttribute("mem_name", memName);
                req.getRequestDispatcher("/WEB-INF/view/boardDetail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "게시글을 찾을 수 없습니다.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "올바른 게시글 번호가 필요합니다.");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("BoardDetailController: doGet completed in " + (endTime - startTime) + "ms");
    }
}