package com.yapp.core.entity.folder.comment.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
@DisplayName("[Unit] Comment")
class CommentTest {

    @Test
    void modify() {
        Comment 댓글 = new Comment();
        String as_is = "new";
        String to_be = "changed";

        댓글.modify(as_is);
        assertThat(댓글.getContent()).isNotEqualTo(to_be);

        댓글.modify(to_be);
        assertThat(댓글.getContent()).isEqualTo(to_be);
    }
}