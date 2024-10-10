package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.CommentVo;


public interface iCommentService {
    List<CommentVo> addComment(CommentVo comment);
    List<CommentVo> getCommentsByBoardNo(String boardNo);
    int updateComment(CommentVo comment);
    int deleteComment(int commentNo);
}