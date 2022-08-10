package com.yapp.api.domain.file.persistence.jpa;

import com.yapp.api.domain.file.persistence.FileQuery;
import com.yapp.core.entity.file.persistence.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FileJpaQuery extends JpaRepository<File, Long>, FileQuery {
}
