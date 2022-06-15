package com.yapp.pojo.unit.album;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Folder : Entity 단위 테스트")
public class FolderTest {
	@Test
	void 정상_CommentConstructor_생성() {
		User 작성자 = EntityFactory.user();
		String 내용 = "댓글 내용";

		Comment 댓글 = new Comment(작성자, 내용);

		assertThat(댓글.getUser()).isEqualTo(작성자);
		assertThat(댓글.getContent()).isEqualTo(내용);
	}

	@Test
	void 정상_FolderConstructor_생성() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";

		Folder 폴더 = new Folder(가족, 앨범날짜);

		assertThat(폴더.getFamily()).isEqualTo(가족);
		assertThat(폴더.getThumbnail()).isEqualTo(썸네일);
		assertThat(폴더.getDate()).isEqualTo(앨범날짜);
		assertThat(폴더.getTitle()).isEqualTo(기본제목);
	}

	@Test
	void 정상_setThumbnail_썸네일수정() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";
		Folder 폴더 = new Folder(가족, 앨범날짜);

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
		Folder 폴더 = new Folder(가족, 앨범날짜);
		폴더.setThumbnail(썸네일);

		assertThat(폴더.noThumbnail()).isFalse();
	}

	@Test
	void 정상_noThumbnail_썸네일검증_없음() {
		Family 가족 = EntityFactory.family();
		String 썸네일 = "imageLink";
		LocalDate 앨범날짜 = LocalDate.of(2022, 6, 15);
		String 기본제목 = "2022-06-15 앨범";
		Folder 폴더 = new Folder(가족, 앨범날짜);

		assertThat(폴더.noThumbnail()).isTrue();

	}
}
