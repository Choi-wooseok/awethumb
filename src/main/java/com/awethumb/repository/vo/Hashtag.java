package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Hashtag {
	// 글 번호 
	private int postNoAndCmtNo;
	
	// 해시태그 내용 
    private String hashtagContent;

    
    // 해시태그가 작성되는 글의 타입 (1: 게시글 / 2: 댓글)
    private int hashType;
    
}
