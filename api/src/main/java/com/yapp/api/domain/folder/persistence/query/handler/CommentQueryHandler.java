package com.yapp.api.domain.folder.persistence.query.handler;

import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.comment.entity.Comment;

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
