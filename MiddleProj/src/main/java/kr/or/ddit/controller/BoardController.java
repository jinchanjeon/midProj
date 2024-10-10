package kr.or.ddit.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.iBoardService;
import kr.or.ddit.vo.BoardVo;



@WebServlet("/board.do")
public class BoardController extends HttpServlet {
    private iBoardService boardService = BoardServiceImpl.getInstance();
    private int requestCount = 0;
    private static final int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        requestCount++;
        System.out.println("Request #" + requestCount + " started");

        String action = req.getParameter("action");
        System.out.println("BoardController: action = " + action);

        try {
            if ("getList".equals(action)) {
                handleGetList(req, resp);
            } else if ("write".equals(action)) {
                handleWritePage(req, resp);
            } else if ("getBoard".equals(action)) {
                handleGetBoard(req, resp);
            } else if ("detail".equals(action)) {
                handleDetailPage(req, resp);
            } else {
                loadDefaultPage(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("Request #" + requestCount + " completed in " + (endTime - startTime) + "ms");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement POST method if needed
    }

    private void handleGetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("handleGetList started");

        String boardCode = req.getParameter("boardCode");
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        int totalCount = boardService.getTotalBoardCount();
        int pageSize = 10; // 한 페이지당 보여줄 게시글 수
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        int start = (page - 1) * pageSize;
        int end = start + pageSize;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("end", end);
        paramMap.put("boardCode", boardCode);

        // 새로 추가된 부분: 게시물 유형 필터링
        String boardType = req.getParameter("boardType");
        paramMap.put("boardType", boardType);

        try {
            List<BoardVo> boardList = boardService.getAllBoards(paramMap);
            System.out.println("BoardController: boardList size = " + (boardList != null ? boardList.size() : "null"));

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("boardList", boardList);
            resultMap.put("currentPage", page);
            resultMap.put("totalPages", totalPages);
            resultMap.put("boardType", boardType);  // 새로 추가된 부분

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            
            String jsonResponse = new Gson().toJson(resultMap);
            System.out.println("BoardController: JSON response = " + jsonResponse);
            resp.getWriter().write(jsonResponse);
        } catch (Exception e) {
            System.err.println("BoardController: Error occurred - " + e.getMessage());
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"서버 오류가 발생했습니다.\"}");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("handleGetList completed in " + (endTime - startTime) + "ms");
    }

    private void handleWritePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("handleWritePage started");

        List<BoardVo> categoryList = boardService.cateList();
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/WEB-INF/view/boardWrite.jsp").forward(req, resp);

        long endTime = System.currentTimeMillis();
        System.out.println("handleWritePage completed in " + (endTime - startTime) + "ms");
    }

    private void loadDefaultPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("loadDefaultPage started");
        long startTime = System.currentTimeMillis();

        List<BoardVo> cateList = boardService.cateList();
        System.out.println("cateList fetched in " + (System.currentTimeMillis() - startTime) + "ms");

        List<BoardVo> allBoards = boardService.getAllBoards();
        System.out.println("allBoards fetched in " + (System.currentTimeMillis() - startTime) + "ms");
        
        req.setAttribute("categoryList", cateList);
        req.setAttribute("boardList", allBoards);
        req.getRequestDispatcher("/WEB-INF/view/index2.jsp").forward(req, resp);

        long endTime = System.currentTimeMillis();
        System.out.println("loadDefaultPage completed in " + (endTime - startTime) + "ms");
    }

    private void handleDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("handleDetailPage started");

        String boardNo = req.getParameter("boardNo");
        System.out.println("BoardController: Fetching detail for boardNo = " + boardNo);

        if (boardNo != null && !boardNo.isEmpty()) {
            BoardVo board = boardService.getBoard(boardNo);
            if (board != null) {
                req.setAttribute("board", board);
                req.getRequestDispatcher("/WEB-INF/view/boardDetail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Board not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Board number is required");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("handleDetailPage completed in " + (endTime - startTime) + "ms");
    }

    private void handleGetBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("handleGetBoard started");

        String boardNo = req.getParameter("boardNo");
        System.out.println("BoardController: handleGetBoard called with boardNo = " + boardNo);

        try {
            BoardVo board = boardService.getBoard(boardNo);
            if (board != null) {
                System.out.println("Board: " + board.getBoard_no() + 
                                   ", Title: " + board.getBoard_title() + 
                                   ", Content: " + board.getBoard_content() + 
                                   ", Count: " + board.getBoard_cnt() + 
                                   ", Image: " + board.getBoard_img() + 
                                   ", Write Date: " + board.getBoard_write_date() + 
                                   ", Start: " + board.getBoard_start() + 
                                   ", End: " + board.getBoard_end() + 
                                   ", Member No: " + board.getMem_no() + 
                                   ", Board Code: " + board.getBoard_code() + 
                                   ", Writer: " + board.getWriter() + 
                                   ", Code Name: " + board.getCode_name());
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            
            String jsonResponse = new Gson().toJson(board);
            System.out.println("BoardController: JSON response = " + jsonResponse);
            resp.getWriter().write(jsonResponse);
        } catch (Exception e) {
            System.err.println("BoardController: Error occurred - " + e.getMessage());
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"서버 오류가 발생했습니다.\"}");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("handleGetBoard completed in " + (endTime - startTime) + "ms");
    }
}