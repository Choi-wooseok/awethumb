package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class feedBoard {
	 // 글 번호 
    private int postNo;

    // 프로젝트 번호 
    private int projectNo;

    // 글 내용 
    private String postContent;
    
    // 글 공개 여부 
    private String postPublicEnabled;

    // 해시태그 번호 
    private String hashtagNo;
    
    // 유저 이름
    private String userNickName;
    
    // 게시판 사진 경로
    private String boardFilePath;

    // 유저 사진 경로 
    private String userFilePath;
    
    // 게시글 조회수
    private int viewCnt;
    
    // 더미
    private int xCoord;

    
}
