package com.yapp.api.domain.file.jpa;

import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.core.persistence.file.persistence.repository.FileCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FileJpaCommand extends JpaRepository<File, Long>, FileCommand {
}
