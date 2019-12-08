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

    // 보낸 시간 
    private Date sendTime;

    // 읽은 시간 
    private Date readTime;

    // 메세지 내용 
    private String messageContent;
    
    public static Message convertMessage(String source) {
		Message message = new Message();
		Gson gson = new Gson();
		message = gson.fromJson(source,  Message.class);
		return message;
	}
    
}
