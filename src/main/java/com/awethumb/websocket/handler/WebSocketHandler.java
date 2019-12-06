package com.awethumb.websocket.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.awethumb.repository.dao.ChatDAO;

@Component("soc_client")
public class WebSocketHandler extends TextWebSocketHandler {
	@Autowired
	ChatDAO dao;
	
	private List<WebSocketSession> connectedUsers;
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	public WebSocketHandler() {
		this.connectedUsers = new ArrayList<>();
	}
	
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// WebSocketSession => 웹소켓 연결한 브라우저의 정보를 가지고 있다. 페이지가 이동됬을 경우 웹소켓이 끊긴다.
		System.out.println(session.getId() + " 연결되었음..");
		users.put(session.getId(), session);
		connectedUsers.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		System.out.println("보낸 아이디 : " + session.getId());
		System.out.println("보낸 데이터 : " + message.getPayload());
		session.sendMessage(new TextMessage("에코 메세지 : " + message.getPayload()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + " 연결 종료되었음..");
	}
}
