package com.yapp.core.persistance.album.element.comment.persistence.handler;

import java.util.function.Consumer;
import java.util.function.Function;

import com.yapp.core.persistance.album.element.comment.persistence.entity.Comment;
import com.yapp.core.persistance.album.element.comment.persistence.repository.CommentRepository;

public interface CommentCommandHandler {
	Long create(Function<CommentRepository, Comment> consumer);

	void remove(Consumer<CommentRepository> consumer);
}
