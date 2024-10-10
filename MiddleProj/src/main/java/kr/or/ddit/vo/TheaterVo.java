package kr.or.ddit.vo;

import lombok.Data;

@Data
public class TheaterVo {

// 지점(SPOT)
	private String spotCode;
	private String spotName;
	private String spotSite;
	private String regNo;
	private String mngNo;

// 지역(REGION)
	private String regName;

// 상영관(MOVIE_ROOM)
	private String roomNum;
	private String roomName;

// 스케줄(schedule)
	private String schNo;
	private String schDate;
	private String movieStart;
	private String movieEnd;
	private String movieNo;

}