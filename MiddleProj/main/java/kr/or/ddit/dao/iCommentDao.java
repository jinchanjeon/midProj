package kr.or.ddit.dao;

import java.util.List;
import kr.or.ddit.vo.CommentVo;

public interface iCommentDao {
    List<CommentVo> insertComment(CommentVo comment);
    List<CommentVo> selectCommentsByBoardNo(String boardNo);
    int updateComment(CommentVo comment);
    int deleteComment(int commentNo);
}