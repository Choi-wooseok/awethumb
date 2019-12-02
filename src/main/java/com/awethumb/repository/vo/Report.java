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
    
    // 신고 처리 상태
    private String reportStatus;
    
    // 신고한 회원 번호
    private int reportUserNo;
    
	/* 출력의 편의를 위해 만들어진 변수들 */
    
    // 유저 아이디
    private String userId;
    
    // 신고 사유
    private String reportReason;
    
    // 신고 제목 포스트 내용 앞에 20글자만 짤라서..
    private String reportTitle;
    
    // 각종 추가 정보를 뽑아내기 위한 Vo
    private UserVO userVO;
    private Comment comment;
    private UserVO commentUser;
    private Board board;
    
    // 유저의 정지상태
    private String blockEnabled;
}
