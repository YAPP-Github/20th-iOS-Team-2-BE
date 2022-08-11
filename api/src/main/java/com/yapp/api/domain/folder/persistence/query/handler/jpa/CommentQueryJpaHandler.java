package com.yapp.api.domain.folder.persistence.query.handler.jpa;

import com.yapp.api.domain.folder.persistence.query.handler.CommentQueryHandler;
import com.yapp.api.domain.folder.persistence.query.repository.CommentQuery;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.folder.comment.entity.Comment;
import com.yapp.core.entity.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class CommentQueryJpaHandler implements CommentQueryHandler {
    private final CommentQuery commentQuery;

    public CommentQueryJpaHandler(CommentQuery commentQuery) {
        this.commentQuery = commentQuery;
    }

    @Override
    public Optional<Comment> findOne(
            User user, Long fileId) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll(
            Family family, Long fileId) {
        return null;
    }
}
