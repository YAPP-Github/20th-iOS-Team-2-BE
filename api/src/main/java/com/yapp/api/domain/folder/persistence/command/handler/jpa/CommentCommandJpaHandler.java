package com.yapp.api.domain.folder.persistence.command.handler.jpa;

import com.yapp.api.domain.folder.persistence.command.handler.CommentCommandHandler;
import com.yapp.api.domain.folder.persistence.repository.CommentJpaRepository;
import com.yapp.supporter.entity.folder.comment.entity.Comment;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class CommentCommandJpaHandler implements CommentCommandHandler {
    private final CommentJpaRepository commentJpaRepository;

    public CommentCommandJpaHandler(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentJpaRepository.delete(comment);
    }
}
