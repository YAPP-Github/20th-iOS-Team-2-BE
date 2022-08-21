package com.yapp.realtime.home.socket.dictionary;

import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/07/30
 * Info : 
 **/
public interface WebSocketSessionDictionary <KEY> extends SessionDictionary<KEY, WebSocketSession> {}
