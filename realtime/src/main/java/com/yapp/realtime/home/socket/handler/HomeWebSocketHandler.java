package com.yapp.realtime.home.socket.handler;

import com.yapp.realtime.home.socket.dictionary.WebSocketSessionDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import static com.yapp.supporter.constant.RealTimeConstant.DEFAULT_SESSION_KEY;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info :
 **/
@RequiredArgsConstructor
public class HomeWebSocketHandler implements WebSocketHandler {
    private final WebSocketSessionDictionary<Long> sessionDictionary;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes()
                .get(DEFAULT_SESSION_KEY);

        sessionDictionary.addSession(userId, session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessionDictionary.remove((Long) session.getAttributes()
                .get(DEFAULT_SESSION_KEY));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
