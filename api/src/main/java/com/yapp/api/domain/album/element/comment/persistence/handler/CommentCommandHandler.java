package com.yapp.api.domain.album.element.comment.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.album.element.comment.persistence.repository.CommentRepository;

public interface CommentCommandHandler {
	void create(Consumer<CommentRepository> consumer);
}
