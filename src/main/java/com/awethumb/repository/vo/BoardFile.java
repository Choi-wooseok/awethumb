package com.awethumb.repository.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardFile {
    // 게시판 파일 번호 
    private int boardFileNo;
    // 게시판 파일 시스템 이름 
    private String boardFileSysName;
    // 게시판 파일 원본 이름 
    private String boardFileOrgName;
    // 게시판 파일 경로 
    private String boardFilePath;
    // 게시판 파일 크기 
    private long boardFileSize;
    // 게시판 파일 등록 날짜 
    private Date boardFileRegDt;
    // 게시판 파일 확장자 
    private String boardFileExe;
    // 글 번호 
    private int postNo;
    
    // 이미지 URL
    private String url;
}
