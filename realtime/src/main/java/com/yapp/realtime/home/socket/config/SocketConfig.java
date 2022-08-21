package com.yapp.realtime.home.socket.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.yapp.realtime.home.socket.dictionary.DefaultWebSocketSessionDictionary;
import com.yapp.realtime.home.socket.dictionary.SessionDictionary;
import com.yapp.realtime.home.socket.handler.HomeWebSocketHandler;
import com.yapp.realtime.home.socket.interceptor.HomeHandShakeInterceptor;
import com.yapp.realtime.home.socket.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/home/{userId}")
                .addInterceptors(handshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new HomeWebSocketHandler(sessionDictionary(), userRepository, objectMapper());
    }

    @Bean
    public SessionDictionary sessionDictionary() {
        return new DefaultWebSocketSessionDictionary();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new HomeHandShakeInterceptor();
    }
}
