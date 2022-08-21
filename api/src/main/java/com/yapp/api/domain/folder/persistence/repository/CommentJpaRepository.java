package com.yapp.api.domain.folder.persistence.repository;

import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByFamilyAndFile(Family family, File file);
}
