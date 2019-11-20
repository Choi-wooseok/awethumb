package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Follow {
	private int userNo;
	
	private int countPerPage = 2;
	private int currentPageNo;
}
