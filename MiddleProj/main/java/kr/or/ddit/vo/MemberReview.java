package kr.or.ddit.vo;

import lombok.Data;

@Data
public class MemberReview {

	private int no;
	private String movieName;
	private String reviewCode;
	private String reviewContent;
	private String reviewDate;
	private int reviewStar;
	private int memNo;
	private String memId;
	private String movieNo;
}
