package com.yapp.core.persistence.folder.comment.handler;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CommentQueryHandler {
    Optional<Comment> findOne(Function<CommentRepository, Optional<Comment>> function);

    List<Comment> findAll(Function<CommentRepository, List<Comment>> function);
}
