package com.yapp.allinone.domain.folder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public class CommentResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Create {
        private String content;
    }
}
