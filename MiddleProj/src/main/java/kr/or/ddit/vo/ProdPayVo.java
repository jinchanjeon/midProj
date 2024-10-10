package kr.or.ddit.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProdPayVo {
	private String prodPayNo;
	private int prodPayPrice;
	private String prodPayState;
	private Timestamp prodPayCreated;
	private String orderNo;
}
