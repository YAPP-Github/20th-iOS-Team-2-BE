package com.yapp.event.home.dictionary;

import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public class ConcurrentSessionDictionary implements WebSocketSessionDictionary {
	private final ConcurrentMap<String, WebSocketSession> sessionInMemory;

	public ConcurrentSessionDictionary(ConcurrentMap<String, WebSocketSession> sessionInMemory) {
		this.sessionInMemory = sessionInMemory;
	}

	@Override
	public void addSession(WebSocketSession session) {
		this.sessionInMemory.put(session.getId(), session);
	}

	@Override
	public WebSocketSession get(String ses) {
		return sessionInMemory.get(ses);
	}
}
