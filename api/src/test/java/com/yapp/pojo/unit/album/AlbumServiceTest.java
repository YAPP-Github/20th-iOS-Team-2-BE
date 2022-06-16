package com.yapp.pojo.unit.album;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumCommandHandlerImpl;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumQueryHandlerImpl;
import com.yapp.api.domain.album.element.folder.service.AlbumService;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandlerImpl;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Folder : Service 단위 테스트")
public class AlbumServiceTest extends Mocker {
	private AlbumService albumService;
	private User 사용자;
	private LocalDate 날짜;

	@BeforeEach
	void init() {
		사용자 = EntityFactory.user();
		날짜 = LocalDate.of(2022, 6, 15);
		albumService = new AlbumService(new AlbumCommandHandlerImpl(albumRepository),
										new AlbumQueryHandlerImpl(albumRepository),
										new FileCommandHandlerImpl(fileRepository));
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1");

		albumService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(albumRepository, times(1)).findByDate(any());
		verify(fileRepository, times(1)).saveAll(any());
		verify(albumRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_기존앨범에추가() {
		Family 가족 = EntityFactory.family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		List<String> 사진리스트 = List.of("사진2");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());

		albumService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(albumRepository, times(1)).findByDate(any());
		verify(fileRepository, times(1)).saveAll(any());
		verify(albumRepository, times(0)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1", "사진2", "사진3");

		albumService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(fileRepository, times(1)).saveAll(any());
		verify(albumRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_기존앨범에추가() {
		Family 가족 = EntityFactory.family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		List<String> 사진리스트 = List.of("사진3", "사진4");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());

		albumService.uploadPhotos(사용자, 날짜, 사진리스트);

		verify(albumRepository, times(1)).findByDate(any());
		verify(fileRepository, times(1)).saveAll(any());
		verify(albumRepository, times(0)).save(any());
	}

	@Test
	void 정상_uploadRecordings_음성올리기_앨범첫생성() {
		String 제목 = "음성 제목";
		String 음성 = "음성 파일";

		albumService.uploadRecordings(사용자, 날짜, 제목, 음성);

		verify(albumRepository, times(1)).findByDate(any());
		verify(fileRepository, times(1)).save(any());
		verify(albumRepository, times(1)).save(any());
	}

	@Test
	void 정상_uploadRecordings_음성올리기_기존앨범에추가() {
		Family 가족 = EntityFactory.family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());
		String 제목 = "음성 제목";
		String 음성 = "음성 파일";

		albumService.uploadRecordings(사용자, 날짜, 제목, 음성);

		verify(albumRepository, times(1)).findByDate(any());
		verify(fileRepository, times(1)).save(any());
		verify(albumRepository, times(0)).save(any());
	}

	@Test
	void 정상_getAlbums_앨범리스트조회() {
	}

	@Test
	void 정상_getAlbumDetails_앨범상세조회() {

	}
}
