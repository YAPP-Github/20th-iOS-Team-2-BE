package com.yapp.realtime.home.socket.dictionary;

import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.realtime.global.error.exception.RealTimeException;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author : daehwan2yo
 * Date : 2022/08/21
 * Info :
 **/
public class DefaultWebSocketSessionDictionary implements WebSocketSessionDictionary<Long> {
    // 후에 redis 로 옮겨야함
    private final ConcurrentMap<Long, WebSocketSession> sessionForUser = new ConcurrentHashMap<>();

    @Override
    public void addSession(Long key, WebSocketSession session) {
        sessionForUser.put(key, session);
    }

    @Override
    public WebSocketSession get(Long ses) {
        return sessionForUser.get(ses);
    }

    @Override
    public boolean isEmpty() {
        return sessionForUser.isEmpty();
    }

    @Override
    public void remove(Long key) {
        try {
            sessionForUser.remove(key);
        } catch (Exception e) {
            throw new RealTimeException(ErrorCode.SESSION_REMOVE_ERROR);
        }
    }
}
