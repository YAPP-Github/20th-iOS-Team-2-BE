package com.yapp.api.domain.album.element.comment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.user.persistence.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Optional<Comment> findByUserAndId(User user, Long fileId);
}
