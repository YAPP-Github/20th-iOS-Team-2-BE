package com.yapp.allinone.socket.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : daehwan2yo
 * Date : 2022/08/23
 * Info :
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GreetingMessageRequest {
    private String content;
}
