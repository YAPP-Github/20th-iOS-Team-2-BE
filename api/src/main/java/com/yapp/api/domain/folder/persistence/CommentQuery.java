package com.yapp.api.domain.folder.persistence;

import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.folder.comment.entity.Comment;
import com.yapp.core.entity.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface CommentQuery {

    Optional<Comment> findByUserAndId(User user, Long fileId);

    List<Comment> findAllByFamilyAndFileId(Family family, Long fileId);
}
