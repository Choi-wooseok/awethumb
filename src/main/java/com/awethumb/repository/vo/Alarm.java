package com.awethumb.repository.vo;

import java.util.Date;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Alarm {
	private String userNickname;
	
	// 알림 번호 
    private Integer alarmNo;

    // 수신 유저 번호 알림 받는 유저 번호
    private Integer receiveUserNo;

    // 발신 유저 번호 알림 보내는 유저 번호
    private Integer sendUserNo;

    // 게시글 번호 
    private Integer boardNo;

    // 댓글 번호 
    private Integer commentNo;

    // 프로젝트 번호 
    private Integer projectNo;

    // 알림 시간 
    private Date regDate;

    // 확인 여부 Y/N
    private String alarmCheck;

    // 알림 타입 1. 구독 2. 좋아요 3. 댓글 4. 공유 수락
    private int alarmType;
    
    public static Alarm convertMessage(String source) {
		Alarm alarm = new Alarm();
		Gson gson = new Gson();
		alarm = gson.fromJson(source,  Alarm.class);
		return alarm;
	}
}
