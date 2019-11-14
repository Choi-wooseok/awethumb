package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class MainFeed {
	 // 글 번호 
    private int postNo;

    // 프로젝트 번호 
    private int projectNo;

    // 글 내용 
    private String postContent;

    // x좌표 
    private int xCoord;

    // y좌표 
    private int yCoord;

    // 가로 
    private int width;

    // 세로 
    private int hight;

    // 글 공개 여부 
    private String postPublicEnabled;

    // 해시태그 번호 
    private String hashtagNo;
    
    // 해시태그 번호 
    private String userNickname;
    
    // 해시태그 번호 
    private String hashtagContent;
}
