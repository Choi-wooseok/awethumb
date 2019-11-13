package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Subscribe {
	    // 구독자 회원 번호 
	    private int subUserNo;
	    // 피구독자 회원 번호 
	    private int oppUserNo;
	    // 피드 여부 
	    private String feedEnabled;
	    // 알림 여부 
	    private String alarmEnabled;

}
