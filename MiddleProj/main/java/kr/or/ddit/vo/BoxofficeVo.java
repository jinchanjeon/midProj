package kr.or.ddit.vo;

import lombok.Data;

@Data
public class BoxofficeVo {
	private String boxcode;
	private String movieName;
	private int releaseDate;
	private int boxofficeDate;
	private int rank;
	private int totalPeople;
	private int todayPeople;

}
