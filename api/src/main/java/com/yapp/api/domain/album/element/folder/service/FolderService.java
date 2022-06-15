package com.yapp.api.domain.album.element.folder.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.handler.FolderCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderQueryHandler;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {
	private final FolderCommandHandler folderCommandHandler;
	private final FolderQueryHandler folderQueryHandler;
	private final FileCommandHandler fileCommandHandler;

	@Transactional
	public void uploadPhotos(User user, LocalDate date, List<String> photos) {
		// 파일 업로드
		// 폴더 업로그
		// - 같은 날짜에 존재하지 않은 폴더  : 폴더 새롭게 생성
		// - 같은 날짜에 존재하는 폴더 : 기존 폴더 그대로 사용
	}

	@Transactional
	public void uploadRecordings(User user, LocalDate date, String title, String link) {
		// 파일 업로드
		// 폴더 업로그
		// - 같은 날짜에 존재하지 않은 폴더  : 폴더 새롭게 생성
		// - 같은 날짜에 존재하는 폴더 : 기존 폴더 그대로 사용
	}
}
