package com.yapp.core.persistance.album.element.comment.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.album.element.comment.persistence.entity.Comment;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.user.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Optional<Comment> findByUserAndId(User user, Long fileId);

	List<Comment> findAllByFamilyAndFileId(Family family, Long fileId);
}
