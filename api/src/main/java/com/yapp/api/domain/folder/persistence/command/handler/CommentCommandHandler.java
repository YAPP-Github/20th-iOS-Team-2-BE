package com.yapp.api.domain.folder.persistence.command.handler;

import com.yapp.supporter.entity.folder.comment.entity.Comment;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface CommentCommandHandler {
    Comment save(Comment build);

    void remove(Comment comment);
}
