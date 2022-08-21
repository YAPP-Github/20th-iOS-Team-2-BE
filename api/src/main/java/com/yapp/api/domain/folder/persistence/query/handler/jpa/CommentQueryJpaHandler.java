package com.yapp.api.domain.folder.persistence.query.handler.jpa;

import com.yapp.api.domain.folder.persistence.query.handler.CommentQueryHandler;
import com.yapp.api.domain.folder.persistence.repository.CommentJpaRepository;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.comment.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class CommentQueryJpaHandler implements CommentQueryHandler {
    private final CommentJpaRepository commentJpaRepository;

    public CommentQueryJpaHandler(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }

    @Override
    public Optional<Comment> findOne(Long commentId) {
        return commentJpaRepository.findById(commentId);
    }

    @Override
    public List<Comment> findAll(
            Family family, File file) {
        return commentJpaRepository.findAllByFamilyAndFile(family, file);
    }
}
