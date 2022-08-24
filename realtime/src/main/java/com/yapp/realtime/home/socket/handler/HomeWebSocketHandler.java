package com.yapp.realtime.home.socket.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.realtime.global.validator.RealTimeValidator;
import com.yapp.realtime.home.model.request.GreetingMessageRequest;
import com.yapp.realtime.home.response.HomeResponse;
import com.yapp.realtime.home.socket.dictionary.SessionDictionary;
import com.yapp.realtime.home.socket.dictionary.session.DefaultSessionInfo;
import com.yapp.realtime.home.socket.dictionary.session.SessionInfo;
import com.yapp.realtime.home.socket.repository.UserRepository;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.supporter.error.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.List;
import java.util.Map;

import static com.yapp.supporter.constant.RealTimeConstant.DEFAULT_SESSION_KEY;
import static com.yapp.supporter.constant.RealTimeConstant.SESSION_FAMILY_KEY;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info :
 **/
@Component
@RequiredArgsConstructor
public class HomeWebSocketHandler implements WebSocketHandler {
    private final SessionDictionary<Long, SessionInfo<String>> sessionDictionary;

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes()
                .get(DEFAULT_SESSION_KEY);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PersistenceException(ErrorCode.USER_NOT_FOUND));

        sessionDictionary.addSession(userId, DefaultSessionInfo.of(session, user));

        List<User> members = userRepository.findAllByFamily(user.getFamily());

        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(HomeResponse.HomeStatusInfo.of(user, members))));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Map<String, Object> sessionInfos = session.getAttributes();
        RealTimeValidator.familyId(sessionInfos.containsKey(SESSION_FAMILY_KEY), ErrorCode.NO_FAMILY_IN_HEADER);

        Long familyId = (Long) sessionInfos.get(SESSION_FAMILY_KEY);

        List<WebSocketSession> memberSessions = sessionDictionary.getMembers(familyId);

        GreetingMessageRequest greetingMessageRequest = objectMapper.readValue((String) message.getPayload(), GreetingMessageRequest.class);

        // 내 상태 변경

        // members에 전송
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
