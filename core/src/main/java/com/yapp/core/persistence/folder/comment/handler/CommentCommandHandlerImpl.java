package com.yapp.core.persistence.folder.comment.handler;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

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
