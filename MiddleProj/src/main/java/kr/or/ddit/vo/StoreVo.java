package kr.or.ddit.vo;

import lombok.Data;

@Data
public class StoreVo {
	
//	PRODUCT
	private String product_id;
	private String store_id;
	private String product_name;
	private int price;
	private int stock;
	private String imgUrl;
	private String content;
	
//	PRODUCT_CATEGORY
	private String category_id;
	private String category_name;
	
//	PRODUCT_CART
	private int cart_id;
	private int mem_no;
	private int quantity;
	private String added_date;
	private String mem_name;
}