package com.awethumb.websocket.service;

import java.util.List;
import java.util.Map;

import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SearchUser;
import com.awethumb.repository.vo.UserVO;

public interface WebSocketService {
	int isRoom(Message message);
	int selectRoom(Message message);
	int createRoom(Chatroom croom);
	int insertMessage(Message message);
	List<Chatroom> selectAllChatRoom(int sendUser);
	int readMessageTime(Message message);
	List<UserVO> searchNickname(SearchUser srchUser);
	String selectNickname(int userNo);
}
