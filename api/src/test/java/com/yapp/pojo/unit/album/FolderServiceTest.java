package com.yapp.pojo.unit.album;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.folder.persistence.handler.FolderCommandHandlerImpl;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderQueryHandlerImpl;
import com.yapp.api.domain.album.element.folder.service.FolderService;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandlerImpl;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Folder : Service 단위 테스트")
public class FolderServiceTest extends Mocker {
	private FolderService folderService;
	private User 사용자;
	private LocalDate 날짜;

	@BeforeEach
	void init() {
		사용자 = EntityFactory.user();
		날짜 = LocalDate.of(2022, 6, 15);
		folderService = new FolderService(new FolderCommandHandlerImpl(folderRepository),
										  new FolderQueryHandlerImpl(folderRepository),
										  new FileCommandHandlerImpl(fileRepository));
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1");

		folderService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(fileRepository, times(1)).save(any());
		verify(folderRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_기존앨범에추가() {
		List<String> 최초_사진리스트 = List.of("사진1");
		List<String> 사진리스트 = List.of("사진2");

		folderService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(fileRepository, times(2)).save(any());
		verify(folderRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1", "사진2", "사진3");

		folderService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(fileRepository, times(3)).save(any());
		verify(folderRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_기존앨범에추가() {
		List<String> 최초_사진리스트 = List.of("사진1", "사진2");
		List<String> 사진리스트 = List.of("사진3", "사진4");

		folderService.uploadPhotos(사용자, 날짜, 최초_사진리스트);
		folderService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(fileRepository, times(4)).save(any());
		verify(folderRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadRecordings_음성올리기_앨범첫생성() {
		String 제목 = "음성 제목";
		String 음성 = "음성 파일";

		folderService.uploadRecordings(사용자, 날짜, 제목, 음성);

		verify(fileRepository, times(1)).save(any());
		verify(folderRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadRecordings_음성올리기_기존앨범에추가() {
		List<String> 최초_사진리스트 = List.of("사진1", "사진2");
		folderService.uploadPhotos(사용자, 날짜, 최초_사진리스트);

		String 제목 = "음성 제목";
		String 음성 = "음성 파일";
		folderService.uploadRecordings(사용자, 날짜, 제목, 음성);

		verify(fileRepository, times(3)).save(any());
		verify(folderRepository, times(1)).save(any());
	}
}
