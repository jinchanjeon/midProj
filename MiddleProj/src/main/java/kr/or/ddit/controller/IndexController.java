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

@WebServlet("/index")
public class IndexController extends HttpServlet {
    
    private iBoardService boardService = BoardServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 카테고리 목록 가져오기
            List<BoardVo> cateList = boardService.cateList();
            System.out.println("카테고리 목록: " + cateList);
            req.setAttribute("cateList", cateList);
            
            // 선택된 카테고리의 게시글 목록 가져오기
            String codeNoStr = req.getParameter("code_no");
            int codeNo = 1; // 기본값 설정 (예: 1번 카테고리)
            if (codeNoStr != null && !codeNoStr.isEmpty()) {
                try {
                    codeNo = Integer.parseInt(codeNoStr);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 code_no 파라미터: " + codeNoStr);
                }
            }
            
            System.out.println("선택된 카테고리 번호: " + codeNo);
            List<BoardVo> boardList = boardService.boardList(codeNo);
            System.out.println("게시글 목록: " + boardList);
            req.setAttribute("boardList", boardList);
            
            // 현재 선택된 카테고리 번호를 request에 저장
            req.setAttribute("currentCodeNo", codeNo);
            
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        
        req.getRequestDispatcher("/WEB-INF/view/index2.jsp").forward(req, resp);
    }
}