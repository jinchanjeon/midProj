package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.CommentDaoImpl;
import kr.or.ddit.dao.iCommentDao;
import kr.or.ddit.vo.CommentVo;

public class CommentServiceImpl implements iCommentService {
    private static CommentServiceImpl instance;
    private iCommentDao commentDao;

    private CommentServiceImpl() {
        commentDao = CommentDaoImpl.getInstance();
    }

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CommentVo> addComment(CommentVo comment) {
        List<CommentVo> commentList = commentDao.insertComment(comment);
        if (commentList != null && !commentList.isEmpty()) {
            System.out.println("댓글이 추가되었습니다. 현재 댓글 목록:");
            for (CommentVo c : commentList) {
                System.out.println("댓글 번호: " + c.getCmt_no());
                System.out.println("내용: " + c.getCmt_content());
                System.out.println("작성일: " + c.getCmt_date());
                System.out.println("게시글 번호: " + c.getBoard_no());
                System.out.println("작성자 번호: " + c.getMem_no());
                System.out.println("--------------------");
            }
        }
        return commentList;
    }

    @Override
    public List<CommentVo> getCommentsByBoardNo(String boardNo) {
        return commentDao.selectCommentsByBoardNo(boardNo);
    }

    @Override
    public int updateComment(CommentVo comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public int deleteComment(int commentNo) {
        return commentDao.deleteComment(commentNo);
    }
}