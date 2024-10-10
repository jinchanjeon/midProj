package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.CommentVo;

public class CommentDaoImpl extends MyBatisDao implements iCommentDao {
    private static CommentDaoImpl instance;

    private CommentDaoImpl() {
        super();
    }

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<CommentVo> insertComment(CommentVo comment) {
        int result = insert("comment.insertComment", comment);
        if (result > 0) {
            return selectList("comment.selectCommentsByBoardNo", comment.getBoard_no());
        }
        return null;
    }

    @Override
    public List<CommentVo> selectCommentsByBoardNo(String boardNo) {
        return selectList("comment.selectCommentsByBoardNo", boardNo);
    }

    @Override
    public int updateComment(CommentVo comment) {
        return update("comment.updateComment", comment);
    }

    @Override
    public int deleteComment(int commentNo) {
        return delete("comment.deleteComment", commentNo);
    }
}