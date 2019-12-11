package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class FollowMeUser {
	private int userNo;
	private String userNickname;
	private String userId;
	// 페이지 시작(번호 초기값 0) 
	private int sidePageIndex; 
	// 페이지  (0~몇개씩 뽑을건지)
	private int sidePageCount;
	
	
}
