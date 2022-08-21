package com.yapp.realtime.home.socket.dictionary;

import com.yapp.realtime.global.error.exception.RealTimeException;
import com.yapp.realtime.home.socket.dictionary.session.SessionInfo;
import com.yapp.supporter.error.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author : daehwan2yo
 * Date : 2022/08/21
 * Info :
 **/
@Component
public class DefaultWebSocketSessionDictionary implements SessionInfoDictionary<Long> {
    // 후에 redis 로 옮겨야함
    private final ConcurrentMap<Long, SessionInfo> sessionForUser = new ConcurrentHashMap<>();

    @Override
    public void addSession(Long key, SessionInfo session) {
        sessionForUser.put(key, session);
    }

    @Override
    public SessionInfo get(Long ses) {
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
