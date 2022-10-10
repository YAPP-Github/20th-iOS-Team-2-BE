package com.yapp.allinone.socket.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/28
 * Info :
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GreetingEmoji {
    private Long userId;
    private int emojiNumber;

    public static GreetingEmoji of(Long userId, int emoji) {
        return new GreetingEmoji(userId, emoji);
    }
}
