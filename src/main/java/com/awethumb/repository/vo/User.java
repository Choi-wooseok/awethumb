package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class User {
	private int userNo;
	private String userId;
	private String userPass;
	private String userNickname;
	private String userName;
	private String categoryList;
	private List<Auth> authList;
}
