package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class Chatroom {
	 private int chatroomNo;
	 private int unReadCnt;	// 읽지 않은 수
	 private int sendUser; // 보낸 사람 
	 private List<Message> messageList;
	 // 메세지 관련 유저
	 private UserVO user;
}
