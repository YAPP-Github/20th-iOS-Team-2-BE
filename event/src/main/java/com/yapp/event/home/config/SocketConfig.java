package com.yapp.event.home.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;

import com.yapp.event.home.dictionary.ConcurrentSessionDictionary;
import com.yapp.event.home.dictionary.SessionDictionary;
import com.yapp.event.home.handler.HomeWebSocketHandler;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/home")
				.setAllowedOrigins("*");
	}

	@Bean
	public SessionDictionary sessionDictionary() {
		return new ConcurrentSessionDictionary(new ConcurrentHashMap<>());
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new HomeWebSocketHandler(sessionDictionary());
	}
}
