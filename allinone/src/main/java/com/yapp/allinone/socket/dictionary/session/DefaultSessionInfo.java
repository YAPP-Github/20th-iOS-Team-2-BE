package com.yapp.allinone.socket.dictionary.session;

import com.yapp.allinone.common.exception.RealTimeException;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static com.yapp.supporter.constant.RealTimeConstant.*;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
public final class DefaultSessionInfo implements SessionInfo<String> {
    private final Map<String, Object> sessionMap = new HashMap<>();

    public static DefaultSessionInfo of(final WebSocketSession session, final User user) {
        return new DefaultSessionInfo(session, user);
    }

    public DefaultSessionInfo(WebSocketSession session, User user) {
        sessionMap.put(SESSION_INFO_KEY, session);
        sessionMap.put(SESSION_USER_KEY, user);
        sessionMap.put(SESSION_FAMILY_KEY, user.getFamily().getId());
    }

    @Override
    public Map<String, Object> get() {
        return sessionMap;
    }

    @Override
    public Object getDetail(String s) {
        if (!sessionMap.containsKey(s)) {
            throw new RealTimeException(ErrorCode.REALTIME_INTERNAL_ERROR);
        }
        return sessionMap.get(s);
    }

    public boolean contains(Long familyId) {
        return sessionMap.containsValue(familyId);
    }
}
