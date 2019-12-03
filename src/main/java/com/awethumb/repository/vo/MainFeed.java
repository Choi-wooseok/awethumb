package com.awethumb.repository.vo;

import java.util.List;

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

    // 유저 닉네임 
    private String userNickname;

    // 해시태그 번호 
    private String hashtagNo;
    
    // 해시태그 내용
    private String hashtagContent;
    
    // 댓글 갯수
    private int commentCount;
    
    // 좋아요 갯수
    private int likeCount;
    
    // 검색 키워드
    private String searchWord;
    
    // 댓글을 리스트로
    private List<Comment> commentList;
    
    // 해시태그를 리스트로
    private List<Hashtag> hashtagList;
}
