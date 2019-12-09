package com.awethumb.websocket.service;

import java.util.List;

import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;

public interface WebSocketService {
	int isRoom(Message message);
	int selectRoom(Message message);
	int createRoom(Chatroom croom);
	int insertMessage(Message message);
	List<Chatroom> selectAllChatRoom(int sendUser);
	int readMessageTime(Message message);
}
