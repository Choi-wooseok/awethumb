package com.awethumb.websocket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SecurityUser;
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
		mav.setViewName("/include/chat");
		return mav;
	}
	
	// 사용자가 해당 게시글에 읽기를 눌렀을 때
	@RequestMapping("/read_msg.do")
	@ResponseBody
	public int readMsg(Message message) {
		return webService.readMessageTime(message);
	}
}
