package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.CommentServiceImpl;
import kr.or.ddit.service.iCommentService;
import kr.or.ddit.vo.CommentVo;

@WebServlet("/addComment.do")
public class CommentController extends HttpServlet {
    private iCommentService commentService = CommentServiceImpl.getInstance();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CommentController: doPost 메소드 시작");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String content = req.getParameter("content");
        String boardNo = req.getParameter("boardNo");
        // 실제 환경에서는 세션에서 회원 정보를 가져와야 합니다.
        int memNo = 1; // 임시 회원 번호

        System.out.println("받은 댓글 내용: " + content);
        System.out.println("게시글 번호: " + boardNo);
        System.out.println("작성자 번호: " + memNo);

        CommentVo comment = new CommentVo();
        comment.setCmt_content(content);
        comment.setBoard_no(boardNo);
        comment.setMem_no(memNo);

        List<CommentVo> commentList = commentService.addComment(comment);

        System.out.println("댓글 추가 후 반환된 댓글 목록 크기: " + (commentList != null ? commentList.size() : "null"));

        if (commentList != null && !commentList.isEmpty()) {
            String jsonResult = gson.toJson(commentList);
            System.out.println("클라이언트로 전송할 JSON: " + jsonResult);
            out.print(jsonResult);
        } else {
            System.out.println("댓글 추가 실패 또는 댓글 목록 조회 실패");
            out.print("{\"status\":\"fail\", \"message\":\"댓글 추가에 실패했습니다.\"}");
        }

        System.out.println("CommentController: doPost 메소드 종료");
    }
}