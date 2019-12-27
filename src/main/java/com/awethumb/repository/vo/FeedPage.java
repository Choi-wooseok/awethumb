package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class FeedPage {
	// 페이지 시작(번호 초기값 0) 
	private int pageIndex; 
	
	// 페이지  (0~몇개씩 뽑을건지)
	private int pageCount = 9; 

    // 검색 키워드
    private String searchWord;
    
    // 내유저번호로 구독한사람뽑기
    private int subUserNo;
    
    // 이미지를 위한 글번호
    private int postNo;
}
