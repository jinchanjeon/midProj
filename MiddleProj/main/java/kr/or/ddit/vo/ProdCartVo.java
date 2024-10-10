package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ProdCartVo {
	private String cartNo;
	private int prodNo;
	private int cartCnt;
	private int cartState;
	private int memNo;
}
