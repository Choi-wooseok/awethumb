package com.awethumb.websocket.controller;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SearchUser;
import com.awethumb.repository.vo.SecurityUser;
import com.awethumb.repository.vo.UserVO;
import com.awethumb.websocket.service.WebSocketService;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	
	@Autowired
	WebSocketService webService;
	
	
	// 웹소켓 처음 접속
	@RequestMapping("/socket_client.do")
	public ModelAndView socketClient(Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		mav.addObject("chatList", webService.selectAllChatRoom(user.getUser().getUserNo()));
		mav.setViewName("/include/chat2");
		return mav;
	}
	
	// 사용자가 해당 게시글에 읽기를 눌렀을 때
	@RequestMapping("/read_msg.do")
	@ResponseBody
	public int readMsg(Message message) {
		System.out.println("MSG"  + message);
		return webService.readMessageTime(message);
	}
	
	// 닉네임 검색
	@RequestMapping("/searchNickname.do")
	@ResponseBody
	public List<UserVO> searchNickname(@RequestBody SearchUser srchUser) {
//		return null;
		System.out.println(srchUser);
		return webService.searchNickname(srchUser);
	}
	
	// 새로운 채팅방 개설 시 유저 아이디 가져오기
	@RequestMapping("/selectNickname.do")
	@ResponseBody
	public String selectNickname(@RequestBody int userNo) {
		System.out.println("안녕하십니까");
		System.out.println("user =>" + webService.selectNickname(userNo));
		return webService.selectNickname(userNo);
	}
	
	
}
