package com.bit.codesquare.controller.notice;
 

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ReplyEchoHandler extends TextWebSocketHandler{
	List<WebSocketSession> sessions = new ArrayList<>(); //테스트용, 실제로는 map 사용하기
	//Map<String, WebSocketSession> userSessions = new HashMap<>();
	
	//클라이언트가 서버에 접속 성공했을때마다
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("afterConnectionEstablished:"+session);
		sessions.add(session);
//		String senderId = session.getId(); //세션으로 로그인 처리
//		userSessions.put(senderId, session);
	}
	
	//소켓에 메세지를 보냈을때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		System.out.println("handleTextMessage:"+session+" : " + message);		
		String senderId = session.getId();
		for (WebSocketSession sess: sessions) {
			sess.sendMessage(new TextMessage(senderId + ": " + message.getPayload()));
		}
	}
	
	//커넥션이 닫혔을때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("afterConnectionEstablished:"+session+ " : " + status);
	}	
	
}
