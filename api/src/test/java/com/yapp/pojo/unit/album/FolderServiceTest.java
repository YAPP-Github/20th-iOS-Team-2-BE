package com.yapp.pojo.unit.album;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.folder.persistence.handler.FolderCommandHandlerImpl;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderQueryHandlerImpl;
import com.yapp.api.domain.album.element.folder.service.FolderService;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandlerImpl;
import com.yapp.util.Mocker;

@DisplayName("Folder : Service 단위 테스트")
public class FolderServiceTest extends Mocker {
	private FolderService folderService;

	@BeforeEach
	void init() {
		folderService = new FolderService(new FolderCommandHandlerImpl(folderRepository),
										  new FolderQueryHandlerImpl(folderRepository),
										  new FileCommandHandlerImpl(fileRepository));
	}

	@Test
	void 정상_uploadPhotos_사진올리기_앨범첫생성() {

	}

	@Test
	void 정상_uploadPhotos_사진올리기_기존앨범에추가() {

	}

	@Test
	void 정상_uploadRecordings_음성올리기_앨범첫생성() {

	}

	@Test
	void 정상_uploadRecordings_음성올리기_기존앨범에추가() {

	}
}
