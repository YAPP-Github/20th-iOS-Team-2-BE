package com.yapp.realtime.home.socket.config;

import com.yapp.realtime.home.socket.dictionary.DefaultWebSocketSessionDictionary;
import com.yapp.realtime.home.socket.handler.HomeWebSocketHandler;
import com.yapp.realtime.home.socket.interceptor.HomeHandShakeInterceptor;
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

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/home/{userId}")
                .addInterceptors(handshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new HomeWebSocketHandler(new DefaultWebSocketSessionDictionary());
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new HomeHandShakeInterceptor();
    }
}
