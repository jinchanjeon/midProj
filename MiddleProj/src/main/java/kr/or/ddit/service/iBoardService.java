package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BoardVo;

public interface iBoardService {

    List<BoardVo> cateList();
    
    List<BoardVo> boardList(int cateNo);
    
    BoardVo boardDetail(int board_no);
    
    List<BoardVo> boardList(String boardCode);

    List<BoardVo> getAllBoards();
    
    List<BoardVo> getBoardListByCodeString(String boardCode);
    
    List<BoardVo> getBoardListByCodeNumber(int boardCode);
    
    BoardVo getBoard(String boardNo);

	int getSimpleBoardList();
	
	void incrementBoardCount(String boardNo);
	
	boolean insertBoard(BoardVo board);
	
	boolean updateBoard(BoardVo board);
	
    boolean deleteBoard(String boardNo);

    // 나의 문의내역 조회
	BoardVo myqna(String boardNo);

	List<BoardVo> myboard(int memNo);
	
	// 페이징 처리
	List<BoardVo> getAllBoards(Map<String, Object> paramMap);
    int getTotalBoardCount();
	
}