package com.yapp.pojo.unit.file;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("File : Entity 단위 테스트")
public class FileTest {
	private User 사용자;
	private Family 가족;
	private Album 앨범;

	@BeforeEach
	void init() {
		사용자 = EntityFactory.user();
		가족 = EntityFactory.family(사용자);
		앨범 = EntityFactory.album(가족, LocalDate.of(2022, 6, 15));
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "PHOTO,RECORDING")
	void 정상_of_생성(String kind) {
		File 파일 = File.of("제목", "링크", kind, 앨범, LocalDate.of(2022, 6, 15), 가족);

		assertThat(파일.getKind()).isEqualTo(File.Kind.valueOf(kind));
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "PHOTO,RECORDING")
	void 정상_doFavour_즐겨찾기하기(String kind) {
		File 파일 = File.of("제목", "링크", kind, 앨범, LocalDate.of(2022, 6, 15), 가족);

		파일.doFavour();

		assertThat(파일.isFavourite()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "PHOTO,RECORDING")
	void 정상_doFavour_즐겨찾기_취소(String kind) {
		File 파일 = File.of("제목", "링크", kind, 앨범, LocalDate.of(2022, 6, 15), 가족);

		파일.doFavour();
		파일.doFavour();

		assertThat(파일.isFavourite()).isFalse();
	}

	@Test
	void 정상_isPhoto_검증() {
		File 파일 = File.of("제목", "링크", "photo", 앨범, LocalDate.of(2022, 6, 15), 가족);

		assertThat(파일.isPhoto()).isTrue();
		assertThat(파일.isRecording()).isFalse();
	}

	@Test
	void 정상_isRecording_검증() {
		File 파일 = File.of("제목", "링크", "recording", 앨범, LocalDate.of(2022, 6, 15), 가족);

		assertThat(파일.isPhoto()).isFalse();
		assertThat(파일.isRecording()).isTrue();
	}
}
