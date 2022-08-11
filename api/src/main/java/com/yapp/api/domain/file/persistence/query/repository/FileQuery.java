package com.yapp.api.domain.file.persistence.query.repository;

import com.yapp.core.entity.file.persistence.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FileQuery extends JpaRepository<File, Long> {
}
