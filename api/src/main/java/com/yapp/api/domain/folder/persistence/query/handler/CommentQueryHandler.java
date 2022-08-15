package com.yapp.api.domain.folder.persistence.query.handler;

import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.file.persistence.entity.File;
import com.yapp.core.entity.folder.comment.entity.Comment;
import com.yapp.core.entity.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface CommentQueryHandler {

    Optional<Comment> findOne(Long commentId);

    List<Comment> findAll(Family family, File file);
}
