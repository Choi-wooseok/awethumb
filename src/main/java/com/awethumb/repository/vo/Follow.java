package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Follow {
	private int userNo;
	private String searchName;
	
	private int countPerPage;
	private int currentPageNo;
}
