package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Stats {
	private int postNo;
	private int viewCnt;
	private int likeCnt;
	private String postContent;
	private int subscribeCnt;
	private String userId;
	private String userNickname;
	private int userNo;
	private int totalViewCnt;
	private int totalLikeCnt;
	private int totalPostCnt;
	
}
