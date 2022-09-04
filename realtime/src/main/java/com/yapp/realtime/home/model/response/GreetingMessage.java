package com.yapp.realtime.home.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/28
 * Info :
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GreetingMessage {
    private Long userId;
    private String content;

    public static GreetingMessage of(Long userId, String content) {
        return new GreetingMessage(userId, content);
    }
}
