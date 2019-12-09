package com.awethumb.websocket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.ChatDAO;
import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;

@Service
public class WebSocketServiceImpl implements WebSocketService {
	@Autowired
	ChatDAO dao;
	
	public int isRoom(Message message) {
		return dao.isRoom(message);
	}
	
	public int selectRoom(Message message) {
		return dao.selectRoom(message);
	}
	
	public int createRoom(Chatroom croom) {
		dao.createRoom(croom);
		return croom.getChatroomNo();
	}
	
	public int insertMessage(Message message) {
		return dao.insertMessage(message);
	}
	
	public List<Chatroom> selectAllChatRoom(int sendUser) {
		return dao.selectAllChatRoom(sendUser);
	}
	
	public int readMessageTime(Message message) {
		return dao.readMessageTime(message);
	}
	
}
