package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class FeedPage {
	private int pageIndex; // 페이지 시작(번호 초기값 0) 
	private int pageCount = 5; // 페이지  (0~몇개씩 뽑을건지)
}
