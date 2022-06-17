package com.yapp.api.domain.album.element.comment.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.album.element.comment.persistence.repository.CommentRepository;

public interface CommentQueryHandler {
	Optional<Comment> findOne(Function<CommentRepository, Optional<Comment>> function);
}
