package com.yapp.api.domain.album.element.comment.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.comment.persistence.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentCommandHandlerImpl implements CommentCommandHandler {
	private final CommentRepository commentRepository;

	@Override
	public void create(Consumer<CommentRepository> consumer) {
		consumer.accept(commentRepository);
	}

	@Override
	public void remove(Consumer<CommentRepository> consumer) {
		consumer.accept(commentRepository);
	}
}
