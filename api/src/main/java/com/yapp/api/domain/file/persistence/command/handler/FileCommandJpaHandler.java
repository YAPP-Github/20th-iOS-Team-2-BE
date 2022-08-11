package com.yapp.api.domain.file.persistence.command.handler;

import com.yapp.api.domain.file.persistence.command.repository.FileCommand;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FileCommandJpaHandler implements FileCommandHandler{
    private final FileCommand fileCommand;

    public FileCommandJpaHandler(FileCommand fileCommand) {
        this.fileCommand = fileCommand;
    }
}
