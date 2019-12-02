package com.awethumb.repository.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectSubscribe {
	 // 회원 번호 
    private int userNo;

    // 프로젝트 번호 
    private int projectNo;
    
    // 저장된 날짜
    private Date savedDate;
}
