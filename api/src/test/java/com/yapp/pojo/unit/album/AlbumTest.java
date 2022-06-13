package com.yapp.pojo.unit.album;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Album : Entity 단위 테스트")
public class AlbumTest {
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
		String 제목 = "title";

		Folder 폴더 = new Folder(가족, 썸네일, 제목);

		assertThat(폴더.getFamily()).isEqualTo(가족);
		assertThat(폴더.getThumbnail()).isEqualTo(썸네일);
		assertThat(폴더.getTitle()).isEqualTo(제목);
	}
}
