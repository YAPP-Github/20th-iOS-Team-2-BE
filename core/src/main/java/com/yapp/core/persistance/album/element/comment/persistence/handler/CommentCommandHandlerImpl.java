package com.yapp.core.persistance.album.element.comment.persistence.handler;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.album.element.comment.persistence.entity.Comment;
import com.yapp.core.persistance.album.element.comment.persistence.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentCommandHandlerImpl implements CommentCommandHandler {
	private final CommentRepository commentRepository;

	@Override
	public Long create(Function<CommentRepository, Comment> consumer) {
		return consumer.apply(commentRepository)
					   .getId();
	}

	@Override
	public void remove(Consumer<CommentRepository> consumer) {
		consumer.accept(commentRepository);
	}
}
