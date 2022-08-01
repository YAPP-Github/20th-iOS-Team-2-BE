package com.yapp.event.home.dictionary;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public interface FamilyWebSocketSessionDictionary extends SessionDictionary {

	List<String> get(Long familyId);
}
