package com.yapp.realtime.home.dictionary;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public interface FamilyWebSocketSessionDictionary extends SessionDictionary<Long, WebSocketSession> {

	WebSocketSession get(Long familyId);
}
