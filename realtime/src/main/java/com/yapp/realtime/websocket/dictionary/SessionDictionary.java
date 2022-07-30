package com.yapp.realtime.websocket.dictionary;

import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public interface SessionDictionary {

	void addSession(WebSocketSession session, Mono<Void> pipeline);
}
