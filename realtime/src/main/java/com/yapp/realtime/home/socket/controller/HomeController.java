package com.yapp.realtime.home.socket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.realtime.global.error.exception.RealTimeException;
import com.yapp.realtime.home.model.request.GreetingEmojiRequest;
import com.yapp.realtime.home.model.request.GreetingMessageRequest;
import com.yapp.realtime.home.model.response.GreetingEmoji;
import com.yapp.realtime.home.model.response.GreetingMessage;
import com.yapp.realtime.home.socket.dictionary.SessionDictionary;
import com.yapp.realtime.home.socket.dictionary.session.SessionInfo;
import com.yapp.realtime.home.socket.service.GreetingService;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.yapp.supporter.constant.RealTimeConstant.SESSION_INFO_KEY;

/**
 * Author : daehwan2yo
 * Date : 2022/09/04
 * Info :
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
public class HomeController {
    private final SessionDictionary<Long, SessionInfo<String>> sessionDictionary;
    private final GreetingService greetingService;
    private final ObjectMapper objectMapper;

    @PostMapping("/family/{familyId}/greeting/message")
    public void sendMessage(
            @RequestBody GreetingMessageRequest request,
            @PathVariable(value = "familyId") Long familyId,
            @RequestHeader(value = "userId") String userId) {

        List<User> members = greetingService.findMembers(familyId);

        List<WebSocketSession> memberSessions = members.stream()
                .filter(member -> sessionDictionary.contains(member.getId()))
                .map(user -> (WebSocketSession) sessionDictionary.get(user.getId())
                        .getDetail(SESSION_INFO_KEY))
                .collect(Collectors.toList());

        long user = Long.parseLong(userId);

        memberSessions.forEach(member -> {
            try {
                member.sendMessage(new TextMessage(objectMapper.writeValueAsString(GreetingMessage.of(user, request.getContent()))));
            } catch (IOException e) {
                log.error("실시간 메시지 전송중 에러가 발생했습니다. {} :", member.getId(), e);
                throw new RealTimeException(ErrorCode.REALTIME_INTERNAL_ERROR);
            }
        });

        greetingService.save(user, request.getContent());
    }

    @PostMapping("/family/{familyId}/greeting/emoji")
    public void sendEmoji(
            @RequestBody GreetingEmojiRequest request,
            @PathVariable(value = "familyId") Long familyId,
            @RequestHeader(value = "userId") String userId) {
        List<User> members = greetingService.findMembers(familyId);

        List<WebSocketSession> memberSessions = members.stream()
                .map(user -> (WebSocketSession) sessionDictionary.get(user.getId())
                        .getDetail(SESSION_INFO_KEY))
                .collect(Collectors.toList());

        long user = Long.parseLong(userId);

        memberSessions.forEach(member -> {
            try {
                member.sendMessage(new TextMessage(objectMapper.writeValueAsString(GreetingEmoji.of(user, request.getEmojiNumber()))));
            } catch (IOException e) {
                log.error("실시간 이모지 전송중 에러가 발생했습니다. {} :", member.getId(), e);
                throw new RealTimeException(ErrorCode.REALTIME_INTERNAL_ERROR);
            }
        });
    }
}
