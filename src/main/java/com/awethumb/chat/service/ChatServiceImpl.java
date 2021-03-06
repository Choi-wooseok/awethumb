package com.awethumb.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.ChatDAO;
import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SearchUser;
import com.awethumb.repository.vo.UserVO;

@Service
public class ChatServiceImpl implements ChatService {
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
	
	public List<UserVO> searchNickname(SearchUser srchUser) {
		return dao.searchNickname(srchUser);
	}
	
	public String selectNickname(int userNo) {
		return dao.selectNickname(userNo);
	}
	
	public List<Message> selectAllMessage(Message message) {
		return dao.selectAllMessage(message);
	}
	
	public String selectUserImgPath(int userNo) {
		return dao.selectUserImgPath(userNo);
	}
}
