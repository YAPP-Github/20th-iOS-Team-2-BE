package com.yapp.realtime.home.socket.dictionary;

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
}
