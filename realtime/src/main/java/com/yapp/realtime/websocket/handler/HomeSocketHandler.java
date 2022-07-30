package com.yapp.realtime.websocket.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.yapp.realtime.websocket.dictionary.SessionDictionary;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
public class HomeSocketHandler implements WebSocketHandler {

	private final SessionDictionary sessionDictionary;
	private final HomeConsumer homeConsumer;

	public HomeSocketHandler(SessionDictionary sessionDictionary, HomeConsumer homeConsumer) {
		this.sessionDictionary = sessionDictionary;
		this.homeConsumer = homeConsumer;
	}

	/**
	 * make connection
	 * @param session
	 * @return
	 */
	@Override
	public Mono<Void> handle(WebSocketSession session) {

		Flux<WebSocketMessage> receive = session.receive()
												.doOnNext(WebSocketMessage::getPayloadAsText)
												.map(value -> session.textMessage(value.getPayloadAsText()));

		return session.send(receive);
	}

}
