package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchUser {
	private String searchWord;
	private List<Integer> existUser;
}
