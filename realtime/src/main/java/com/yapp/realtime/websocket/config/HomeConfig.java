package com.yapp.realtime.websocket.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.yapp.realtime.websocket.dictionary.ConcurrentSessionDictionary;
import com.yapp.realtime.websocket.dictionary.SessionDictionary;
import com.yapp.realtime.websocket.handler.HomeConsumer;
import com.yapp.realtime.websocket.handler.HomeSocketHandler;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
@Configuration
@EnableWebFlux
public class HomeConfig {

	private final HomeConsumer homeConsumer;

	public HomeConfig(HomeConsumer homeConsumer) {
		this.homeConsumer = homeConsumer;
	}

	@Bean
	public SessionDictionary sessionDictionary() {
		return new ConcurrentSessionDictionary(new ConcurrentHashMap<>());
	}

	@Bean
	public HandlerMapping handlerMapping() {
		Map<String, WebSocketHandler> map = new HashMap<>();
		map.put("/home", new HomeSocketHandler(sessionDictionary(), homeConsumer));

		return new SimpleUrlHandlerMapping(map, Ordered.HIGHEST_PRECEDENCE);
	}
}
