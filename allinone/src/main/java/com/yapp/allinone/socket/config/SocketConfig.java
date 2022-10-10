package com.yapp.allinone.socket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.allinone.domain.user.persistence.query.handler.UserQueryHandler;
import com.yapp.allinone.socket.dictionary.DefaultWebSocketSessionDictionary;
import com.yapp.allinone.socket.dictionary.SessionDictionary;
import com.yapp.allinone.socket.handler.HomeWebSocketHandler;
import com.yapp.allinone.socket.interceptor.HomeHandShakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info :
 **/
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class SocketConfig implements WebSocketConfigurer {
    private final UserQueryHandler userQueryHandler;
    private final ObjectMapper objectMapper;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/home/{userId}")
                .addInterceptors(handshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new HomeWebSocketHandler(sessionDictionary(), userQueryHandler, objectMapper);
    }

    @Bean
    public SessionDictionary sessionDictionary() {
        return new DefaultWebSocketSessionDictionary();
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new HomeHandShakeInterceptor();
    }
}
