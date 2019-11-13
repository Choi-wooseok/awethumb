package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class UserFile {
	
	private int userFileNo;

    // 회원 번호 
    private int userNo;

    // 회원 파일 시스템 이름 
    private String userFileSysName;

    // 회원 파일 원본 이름 
    private String userFileOrgName;

    // 회원 파일 경로 
    private String userFilePath;

    // 회원 파일 크기 
    private String userFileSize;

    // 회원 파일 확장자 
    private String userFileExe;

}
