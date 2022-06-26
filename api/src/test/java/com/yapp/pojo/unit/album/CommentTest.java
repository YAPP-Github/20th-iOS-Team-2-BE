package com.yapp.pojo.unit.album;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Comment : Entity 단위 테스트")
public class CommentTest {
	@Test
	void 정상_constructor_댓글생성() {
		String 댓글_내용 = "내용";
		User 사용자 = EntityFactory.user();
		Family 가족 = EntityFactory.family(사용자);
		LocalDate 날짜 = LocalDate.of(2022, 6, 15);
		File 파일 = EntityFactory.file("제목", "링크", "photo", 사용자, 날짜);

		Comment 댓글 = new Comment(사용자, 가족, 파일, 댓글_내용);

		assertThat(댓글.getUser()).isEqualTo(사용자);
		assertThat(댓글.getFile()).isEqualTo(파일);
		assertThat(댓글.getContent()).isEqualTo(댓글_내용);
	}

	@Test
	void 정상_modifyContent_댓글수정() {
		String 댓글_내용 = "내용";
		String 수정_내용 = "수정 내용";
		User 사용자 = EntityFactory.user();
		Family 가족 = EntityFactory.family(사용자);
		LocalDate 날짜 = LocalDate.of(2022, 6, 15);
		File 파일 = EntityFactory.file("제목", "링크", "photo", 사용자, 날짜);
		Comment 댓글 = new Comment(사용자, 가족,파일, 댓글_내용);

		댓글.modifyComment(수정_내용);

		assertThat(댓글.getContent()).isEqualTo(수정_내용);
	}
}
