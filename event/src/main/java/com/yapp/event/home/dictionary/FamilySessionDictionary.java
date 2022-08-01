package com.yapp.event.home.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public class FamilySessionDictionary implements FamilyWebSocketSessionDictionary{
	private final ConcurrentMap<Long, List<String>> sessionsForFamily = new ConcurrentHashMap<>();

	@Override
	public void addSession(WebSocketSession session) {
		;
	}

	@Override
	public WebSocketSession get(String ses) {
		return null;
	}

	@Override
	public List<String> get(Long familyId) {
		if(!sessionsForFamily.containsKey(familyId)) {
			List<String> list = new ArrayList<>();
			sessionsForFamily.put(familyId, list);
			return list;
		}

		return sessionsForFamily.get(familyId);
	}
}
