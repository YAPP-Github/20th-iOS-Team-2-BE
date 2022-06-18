package com.yapp.pojo.unit.album;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.util.EntityFactory;

@DisplayName("Folder : Entity 단위 테스트")
public class AlbumTest {

	@Test
	void 정상_FolderConstructor_생성() {
		Family 가족 = EntityFactory.family();
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";

		Album 폴더 = new Album(가족, 앨범날짜);

		assertThat(폴더.getFamily()).isEqualTo(가족);
		assertThat(폴더.getThumbnail()).isEmpty();
		assertThat(폴더.getDate()).isEqualTo(앨범날짜);
		assertThat(폴더.getTitle()).isEqualTo(기본제목);
	}

	@Test
	void 정상_setThumbnail_썸네일수정() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";
		Album 폴더 = new Album(가족, 앨범날짜);

		폴더.setThumbnail(썸네일);

		assertThat(폴더.getFamily()).isEqualTo(가족);
		assertThat(폴더.getThumbnail()).isEqualTo(썸네일);
		assertThat(폴더.getDate()).isEqualTo(앨범날짜);
		assertThat(폴더.getTitle()).isEqualTo(기본제목);
	}

	@Test
	void 정상_noThumbnail_썸네일검증_있음() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";
		Album 폴더 = new Album(가족, 앨범날짜);
		폴더.setThumbnail(썸네일);

		assertThat(폴더.noThumbnail()).isFalse();
	}

	@Test
	void 정상_noThumbnail_썸네일검증_없음() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";
		Album 폴더 = new Album(가족, 앨범날짜);

		assertThat(폴더.noThumbnail()).isTrue();
	}

	@Test
	void 정상_modifyTitle_제목수정_올바른값() {
		String 변경된_제목 = "제목";
		Family 가족 = EntityFactory.family();
		LocalDate 날짜 = LocalDate.of(2022, 6, 15);
		Album 앨범 = EntityFactory.album(가족, 날짜);

		앨범.modifyTitle(변경된_제목);
		assertThat(앨범.getTitle()).isNotEqualTo("2022-06-15 앨범");
		assertThat(앨범.getTitle()).isEqualTo(변경된_제목);
	}

	@Test
	void 정상_modifyTitle_제목수정_올바르지못한_값() {
		String 변경된_제목 = "";
		Family 가족 = EntityFactory.family();
		LocalDate 날짜 = LocalDate.of(2022, 6, 15);
		Album 앨범 = EntityFactory.album(가족, 날짜);

		앨범.modifyTitle(변경된_제목);
		assertThat(앨범.getTitle()).isEqualTo("2022-06-15 앨범");
		assertThat(앨범.getTitle()).isNotEqualTo(변경된_제목);
	}
}
