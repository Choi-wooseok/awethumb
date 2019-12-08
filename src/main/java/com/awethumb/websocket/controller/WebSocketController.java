package com.awethumb.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	
	@RequestMapping("/socket_client.do")
	public String socketClient() {
		return "/include/chat";
	}
}
