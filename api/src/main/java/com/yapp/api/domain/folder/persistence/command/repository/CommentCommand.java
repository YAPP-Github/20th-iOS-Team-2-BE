package com.yapp.api.domain.folder.persistence.command.repository;

import com.yapp.core.entity.folder.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface CommentCommand extends JpaRepository<Comment, Long> {
}
