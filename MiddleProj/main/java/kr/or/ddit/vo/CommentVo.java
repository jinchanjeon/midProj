package kr.or.ddit.vo;

import lombok.Data;

@Data
public class CommentVo {

	private int cmt_no;
	private String cmt_content;
	private int cmt_parent_no;
	private String cmt_date;
	private int cmt_depth;
	private int cmt_order_number;
	private String board_no;
	private int mem_no;
}
