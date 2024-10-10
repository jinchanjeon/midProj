package kr.or.ddit.vo;

import lombok.Data;

@Data
public class AttachFileVo {	
	private int file_no;
	private int file_order;
	private String file_path;
	private String file_name;
	private String file_org_name;
	private String file_ext;
	private long file_size;
	private String file_date;

}
