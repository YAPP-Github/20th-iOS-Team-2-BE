package com.yapp.pojo.unit.album;

import static com.yapp.api.domain.file.persistence.entity.File.*;
import static com.yapp.util.EntityFactory.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumCommandHandlerImpl;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumQueryHandlerImpl;
import com.yapp.api.domain.album.element.folder.service.AlbumService;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandlerImpl;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandlerImpl;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandlerImpl;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.util.BDDProcessor;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Folder : Service 단위 테스트")
public class AlbumServiceTest extends Mocker implements BDDProcessor<AlbumService, Object> {
	private AlbumService albumService;
	private User 사용자;
	private LocalDate 날짜;

	@BeforeEach
	void init() {
		사용자 = EntityFactory.user();
		날짜 = LocalDate.of(2022, 6, 15);
		albumService = new AlbumService(new AlbumCommandHandlerImpl(albumRepository),
										new AlbumQueryHandlerImpl(albumRepository),
										new FileCommandHandlerImpl(fileRepository),
										new FileQueryHandlerImpl(fileRepository),
										new FamilyQueryHandlerImpl(familyRepository));
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1");

		whenCommand(albumService, service -> service.uploadPhotos(사용자, 날짜, 사진리스트));

		then(() -> {
			verify(albumRepository, times(ONE)).findByDate(any());
			verify(fileRepository, times(ONE)).saveAll(any());
			verify(albumRepository, times(ONE)).save(any());
		});
	}

	@Test
	void 정상_uploadPhotos_사진올리기_한개_기존앨범에추가() {
		Family 가족 = family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		List<String> 사진리스트 = List.of("사진2");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());

		whenCommand(albumService, service -> service.uploadPhotos(사용자, 날짜, 사진리스트));

		then(() -> {
			verify(albumRepository, times(ONE)).findByDate(any());
			verify(fileRepository, times(ONE)).saveAll(any());
			verify(albumRepository, times(NEVER)).save(any());
		});
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_앨범첫생성() {
		List<String> 사진리스트 = List.of("사진1", "사진2", "사진3");

		whenCommand(albumService, service -> service.uploadPhotos(사용자, 날짜, 사진리스트));

		then(() -> {
			verify(fileRepository, times(ONE)).saveAll(any());
			verify(albumRepository, times(ONE)).save(any());
		});
	}

	@Test
	void 정상_uploadPhotos_사진올리기_여러개_기존앨범에추가() {
		Family 가족 = family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		List<String> 사진리스트 = List.of("사진3", "사진4");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());

		whenCommand(albumService, service -> service.uploadPhotos(사용자, 날짜, 사진리스트));
		then(() -> {
			verify(albumRepository, times(ONE)).findByDate(any());
			verify(fileRepository, times(ONE)).saveAll(any());
			verify(albumRepository, times(NEVER)).save(any());
		});
	}

	@Test
	void 정상_uploadRecordings_음성올리기_앨범첫생성() {
		String 제목 = "음성 제목";
		String 음성 = "음성 파일";

		whenCommand(albumService, service -> service.uploadRecordings(사용자, 날짜, 제목, 음성));
		then(() -> {
			verify(albumRepository, times(ONE)).findByDate(any());
			verify(fileRepository, times(ONE)).save(any());
			verify(albumRepository, times(ONE)).save(any());
		});
	}

	@Test
	void 정상_uploadRecordings_음성올리기_기존앨범에추가() {
		Family 가족 = family(사용자);
		Album 기존앨범 = new Album(가족, 날짜);
		기존앨범.setThumbnail("기존썸네일");
		willReturn(Optional.of(기존앨범)).given(albumRepository)
									 .findByDate(any());
		String 제목 = "음성 제목";
		String 음성 = "음성 파일";

		whenCommand(albumService, service -> service.uploadRecordings(사용자, 날짜, 제목, 음성));
		then(() -> {
			verify(albumRepository, times(ONE)).findByDate(any());
			verify(fileRepository, times(ONE)).save(any());
			verify(albumRepository, times(NEVER)).save(any());
		});
	}

	@Test
	void 정상_getAlbums_앨범리스트조회_날짜별_최신순() {
		Family 가족 = family(사용자);
		Album 앨범_0615 = album(가족, 날짜);
		Album 앨범_0616 = album(가족, LocalDate.of(2022, 6, 16));
		Album 앨범_0613 = album(가족, LocalDate.of(2022, 6, 13));
		Album 앨범_0611 = album(가족, LocalDate.of(2022, 6, 11));
		List<Album> 앨범리스트 = List.of(앨범_0615, 앨범_0616, 앨범_0613, 앨범_0611);
		willReturn(앨범리스트).given(albumRepository)
						 .findByFamily(가족);

		List<Album> 조회된_앨범들 = (List<Album>)whenQuery(albumService, service -> service.getList(사용자));
		then(() -> {
			assertThat(조회된_앨범들).hasSize(SIZE_FOUR);
			assertThat(조회된_앨범들).containsExactly(앨범_0616, 앨범_0615, 앨범_0613, 앨범_0611);
		});
	}

	@Test
	void 정상_getAlbums_앨범리스트조회_종류별() {
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);

		File 사진1 = file("사진1", "링크1", KIND_PHOTO, 앨범, 날짜, 가족);
		File 사진2 = file("사진2", "링크2", KIND_PHOTO, 앨범, 날짜, 가족);
		File 사진3 = file("사진3", "링크3", KIND_PHOTO, 앨범, 날짜, 가족);
		File 음성1 = file("음성1", "링크4", KIND_RECORDING, 앨범, 날짜, 가족);

		사진1.doFavour();
		사진2.doFavour();
		음성1.doFavour();

		willReturn(List.of(사진1, 사진2, 사진3, 음성1)).given(fileRepository)
											   .findAllByFamily(가족);

		Map<String, AlbumService.KindInfo> 조회된_앨범들 = (Map)whenQuery(albumService,
																	service -> service.getCountForEachCategory(사용자));
		then(() -> {
			assertThat(조회된_앨범들.get(FAVOURITE)
							  .getCount()).isEqualTo(SIZE_THREE);
			assertThat(조회된_앨범들.get(FAVOURITE)
							  .getLink()).isEqualTo("링크1");
			assertThat(조회된_앨범들.get(KIND_PHOTO)
							  .getCount()).isEqualTo(SIZE_THREE);
			assertThat(조회된_앨범들.get(KIND_PHOTO)
							  .getLink()).isEqualTo("링크1");
			assertThat(조회된_앨범들.get(KIND_RECORDING)
							  .getCount()).isEqualTo(ONE);
			assertThat(조회된_앨범들.get(KIND_RECORDING)
							  .getLink()).isEqualTo("링크4");
		});
	}

	@Test
	void 정상_getAlbumDetails_앨범상세조회_id조회() {
		Album 단일앨범 = album(family(사용자), 날짜);
		willReturn(Optional.of(단일앨범)).given(albumRepository)
									 .findByFamilyAndId(any(), any());

		Album 조회된_앨범 = (Album)whenQuery(albumService, service -> service.get(사용자, THREE_L));
		then(() -> {
			assertThat(조회된_앨범).isEqualTo(단일앨범);
			assertThat(조회된_앨범.getTitle()).isEqualTo("2022-06-15 앨범");
		});
	}

	@Test
	void 정상_modifyTitle_제목변경() {
		String 변경될제목 = "제목";
		Family 가족 = family(사용자);
		Album 앨범 = album(가족, 날짜);
		willReturn(Optional.of(앨범)).given(albumRepository)
								   .findByFamilyAndId(any(), any());

		whenCommand(albumService, service -> service.modifyTitle(사용자, 앨범.getId(), 변경될제목));
		then(() -> {
			verify(albumRepository, times(ONE)).findByFamilyAndId(any(), any());
			assertThat(앨범.getTitle()).isEqualTo(변경될제목);
			assertThat(앨범.getTitle()).isNotEqualTo("2022-06-15 앨범");
		});
	}

	@Test
	void 정상_remove_앨범삭제_존재하는_앨범() {
		Family 가족 = EntityFactory.family();
		Album 앨범 = EntityFactory.album(가족, 날짜);
		willReturn(Optional.of(앨범)).given(albumRepository)
								   .findByFamilyAndId(any(), any());

		whenCommand(albumService, service -> service.remove(사용자, 앨범.getId()));
		then(() -> {
			verify(albumRepository, times(ONE)).findByFamilyAndId(any(), any());
			verify(albumRepository, times(ONE)).delete(any());
		});
	}

	@Test
	void 정상_remove_앨범삭제_존재하지않는_앨범() {
		willReturn(Optional.ofNullable(null)).given(albumRepository)
											 .findByFamilyAndId(any(), any());

		then(() -> {
			assertThatExceptionOfType(BaseBusinessException.class).isThrownBy(() -> albumService.remove(사용자, THREE_L));
			verify(albumRepository, times(ONE)).findByFamilyAndId(any(), any());
			verify(albumRepository, times(NEVER)).delete(any());
		});
	}
}
