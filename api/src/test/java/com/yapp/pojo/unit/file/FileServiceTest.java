package com.yapp.pojo.unit.file;

import static com.yapp.util.EntityFactory.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.List;
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
import com.yapp.core.error.exception.BaseBusinessException;
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

	@Test
	void 정상_getAlbumDetailsByKind_앨범상세조회_종류조회_PHOTO_최신순() {
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);
		File 사진1 = file("제목1", "링크1", "photo", 앨범, 날짜, 가족);
		File 사진2 = file("제목2", "링크2", "photo", 앨범, LocalDate.of(2022, 6, 18), 가족);
		File 사진3 = file("제목3", "링크3", "photo", 앨범, LocalDate.of(2022, 6, 7), 가족);
		List<File> 사진들 = List.of(사진1, 사진2, 사진3);
		willReturn(사진들).given(fileRepository)
					   .findAllByFamilyAndKind(any(), any());

		List<File> 조회된_사진들 = fileService.getFiles(사용자, "photo");

		assertThat(조회된_사진들).hasSize(3);
		assertThat(조회된_사진들).containsExactly(사진2, 사진1, 사진3);
	}

	@Test
	void 정상_getAlbumDetailsByKind_앨범상세조회_종류조회_RECORDING_최신순() {
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);
		File 음성1 = file("제목1", "링크1", "recording", 앨범, 날짜, 가족);
		File 음성2 = file("제목2", "링크2", "recording", 앨범, LocalDate.of(2022, 6, 18), 가족);
		File 음성3 = file("제목3", "링크3", "recording", 앨범, LocalDate.of(2022, 6, 7), 가족);
		List<File> 음성들 = List.of(음성1, 음성2, 음성3);
		willReturn(음성들).given(fileRepository)
					   .findAllByFamilyAndKind(any(), any());

		List<File> 조회된_음성들 = fileService.getFiles(사용자, "photo");

		assertThat(조회된_음성들).hasSize(3);
		assertThat(조회된_음성들).containsExactly(음성2, 음성1, 음성3);
	}

	@Test
	void 정상_getAlbumDetailsByKind_앨범상세조회_종류조회_FAVOURITE_최신순() {
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);
		File 음성1 = file("음성1", "링크1", "recording", 앨범, 날짜, 가족);
		File 사진1 = file("사진2", "링크2", "photo", 앨범, LocalDate.of(2022, 6, 18), 가족);
		File 음성2 = file("음성2", "링크3", "recording", 앨범, LocalDate.of(2022, 6, 7), 가족);
		음성1.doFavour();
		사진1.doFavour();
		음성2.doFavour();
		List<File> 음성들 = List.of(음성1, 사진1, 음성2);
		willReturn(음성들).given(fileRepository)
					   .findAllByFamilyAndFavourite(any(), any());

		List<File> 조회된_즐겨찾기 = fileService.getFiles(사용자, "favourite");

		assertThat(조회된_즐겨찾기).hasSize(3);
		assertThat(조회된_즐겨찾기).containsExactly(음성1, 사진1, 음성2);
	}

	@Test
	void 정상_makeFavourite_즐겨찾기하기() {
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);
		willReturn(Optional.of(file("제목", "링크", "recording", 앨범, 날짜, 가족))).given(fileRepository)
																		  .findById(any());

		fileService.makeFavourite(사용자, 3L);

		verify(fileRepository, times(1)).findById(any());
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
		assertThatExceptionOfType(BaseBusinessException.class).isThrownBy(() -> fileService.remove(사용자, 3L));
		verify(fileRepository, times(1)).findByFamilyAndId(any(), any());
		verify(fileRepository, times(0)).delete(any());
	}
}
