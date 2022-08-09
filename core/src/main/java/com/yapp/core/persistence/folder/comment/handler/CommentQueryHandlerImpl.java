package com.yapp.core.persistence.folder.comment.handler;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CommentQueryHandlerImpl implements CommentQueryHandler {
    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> findOne(Function<CommentRepository, Optional<Comment>> function) {
        return function.apply(commentRepository);
    }

    @Override
    public List<Comment> findAll(Function<CommentRepository, List<Comment>> function) {
        return function.apply(commentRepository);
    }
}
