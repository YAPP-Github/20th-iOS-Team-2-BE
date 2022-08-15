package com.yapp.api.domain.file.persistence.command.handler;

import com.yapp.core.entity.file.persistence.entity.File;

import java.util.Collection;
import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FileCommandHandler {
    void saveAll(Collection<File> files);

    void save(File file);

    void update(List<File> files);

    void remove(File file);
}
