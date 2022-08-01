package com.yapp.event.home.dictionary;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public interface SessionDictionary {
	void addSession(WebSocketSession session);

	WebSocketSession get(String ses);
}
