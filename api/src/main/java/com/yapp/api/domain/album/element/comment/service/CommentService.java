package com.yapp.api.domain.album.element.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.comment.persistence.handler.CommentCommandHandler;
import com.yapp.api.domain.album.element.comment.persistence.handler.CommentQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	private final CommentCommandHandler commentCommandHandler;
	private final CommentQueryHandler commentQueryHandler;
}
