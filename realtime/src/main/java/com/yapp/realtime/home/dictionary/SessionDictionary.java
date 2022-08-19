package com.yapp.realtime.home.dictionary;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public interface SessionDictionary<KEY, VALUE> {
	void addSession(VALUE session);

	VALUE get(KEY ses);

	boolean isEmpty();
}
