package com.awethumb.alarm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.awethumb.alarm.service.AlarmService;
import com.awethumb.repository.vo.Alarm;
import com.awethumb.repository.vo.SecurityUser;

@Component("AlarmHandler")
public class AlarmHandler extends TextWebSocketHandler{
	// userNo 와 session
	private Map<Integer, WebSocketSession> users = new HashMap<Integer, WebSocketSession>();
	
	@Autowired
	private AlarmService service;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.put(getId(session), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메세지를 객체로 변환시켜준다
		Alarm alarm = Alarm.convertMessage(message.getPayload());
		
		// 알림을 받는 유저
		List<Integer> rUserNoList = new ArrayList<>();
		switch(alarm.getAlarmType()) {
			case 1:
				rUserNoList.add(alarm.getReceiveUserNo());
				break;
			case 2: 
				rUserNoList.add(service.selectUserNoByBoardNo(alarm.getBoardNo()));
				break;
			case 3:
				rUserNoList.add(service.selectUserNoByCommentNo(alarm.getCommentNo()));
				break;
			case 4:
				rUserNoList = service.selectUserNoListByProjectNo(alarm.getProjectNo());
				break;
		}
		// 알림을 받는 유저 수만큼 반복해준다
		for(int rUserNo : rUserNoList) {
			alarm.setReceiveUserNo(rUserNo);
			service.insertAlarm(alarm);
			
			WebSocketSession ruSession = users.get(rUserNo);
			// 알림을 받는 유저가 접속해있을 시
			if (ruSession != null) {
				ruSession.sendMessage(new TextMessage(Integer.toString(service.selectAlarmCnt(rUserNo))));
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(getId(session));
	}
	
	
	private int getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		
		 for (String key : httpSession.keySet()) {
	            Object value = httpSession.get(key);
	            System.out.println("[key]:" + key + ", [value]:" + value);
	     }
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
	
}
