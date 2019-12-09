package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class Chatroom {
	 private int chatroomNo;
	 private List<Message> messageList;
}
