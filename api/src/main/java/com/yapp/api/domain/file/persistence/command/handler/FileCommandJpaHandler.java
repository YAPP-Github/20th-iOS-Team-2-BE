package com.yapp.api.domain.file.persistence.command.handler;

import com.yapp.api.domain.file.persistence.repository.FileJpaRepository;
import com.yapp.core.entity.file.persistence.entity.File;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FileCommandJpaHandler implements FileCommandHandler {
    private final FileJpaRepository fileJpaRepository;

    public FileCommandJpaHandler(FileJpaRepository fileJpaRepository) {
        this.fileJpaRepository = fileJpaRepository;
    }

    @Override
    public void save(File file) {
        fileJpaRepository.save(file);
    }

    @Override
    public void saveAll(Collection<File> files) {
        fileJpaRepository.saveAllAndFlush(files);
    }

    @Override
    public void update(List<File> files) {
        fileJpaRepository.saveAllAndFlush(files);
    }

    @Override
    public void remove(File file) {
        fileJpaRepository.delete(file);
    }
}
