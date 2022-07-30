package com.yapp.realtime.websocket.dictionary;

import java.util.concurrent.ConcurrentMap;

import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public class ConcurrentSessionDictionary implements SessionDictionary {
	private final ConcurrentMap<String, Mono> sessionConcurrentMap;

	public ConcurrentSessionDictionary(ConcurrentMap<String, Mono> sessionConcurrentMap) {
		this.sessionConcurrentMap = sessionConcurrentMap;
	}

	@Override
	public void addSession(WebSocketSession session, Mono<Void> pipeline) {
		System.out.println("== ID : " + session.getId());
		System.out.println("== remoteAddress : " + session.getHandshakeInfo()
														  .getRemoteAddress());
		System.out.println("== uri : " + session.getHandshakeInfo()
												.getUri());
		System.out.println("== subProtocol : " + session.getHandshakeInfo()
														.getSubProtocol());
		sessionConcurrentMap.put(session.getId(), pipeline);
	}

}
