package com.awethumb.websocket.service;

import com.awethumb.repository.vo.Message;

public interface WebSocketService {
	int selectRoom(Message message);
	void createRoom();
}
