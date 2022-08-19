package com.yapp.realtime.home.dictionary;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public class FamilySessionDictionary implements FamilyWebSocketSessionDictionary{
	private final ConcurrentMap<Long, WebSocketSession> sessionForUser = new ConcurrentHashMap<>();

	@Override
	public void addSession(WebSocketSession session) {
		Long userId = (Long) session.getAttributes()
							   .get("userId");

		sessionForUser.put(userId, session);
	}

	@Override
	public WebSocketSession get(Long userId) {
		return sessionForUser.get(userId);
	}

	@Override
	public boolean isEmpty() {
		return sessionForUser.isEmpty();
	}
}
