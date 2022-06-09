package com.yapp.api.domain.album.element.folder.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {}
