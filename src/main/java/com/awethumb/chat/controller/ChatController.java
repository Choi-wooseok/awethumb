package com.awethumb.chat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.chat.service.ChatService;
import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SearchUser;
import com.awethumb.repository.vo.SecurityUser;
import com.awethumb.repository.vo.UserVO;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	ChatService webService;
	
	
	// 웹소켓 처음 접속
	@RequestMapping("/chat_list.do")
	public List<Chatroom> socketClient(Authentication authentication) {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		return webService.selectAllChatRoom(user.getUser().getUserNo());
	}
	
	// 사용자가 해당 게시글에 읽기를 눌렀을 때
	@RequestMapping("/read_msg.do")
	public int readMsg(Message message) {
		return webService.readMessageTime(message);
	}
	
	// 닉네임 검색
	@RequestMapping("/searchNickname.do")
	public List<UserVO> searchNickname(@RequestBody SearchUser srchUser) {
		return webService.searchNickname(srchUser);
	}
	
	// 새로운 채팅방 개설 시 유저 아이디 가져오기
	@RequestMapping("/selectNickname.do")
	public String selectNickname(@RequestBody int userNo) {
		return webService.selectNickname(userNo);
	}
	
	// 페이징 더보기
	@RequestMapping("/select_message_more.do")
	public List<Message> selectMessageMore(@RequestBody Message message) {
		return webService.selectAllMessage(message);
	}
	
	
}
