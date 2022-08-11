package com.yapp.api.domain.folder.persistence.command.handler.jpa;

import com.yapp.api.domain.folder.persistence.command.handler.CommentCommandHandler;
import com.yapp.api.domain.folder.persistence.command.repository.CommentCommand;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class CommentCommandJpaHandler implements CommentCommandHandler {
    private final CommentCommand commentCommand;

    public CommentCommandJpaHandler(CommentCommand commentCommand) {
        this.commentCommand = commentCommand;
    }

}
