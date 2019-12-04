package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	private int userNo;
	private String userId;
	private String userPass;
	private String userNickname;
	private String userName;
	private String categoryList;
	private List<Auth> authList;
	private String userEmailKey;
	private int projectCnt;
	private int followingCnt;
	private int followerCnt;
	private boolean oauth;
	private String oauthType;
}
