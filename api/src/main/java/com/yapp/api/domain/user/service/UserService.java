package com.yapp.api.domain.user.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.yapp.api.domain.user.controller.dto.ProfileResponse;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.user.entity.ProfileMessage;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.handler.UserCommandHandler;
import com.yapp.core.persistance.user.repository.ProfileMessageRepository;
import com.yapp.core.persistance.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserCommandHandler userCommandHandler;
	private final TransactionTemplate transactionTemplate;
	private final UserRepository userRepository;
	private final ProfileMessageRepository profileMessageRepository;

	@Transactional
	public void create(User user, String name, String nickname, String roleInFamily, LocalDate birthday) {
		transactionTemplate.executeWithoutResult(process -> {
			user.update(name, nickname, roleInFamily, birthday);
			userCommandHandler.save(userRepository -> userRepository.save(user));
		});
	}

	@Transactional
	public void modify(User user, String nickname, String imageLink, LocalDate birthDay, String roleInFamily) {
		transactionTemplate.executeWithoutResult(process -> {
			user.update(nickname, imageLink, birthDay, roleInFamily);
			userCommandHandler.save(userRepository -> userRepository.save(user));
		});
	}

	public ProfileResponse.UserSimple getSimple(User user) {
		return ProfileResponse.UserSimple.from(user);
	}

	public ProfileResponse.UserDetail getDetail(User user) {
		return ProfileResponse.UserDetail.from(user);
	}

	public ProfileResponse.MessageHistory history(User user, Long userId) {
		User targetUser = userRepository.findById(userId)
										.orElseThrow(() -> new BaseBusinessException(ErrorCode.USER_NOT_FOUND));
		List<ProfileMessage> messages = profileMessageRepository.findAllByOwner(targetUser);

		return new ProfileResponse.MessageHistory(targetUser.getNicknameForUser(user),
												  targetUser.getRoleInFamily(),
												  targetUser.getProfileInfo()
															.getImageLink(),
												  messages.size(),
												  messages.stream()
														  .map(ProfileResponse.MessageHistory.MessageDetail::from)
														  .collect(Collectors.toList()));
	}

	@Transactional
	public void removeHistory(User user, Long messageId) {
		profileMessageRepository.deleteByOwnerAndId(user, messageId);
	}
}
