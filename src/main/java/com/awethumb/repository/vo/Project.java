package com.awethumb.repository.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Project {
	 // 프로젝트 번호 
    private int projectNo;

    // 프로젝트 제목 
    private String projectTitle;

    // 회원 번호 
    private int userNo;
    
    // 회원 닉네임
    private String userNickname;

    // 레이블 색상 
    private String lableColor;

    // 프로젝트 공개 여부 
    private String projectPublicEnabled;

    // 공유 유저 리스트 
    private String sharedUserList;

    // 카테고리 번호 
    private int categoryNo;
    
    // 프로젝트 종류
    // 1 -> 진행중인 프로젝트
    // 2 -> 공유된 프로젝트
    // 3 -> 저장된 프로젝트
    private int projectType;
    
    private MultipartFile projectFile;
}
