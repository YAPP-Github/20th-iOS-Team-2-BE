package com.yapp.realtime.home.socket.dictionary;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public interface SessionDictionary<KEY, VALUE> {
	void addSession(KEY key, VALUE session);

	void remove(KEY key);

	VALUE get(KEY ses);

	boolean isEmpty();

    List<WebSocketSession> getMembers(KEY familyId);
}
