package kr.or.ddit.vo;

import lombok.Data;

@Data
public class EventVo {
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardCnt;
	private String boardImg;
	private String boardWriteDate;
	private String boardStart;
	private String boardEnd;
	private int memNo;
	private int boardCode;
	private int boardState;
	
	String writer;
}
