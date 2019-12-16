package com.awethumb.repository.vo;

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
    
    // 처음 메세지 보냈을 경우 보낸 사람의 프로필 이미지 경로
    private String userImgPath;
    
}
