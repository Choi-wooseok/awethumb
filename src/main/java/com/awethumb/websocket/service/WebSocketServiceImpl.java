package com.awethumb.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.awethumb.repository.dao.ChatDAO;
import com.awethumb.repository.vo.Message;

public class WebSocketServiceImpl implements WebSocketService {
	@Autowired
	ChatDAO dao;
	
	public int selectRoom(Message message) {
		return dao.selectRoom(message);
	}
	
	public void createRoom() {
		dao.insertRoom();
	}
	
}
