package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;

public interface ChatDAO {
	int selectRoom(Message message);
	void createRoom(Chatroom croom);
	List<Chatroom> selectAllChatRoom(int sendUser);
	int insertMessage(Message message);
	int readMessageTime(Message message);
	int isRoom(Message message);
	
}
