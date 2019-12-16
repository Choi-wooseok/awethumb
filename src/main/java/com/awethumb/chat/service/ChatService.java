package com.awethumb.chat.service;

import java.util.List;
import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SearchUser;
import com.awethumb.repository.vo.UserVO;

public interface ChatService {
	int isRoom(Message message);
	int selectRoom(Message message);
	int createRoom(Chatroom croom);
	int insertMessage(Message message);
	List<Chatroom> selectAllChatRoom(int sendUser);
	int readMessageTime(Message message);
	List<UserVO> searchNickname(SearchUser srchUser);
	String selectNickname(int userNo);
	List<Message> selectAllMessage(Message message);
	String selectUserImgPath(int userNo);
}
