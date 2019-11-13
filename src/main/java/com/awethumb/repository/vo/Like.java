package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Like {
    // 글 겸 댓글 번호 
    private int postAndCmtNo;
    // 회원 번호 
    private int userNo;
    // 코드 값 
    private int codeValue;
}
