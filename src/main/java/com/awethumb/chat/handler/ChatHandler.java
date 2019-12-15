package com.awethumb.chat.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.awethumb.chat.service.ChatService;
import com.awethumb.repository.vo.Chatroom;
import com.awethumb.repository.vo.Message;
import com.awethumb.repository.vo.SecurityUser;
import com.google.gson.Gson;

@Component("ChatHandler")
public class ChatHandler extends TextWebSocketHandler {
	
	@Autowired
	ChatService socService;
	
	// 연결된 유저 목록
	private List<WebSocketSession> connectedUsers;
	
	// 멀티쓰레드 유저 등록 - 세션에 등록 된 유저 정보를 구분 짓기 위한 멤버변수
	private Map<Integer, WebSocketSession> users = new ConcurrentHashMap<Integer, WebSocketSession>();
	
	public ChatHandler() {
		this.connectedUsers = new ArrayList<>();
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// WebSocketSession => 웹소켓 연결한 브라우저의 정보를 가지고 있다. 페이지가 이동됬을 경우 웹소켓이 끊긴다.
		int senderId = getId(session);
		users.put(senderId, session);
		connectedUsers.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		
		
//		 ConvertMessage에 sendUser나 takeUser가 ""일 경우에 convert 하지 않도록 처리해야 함.
		 Message messageVO = new Gson().fromJson(message.getPayload(), Message.class);
		
		
		if (messageVO.getSendUser() == 0 || messageVO.getSendUser() == 0) return;
		
		
		
		// 최초 방 생성 여부
		boolean flag = false;
		
		
		// 사용자 방 조회
		int roomNo = 0;
//		// 사용자 간 방이 없다면 방 생성
		if (socService.isRoom(messageVO) == 0) {
			roomNo = socService.createRoom(new Chatroom());
			flag = true;
		} else {
			roomNo = socService.selectRoom(messageVO);
		}
		
		
		// 방 번호 추가
		messageVO.setChatroomNo(roomNo);
		
		// 채팅 내용 저장
		int sendResult = socService.insertMessage(messageVO);
		
		// 전송한 메세지가 저장이 되지 않았다면 예외 발생 시키고 상대방 소켓에 전송 하지 않음
		if (sendResult == 0) throw new Exception("message send fail");
		
//		
		String userNick = socService.selectNickname(messageVO.getSendUser());
		
		// 세션 체크 후에 받을 사람이 있다면 받을 사람에게 전송
		for (WebSocketSession socSession : connectedUsers) {
			
			int id = getId(socSession);
	         // 받는사람
	        if (id == messageVO.getTakeUser()) {
	        	if (flag) messageVO.setUserNickname(userNick);
	            Gson gson = new Gson();
	            String msgJson = gson.toJson(messageVO);
	            socSession.sendMessage(new TextMessage(msgJson));
	        }
	        // 처음 개설한 방 일 경우 보낸사람에게도 방 번호 알려줘야 함 
	        if (flag) {
	        	 if (id == messageVO.getSendUser()) {
	 	            socSession.sendMessage(new TextMessage("{\"roomNo\" : " + roomNo + ", \"takeUser\" : " + messageVO.getTakeUser() + "}"));
	 	        }
	        	
	        }
		}
		
		

	}
	
	private int getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		
		SecurityContextImpl pp = (SecurityContextImpl) httpSession.get("SPRING_SECURITY_CONTEXT");
		int socLoginUserNo = 0;
		if (pp != null) { 
			Authentication auth = pp.getAuthentication();
			SecurityUser secUser = (SecurityUser) auth.getPrincipal();
			if (secUser.getUser() != null) {
				socLoginUserNo = secUser.getUser().getUserNo();
			}
		
		}
		
		return socLoginUserNo;
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(getId(session));
		connectedUsers.remove(session);
	}
}
