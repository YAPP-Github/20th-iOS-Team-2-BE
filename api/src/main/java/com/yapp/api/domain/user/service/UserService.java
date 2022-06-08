package com.yapp.api.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.user.persistence.handler.ProfileMessageCommandHandler;
import com.yapp.api.domain.user.persistence.handler.ProfileMessageQueryHandler;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;
import com.yapp.api.domain.user.persistence.handler.UserQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserCommandHandler userCommandHandler;
	private final UserQueryHandler userQueryHandler;
	private final ProfileMessageCommandHandler profileMessageCommandHandler;
	private final ProfileMessageQueryHandler profileMessageQueryHandler;

}
