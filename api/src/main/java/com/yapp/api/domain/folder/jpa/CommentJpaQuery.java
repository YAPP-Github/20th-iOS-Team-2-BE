package com.yapp.api.domain.folder.jpa;

import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentQuery;
import com.yapp.core.persistence.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface CommentJpaQuery extends JpaRepository<Comment, Long>, CommentQuery {

    Optional<Comment> findByUserAndId(User user, Long fileId);

    List<Comment> findAllByFamilyAndFileId(Family family, Long fileId);
}
