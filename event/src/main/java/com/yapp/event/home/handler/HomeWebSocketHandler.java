package com.yapp.event.home.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.yapp.event.home.dictionary.SessionDictionary;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public class HomeWebSocketHandler implements WebSocketHandler {
	private final SessionDictionary sessionDictionary;

	public HomeWebSocketHandler(SessionDictionary sessionDictionary) {
		this.sessionDictionary = sessionDictionary;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		session.sendMessage(new TextMessage("Welcome"));
		sessionDictionary.addSession(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
