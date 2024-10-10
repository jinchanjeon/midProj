package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ProdOrderVo {
	private String orderNo;
	private String ordDate;
	private int orderPrice;
	private int memNo;
	private int orderCnt;
	private String productId;
}
