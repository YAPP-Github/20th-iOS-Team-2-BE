package com.yapp.api.domain.folder.jpa;

import com.yapp.core.persistence.folder.comment.entity.Comment;
import com.yapp.core.persistence.folder.comment.repository.CommentCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface CommentJpaCommand extends JpaRepository<Comment, Long>, CommentCommand {
}
