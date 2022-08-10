package com.yapp.api.domain.folder.persistence.jpa;

import com.yapp.api.domain.folder.persistence.CommentQuery;
import com.yapp.core.entity.folder.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface CommentJpaQuery extends JpaRepository<Comment, Long>, CommentQuery {

}
