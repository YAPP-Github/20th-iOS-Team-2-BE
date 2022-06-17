package com.yapp.pojo.unit.album;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.yapp.api.domain.album.element.comment.persistence.handler.CommentCommandHandlerImpl;
import com.yapp.api.domain.album.element.comment.persistence.handler.CommentQueryHandlerImpl;
import com.yapp.api.domain.album.element.comment.service.CommentService;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandlerImpl;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Comment : Service 단위 테스트")
public class CommentServiceTest extends Mocker {
	private CommentService commentService;
	private User 사용자;
	private LocalDate 날짜;

	@BeforeEach
	void init() {
		commentService = new CommentService(new FileQueryHandlerImpl(fileRepository),
											new CommentCommandHandlerImpl(commentRepository),
											new CommentQueryHandlerImpl());
		사용자 = EntityFactory.user();
		날짜 = LocalDate.of(2022, 6, 15);
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "photo,recording")
	void 정상_create_댓글생성(String kind) {
		String 댓글_내용 = "내용";
		File 파일 = EntityFactory.file("제목", "링크", kind, 사용자, 날짜);
		willReturn(Optional.of(파일)).given(fileRepository)
								   .findByFamilyAndId(any(), any());

		commentService.create(사용자, 1L, 댓글_내용);

		verify(commentRepository, times(1)).save(any());
	}

	@ParameterizedTest
	@CsvSource(delimiterString = ",", value = "photo,recording")
	void 예외_create_댓글생성_없는파일(String kind) {
		String 댓글_내용 = "내용";
		File 파일 = EntityFactory.file("제목", "링크", kind, 사용자, 날짜);
		willReturn(Optional.empty()).given(fileRepository)
									.findByFamilyAndId(any(), any());

		assertThatExceptionOfType(BaseBusinessException.class).isThrownBy(() -> commentService.create(사용자, 1L, 댓글_내용));
		verify(commentRepository, times(0)).save(any());
	}
}
