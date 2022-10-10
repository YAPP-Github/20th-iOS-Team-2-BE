package com.yapp.allinone.domain.folder.persistence.repository;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.folder.comment.entity.Comment;
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
