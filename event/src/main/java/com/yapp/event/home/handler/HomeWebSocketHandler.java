package com.yapp.event.home.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.yapp.event.home.dictionary.SessionDictionary;
import com.yapp.event.home.response.HomeResponse;
import com.yapp.event.home.response.SocketResponse;
import com.yapp.event.home.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
@RequiredArgsConstructor
public class HomeWebSocketHandler implements WebSocketHandler {
	private final SessionDictionary sessionDictionary;
	private final HomeService homeService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Long userId = (Long) session.getAttributes()
							 .get("userId");

		// HomeResponse.HomeStatusInfo membersInfo = homeService.getRealTimeStatus(userId);

		//session.sendMessage(new TextMessage(SocketResponse.from(membersInfo)
		//												  .getAsJson()));

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
