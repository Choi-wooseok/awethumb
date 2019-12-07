package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Search {
	// 해시태그 갯수 및 유저 번호
	private int hashtagCountAndUserNo;
	
	// 해시태그 내용 및 유저 닉네임
	private String hashtagAndNickname;
	
	// 검색 결과 타입  / u는 유저 / h는 해시태그
	private String resultType;

    // 검색 키워드
    private String searchWord;
}
