	
package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BoardVo;

public interface iBoardDao {

	public List<BoardVo> cateList();
	
	public List<BoardVo> boardList(int cateNo);
	
	public BoardVo boardDetail(int board_no);
	
	List<BoardVo> boardList(String boardCode);

	List<BoardVo> getAllBoards();
	
    List<BoardVo> getBoardListByCodeString(String boardCode);
    
    List<BoardVo> getBoardListByCodeNumber(int boardCode);
    
    BoardVo getBoard(String boardNo);

	public int getSimpleBoardList();

	public void incrementBoardCount(String boardNo);

	boolean insertBoard(BoardVo board);

	int updateBoard(BoardVo board);
	
    int deleteBoard(String boardNo);

    //나의 문의내역 조회
	public BoardVo myqna(String boardNo);

	public List<BoardVo> myboard(int memNo);
	
	// 페이징 처리
	List<BoardVo> getAllBoards(Map<String, Object> paramMap);
    int getTotalBoardCount();
    
}