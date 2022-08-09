package com.yapp.core.persistence.folder.comment.handler;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentRepository;

import java.util.function.Consumer;
import java.util.function.Function;

public interface CommentCommandHandler {
    Long create(Function<CommentRepository, Comment> consumer);

    void remove(Consumer<CommentRepository> consumer);
}
