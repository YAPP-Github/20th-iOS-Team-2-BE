package com.yapp.api.domain.album.comment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.comment.persistence.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
