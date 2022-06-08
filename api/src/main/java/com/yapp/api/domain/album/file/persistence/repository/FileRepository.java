package com.yapp.api.domain.album.file.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.file.persistence.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {}
