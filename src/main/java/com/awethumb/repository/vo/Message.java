package com.awethumb.repository.vo;

import java.util.Date;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Message {
	 // 메세지 번호 
    private int messageNo;

    // 채팅방 번호 
    private int chatroomNo;

    // 보내는 사람 
    private int sendUser;

    // 받는 사람 
    private int takeUser;
    
    private String userNickname;

    // 보낸 날짜 
    private String sendDate;
    
    // 보낸 시간
    private String sendTime;

    // 읽은 시간 
    private String readTime;

    // 메세지 내용 
    private String messageContent;
    
    // 페이징
    private Page page;
    
}
