	
package kr.or.ddit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.BoardVo;

public class BoardDaoImpl extends MyBatisDao implements iBoardDao {

    private static BoardDaoImpl instance;

    private SqlSessionFactory sqlSessionFactory;
    
    private BoardDaoImpl() {
        super();
    }

    
    public static BoardDaoImpl getInstance() {
        if (instance == null) {
            instance = new BoardDaoImpl();
        }
        return instance;
    }

    
    @Override
    public List<BoardVo> getAllBoards() {
        System.out.println("BoardDaoImpl: getAllBoards called");
        List<BoardVo> boards = selectList("board.getAllBoards");
        System.out.println("BoardDaoImpl: getAllBoards result size = " + (boards != null ? boards.size() : "null"));
        if (boards != null) {
            for (BoardVo board : boards) {
                System.out.println("Board: " + board.getBoard_no() + ", Title: " + board.getBoard_title() + ", Writer: " + board.getWriter());
            }
        }
        return boards;
    }

    @Override
    public List<BoardVo> getBoardListByCodeString(String boardCode) {
        return selectList("board.getBoardListByCodeString", boardCode);
    }

    @Override
    public List<BoardVo> getBoardListByCodeNumber(int boardCode) {
        return selectList("board.getBoardListByCodeNumber", boardCode);
    }

    @Override
    public List<BoardVo> cateList() {
        System.out.println("BoardDaoImpl: cateList 호출");
        long startTime = System.currentTimeMillis();
        List<BoardVo> result = selectList("board.cateList");
        long endTime = System.currentTimeMillis();
        System.out.println("BoardDaoImpl: cateList 완료, 소요 시간: " + (endTime - startTime) + "ms");
        
        if (result != null) {
            for (BoardVo board : result) {
                System.out.println("BoardDaoImpl: Category: " + board.getCode_name() + ", Board Code: " + board.getBoard_code());
            }
        } else {
            System.out.println("BoardDaoImpl: cateList 결과가 null입니다.");
        }
        
        return result;
    }
    

    @Override
    public List<BoardVo> boardList(String boardCode) {
        return selectList("board.boardList", boardCode);
    }
        

    @Override
    public BoardVo boardDetail(int board_no) {
        return selectOne("board.boardDetail", board_no);
    }

    @Override
    public List<BoardVo> boardList(int cateNo) {
        System.out.println("BoardDaoImpl: boardList(int) 메소드 시작, cateNo = " + cateNo);
        try {
            List<BoardVo> result = selectList("board.getBoardListByCateNo", cateNo);
            System.out.println("BoardDaoImpl: 쿼리 결과 크기 = " + (result != null ? result.size() : "null"));
            return result;
        } catch (Exception e) {
            System.err.println("BoardDaoImpl: 오류 발생 - " + e.getMessage());
            e.printStackTrace(System.err);
            throw e;
        }
    }

    public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Category> getCategoryList() {
        System.out.println("BoardDaoImpl: getCategoryList 메소드 호출");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<Category> categoryList = sqlSession.selectList("board.getCategoryList");
            System.out.println("BoardDaoImpl: getCategoryList 결과 크기: " + (categoryList != null ? categoryList.size() : "null"));
            return categoryList;
        } catch (Exception e) {
            System.err.println("BoardDaoImpl: 오류 발생 - " + e.getMessage());
            e.printStackTrace(System.err);
            return new ArrayList<>(); // 빈 리스트 반환
        }
    }
    
    @Override
    public BoardVo getBoard(String boardNo) {
        System.out.println("BoardDaoImpl: getBoard called with boardNo = " + boardNo);
        BoardVo board = selectOne("board.getBoard", boardNo);
        System.out.println("BoardDaoImpl: SQL query result = " + board);
        return board;
    }
    
    public int getSimpleBoardList() {
        return selectOne("board.getSimpleBoardList");
    }

    @Override
    public void incrementBoardCount(String boardNo) {
        System.out.println("BoardDaoImpl: Incrementing board count for boardNo = " + boardNo);
        update("board.incrementBoardCount", boardNo);
    }
    
    @Override
    public boolean insertBoard(BoardVo board) {
        System.out.println("BoardDaoImpl: insertBoard called");
        int result = insert("board.insertBoard", board);
        return result > 0;
    }
    
    @Override
    public int updateBoard(BoardVo board) {
        return update("board.updateBoard", board);
    }

    @Override
    public int deleteBoard(String boardNo) {
    	System.out.println("Deleting board in DAO: " + boardNo); 
        return delete("board.deleteBoard", boardNo);
    }


	@Override
	public BoardVo myqna(String boardNo) {
		return selectOne("board.myqna", boardNo);
	}


	@Override
	public List<BoardVo> myboard(int memNo) {
		return selectList("board.myboard", memNo);
	}


	@Override
    public List<BoardVo> getAllBoards(Map<String, Object> paramMap) {
        System.out.println("BoardDaoImpl: getAllBoards with paging called");
        List<BoardVo> boards = selectList("board.getAllBoardsPaged", paramMap);
        System.out.println("BoardDaoImpl: getAllBoards with paging result size = " + (boards != null ? boards.size() : "null"));
        return boards;
    }


	@Override
    public int getTotalBoardCount() {
        System.out.println("BoardDaoImpl: getTotalBoardCount called");
        return selectOne("board.getTotalBoardCount");
    }
}
