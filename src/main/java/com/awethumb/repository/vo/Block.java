package com.awethumb.repository.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Block {
	 // 정지 번호 
    private int blockNo;

    // 회원 번호 
    private int userNo;

    // 정지 코드 
    private int blockCode;

    // 정지 시작 날짜 
    private Date blockStartDt;

    // 정지 종료 날짜 
    private Date blockEndDt;
}
