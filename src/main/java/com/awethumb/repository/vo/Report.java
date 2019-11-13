package com.awethumb.repository.vo;

import java.util.Date;

import lombok.Data;


@Data
public class Report {
	 // 신고 번호 
    private int reportNo;

    // 정지 코드 
    private int blockCode;

    // 글 번호 
    private int postNo;

    // 회원 번호 
    private int userNo;

    // 댓글 번호 
    private int cmtNo;

    // 신고 날짜 
    private Date reportDt;

    // 신고 내용 
    private String reportContent;
}
