package com.yapp.api.domain.album.element.comment.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Optional<Comment> findByUserAndId(User user, Long fileId);

	List<Comment> findAllByFamilyAndFileId(Family family, Long fileId);
}
