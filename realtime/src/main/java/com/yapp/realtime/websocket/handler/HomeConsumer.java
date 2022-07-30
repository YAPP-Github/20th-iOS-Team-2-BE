package com.yapp.realtime.websocket.handler;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.core.codec.DataBufferEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
@Service
public class HomeConsumer {

	@KafkaListener(topics= "test", groupId = "foo")
	public String consume(String message) throws IOException {
		return message;
	}
}
