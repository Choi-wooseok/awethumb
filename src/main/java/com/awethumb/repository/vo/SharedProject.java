package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class SharedProject {
	// 프로젝트 번호 
    private int projectNo;

    // 공유된 유저 번호 
    private int sharedUserNo;
}
