package com.yapp.realtime.home.socket.dictionary.session;

import java.util.Map;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
public interface SessionInfo<KEY> {
    Map<String, Object> get();

    Object getDetail(KEY key);

    boolean contains(Long familyId);
}
