package com.yapp.api.domain.album.service;

import static com.yapp.core.error.exception.ErrorCode.*;
import static java.time.format.DateTimeFormatter.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.album.element.comment.persistence.entity.Comment;
import com.yapp.core.persistance.album.element.comment.persistence.handler.CommentCommandHandler;
import com.yapp.core.persistance.album.element.comment.persistence.handler.CommentQueryHandler;
import com.yapp.core.persistance.file.persistence.entity.File;
import com.yapp.core.persistance.file.persistence.handler.FileQueryHandler;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

	private final FileQueryHandler fileQueryHandler;
	private final CommentCommandHandler commentCommandHandler;
	private final CommentQueryHandler commentQueryHandler;

	@Transactional
	public Long create(User user, Long fileId, String content) {

		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findByFamilyAndId(user.getFamily(),
																								fileId))
									.orElseThrow(() -> new BaseBusinessException(ErrorCode.FILE_NOT_FOUND,
																				 new RuntimeException(
																					 "FileNotFoundError : which {fileId} in PORT /album/{fileId}/comments")));

		return commentCommandHandler.create(commentRepository -> commentRepository.save(new Comment(user,
																									user.getFamily(),
																									file,
																									content)));
	}

	@Transactional
	public void modify(User user, Long commentId, String content) {
		Comment comment = commentQueryHandler.findOne(commentRepository -> commentRepository.findByUserAndId(user,
																											 commentId))
											 .orElseThrow(() -> new BaseBusinessException(COMMENT_NOT_FOUND,
																						  new RuntimeException(
																							  "CommentNotFoundError : which {commentId} in PATCH /album/comments/{commentId}")));
		comment.modifyComment(content);
	}

	public List<AlbumResponse.CommentElement> getList(User user, Long fileId) {
		return commentQueryHandler.findAll(commentRepository -> commentRepository.findAllByFamilyAndFileId(user.getFamily(),
																										   fileId))
								  .stream()
								  .map(comment -> {
									  // will occurred N+1, need Fetch Join
									  User commentOwner = comment.getUser();

									  String nicknameForUser = discernNicknameForUser(user, commentOwner);
									  return new AlbumResponse.CommentElement(comment.getId(),
																			  commentOwner.getId(),
																			  commentOwner.getProfileInfo()
																						  .getImageLink(),
																			  nicknameForUser,
																			  commentOwner.getProfileInfo()
																						  .getRoleInFamily(),
																			  comment.getCreatedAt()
																					 .format(ISO_DATE),
																			  comment.getContent());
								  })
								  .collect(Collectors.toList());
	}

	private String discernNicknameForUser(User user, User commentOwner) {
		return commentOwner.getNicknameForUser(user);
	}

	@Transactional
	public void remove(User user, Long fileId) {
		Comment comment = commentQueryHandler.findOne(commentRepository -> commentRepository.findByUserAndId(user,
																											 fileId))
											 .orElseThrow(() -> new BaseBusinessException(COMMENT_NOT_FOUND,
																						  new RuntimeException(
																							  "CommentNotFound : which {commentId} in DELETE /album/comments/{commentId}")));

		commentCommandHandler.remove(commentRepository -> commentRepository.delete(comment));
	}
}
