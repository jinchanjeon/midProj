package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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


@WebServlet("/boardWrite.do")
public class BoardWriteController extends HttpServlet {
    private iBoardService boardService = BoardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardVo> categoryList = boardService.cateList();
        
        HttpSession session = req.getSession();
        MemberVo loginMember = (MemberVo) session.getAttribute("member");
        Object memCodeObj = session.getAttribute("mem_code");
        
        String memCode = (memCodeObj != null) ? String.valueOf(memCodeObj) : null;
        
        if (loginMember != null) {
            req.setAttribute("memNo", loginMember.getMemNo());
            req.setAttribute("memName", loginMember.getMemName());
        } else {
            req.setAttribute("memNo", "0");
            req.setAttribute("memName", "비회원");
        }
        
        if (!"2".equals(memCode) && !"3".equals(memCode)) {
            categoryList = categoryList.stream()
                .filter(category -> !category.getBoard_code().equals("1"))
                .collect(Collectors.toList());
        }
        
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("memCode", memCode);
        req.getRequestDispatcher("/WEB-INF/view/boardWrite.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardCode = req.getParameter("board_code");
        String title = req.getParameter("board_title");
        String content = req.getParameter("board_content");
        
        HttpSession session = req.getSession();
        MemberVo loginMember = (MemberVo) session.getAttribute("member");
        Object memCodeObj = session.getAttribute("mem_code");
        
        String memCode = (memCodeObj != null) ? String.valueOf(memCodeObj) : null;
        
        BoardVo newBoard = new BoardVo();
        newBoard.setBoard_code(boardCode);
        newBoard.setBoard_title(title);
        newBoard.setBoard_content(content);
        
        if (loginMember != null) {
            newBoard.setMem_no(String.valueOf(loginMember.getMemNo()));
        } else {
            newBoard.setMem_no("0");
        }
        
        if ("1".equals(boardCode) && (!"2".equals(memCode) && !"3".equals(memCode))) {
            req.setAttribute("error", "매니저와 관리자만 공지사항을 작성할 수 있습니다.");
            doGet(req, resp);
            return;
        }
        
        boolean result = boardService.insertBoard(newBoard);
        
        if (!result) {
            List<BoardVo> categoryList = boardService.cateList();
            req.setAttribute("categoryList", categoryList);
            req.setAttribute("error", "게시물 작성에 실패했습니다.");
            req.setAttribute("board", newBoard);
            req.getRequestDispatcher("/WEB-INF/view/boardWrite.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/board.do");
        }
    }
}