package com.yapp.pojo.unit.file;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandlerImpl;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandlerImpl;
import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("File : Service 단위 테스트")
public class FileServiceTest extends Mocker {
	private FileService fileService;
	private User 사용자;
	private LocalDate 날짜;

	@BeforeEach
	void init() {
		fileService = new FileService(new FileCommandHandlerImpl(fileRepository),
									  new FileQueryHandlerImpl(fileRepository));
		사용자 = EntityFactory.user();
		날짜 = LocalDate.of(2022, 6, 15);
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "photo,recording")
	void 정상_remove_파일삭제_존재하는_파일(String kind) {
		Family 가족 = EntityFactory.family(사용자);
		Album 앨범 = EntityFactory.album(가족, 날짜);
		File 파일 = EntityFactory.file("제목", "링크", kind, 앨범, 날짜, 가족);
		willReturn(Optional.of(파일)).given(fileRepository)
								   .findByFamilyAndId(any(), any());

		fileService.remove(사용자, 파일.getId());

		verify(fileRepository, times(1)).findByFamilyAndId(any(), any());
		verify(fileRepository, times(1)).delete(파일);
	}

	@Test
	void 정상_remove_파일삭제_존재하지않는_파일() {

	}
}
