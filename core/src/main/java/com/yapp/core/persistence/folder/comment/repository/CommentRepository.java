package com.yapp.core.persistence.folder.comment.repository;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByUserAndId(User user, Long fileId);

    List<Comment> findAllByFamilyAndFileId(Family family, Long fileId);
}
