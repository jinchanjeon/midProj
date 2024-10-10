package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ProdVo {
	private String productId;
	private String storeId;
	private String productName;
	private int price;
	private int stock;
	private String categoryId;
	private String imgUrl;
	private String content;

}
