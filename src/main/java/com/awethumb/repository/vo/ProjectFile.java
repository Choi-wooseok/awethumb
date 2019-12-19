package com.awethumb.repository.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectFile {
	 // 프로젝트 파일 번호 
    private int projectFileNo;

    // 프로젝트 파일 시스템 이름 
    private String projectFileSysName;

    // 프로젝트 파일 원본 이름 
    private String projectFileOrgName;

    // 프로젝트 파일 경로 
    private String projectFilePath;

    // 프로젝트 파일 크기 
    private long projectFileSize;

    // 프로젝트 파일 등록 날짜 
    private Date projectFileRegDt;

    // 프로젝트 파일 확장자 
    private String projectFileExe;

    // 프로젝트 번호 
    private int projectNo;
    
    // 프로젝트 유저 번호
    private int projectUserNo;
}
