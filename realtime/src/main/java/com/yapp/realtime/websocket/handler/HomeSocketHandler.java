package com.yapp.realtime.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
public class HomeSocketHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		// kafka pipeline 으로 만들어야함
		Flux<WebSocketMessage> map = session.receive()
											.doOnNext(WebSocketMessage::getPayload)
											.map(value -> session.textMessage("RECEIVED :: " + value));

		return session.send(map);
	}
}
