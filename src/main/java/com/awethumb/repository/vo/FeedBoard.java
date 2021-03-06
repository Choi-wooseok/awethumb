package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class FeedBoard {
	 // 글 번호 
    private int postNo;

    // 프로젝트 번호 
    private int projectNo;
    
    // 유저 번호
    private int userNo;

    // 글 내용 
    private String postContent;
    
    // 글 공개 여부 
    private String projectPublicEnabled;

    // 해시태그 번호 
    private String hashtagNo;
    
    // 유저 이름
    private String userNickName;
    
    // 더미
    private int xCoord;
    
    // 댓글리스트
    private List<Comment> commentList;
    
    // 좋아요 상태
    private int likeCheck;
    // 좋아요 갯수
    private int likeCount;
    
    // 로그인유저
    private int subUserNo;
}







