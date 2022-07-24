package com.yapp.realtime.websocket.handler;

import java.io.IOException;

import org.springframework.core.codec.DataBufferEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
@Service
public class HomeController {
	@KafkaListener(topics= "test", groupId = "foo")
	public void consume(String message) throws IOException {

		System.out.println(message);
		//session.send(Flux.just(session.textMessage(message)));

	}
}
