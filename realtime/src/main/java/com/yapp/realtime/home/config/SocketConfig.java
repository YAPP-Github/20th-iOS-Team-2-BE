package com.yapp.realtime.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.yapp.realtime.home.dictionary.FamilySessionDictionary;
import com.yapp.realtime.home.dictionary.SessionDictionary;
import com.yapp.realtime.home.handler.HomeWebSocketHandler;
import com.yapp.realtime.home.interceptor.HomeHandShakeInterceptor;
import com.yapp.realtime.home.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class SocketConfig implements WebSocketConfigurer {
	private final HomeService homeService;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/home/{userId}")
				.addInterceptors(handshakeInterceptor())
				.setAllowedOrigins("*");
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new HomeWebSocketHandler(sessionDictionary(), homeService);
	}

	@Bean
	public HandshakeInterceptor handshakeInterceptor() {
		return new HomeHandShakeInterceptor();
	}

	@Bean
	public SessionDictionary<Long, WebSocketSession> sessionDictionary() {
		return new FamilySessionDictionary();
	}

	@Bean
	public FamilySessionDictionary familySessionDictionary() {
		return new FamilySessionDictionary();
	}
}
