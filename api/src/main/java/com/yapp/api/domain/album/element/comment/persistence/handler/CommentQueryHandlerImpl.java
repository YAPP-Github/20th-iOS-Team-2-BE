package com.yapp.api.domain.album.element.comment.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.album.element.comment.persistence.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentQueryHandlerImpl implements CommentQueryHandler {
	private final CommentRepository commentRepository;

	@Override
	public Optional<Comment> findOne(Function<CommentRepository, Optional<Comment>> function) {
		return function.apply(commentRepository);
	}
}
