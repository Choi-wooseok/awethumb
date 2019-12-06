package com.awethumb.websocket.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/websocket")
public class WebSocketController {
	
	@RequestMapping("/websocket/socket_client.do")
	public void socketClient() {}
}
