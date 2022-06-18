package com.yapp.api.domain.album.element.comment.service;

import static com.yapp.core.error.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.comment.persistence.entity.Comment;
import com.yapp.api.domain.album.element.comment.persistence.handler.CommentCommandHandler;
import com.yapp.api.domain.album.element.comment.persistence.handler.CommentQueryHandler;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	private final FileQueryHandler fileQueryHandler;
	private final CommentCommandHandler commentCommandHandler;
	private final CommentQueryHandler commentQueryHandler;

	@Transactional
	public void create(User user, Long fileId, String content) {

		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findByFamilyAndId(user.getFamily(),
																								fileId))
									.orElseThrow(() -> new BaseBusinessException(ErrorCode.FILE_NOT_FOUND,
																				 new RuntimeException(
																					 "FileNotFoundError : which {fileId} in PORT /album/{fileId}/comments")));

		commentCommandHandler.create(commentRepository -> commentRepository.save(new Comment(user, file, content)));
	}

	@Transactional
	public void modify(User user, Long fileId, String content) {
		Comment comment = commentQueryHandler.findOne(commentRepository -> commentRepository.findByUserAndId(user,
																											 fileId))
											 .orElseThrow(() -> new BaseBusinessException(COMMENT_NOT_FOUND,
																						  new RuntimeException(
																							  "CommentNotFoundError : which {commentId} in PATCH /album/comments/{commentId}")));
		comment.modifyComment(content);
	}

	public List<Comment> getList(User user, Long fileId) {
		return commentQueryHandler.findAll(commentRepository -> commentRepository.findAllByFamilyAndFileId(user.getFamily(),
																										   fileId));
	}
}
