package com.yapp.api.domain.family.service;

import static java.util.concurrent.CompletableFuture.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.yapp.api.domain.family.controller.dto.FamilyRequest;
import com.yapp.api.domain.family.controller.dto.FamilyResponse;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.handler.FamilyCommandHandler;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyService {
	private final FamilyCommandHandler familyCommandHandler;
	private final FamilyQueryHandler familyQueryHandler;
	private final UserCommandHandler userCommandHandler;
	private final TransactionTemplate transactionTemplate;

	// owner 가 가족을 이미 갖고있는지 검증하는 interceptor 추가해야함
	public Family create(User user, String familyName, String familyMotto) {
		// block
		return transactionTemplate.execute(process -> {
			Family family = familyCommandHandler.saveFamily(repository -> repository.save(new Family(user,
																									 familyName,
																									 familyMotto)));
			family.addUser(user);
			runAsync(() -> userCommandHandler.save(userRepository -> userRepository.save(user)));

			return family;
		});
	}

	public void modify(User user,
					   String imageLink,
					   String familyName,
					   String familyMotto,
					   List<FamilyRequest.Modify.Nickname> nicknameList) {
		// non-block
		transactionTemplate.executeWithoutResult(process -> {
			// block
			Family targetFamily = familyQueryHandler.findOne(familyRepository -> familyRepository.findById(user.getFamily()
																											   .getId()))
													.orElseThrow(() -> new BaseBusinessException(ErrorCode.FAMILY_NOT_FOUND));

			targetFamily.update(imageLink, familyName, familyMotto);

			nicknameList.forEach(nameSet -> {
				String pastNickname = nameSet.getPastNickname();
				String newNickname = nameSet.getNewNickname();
				targetFamily.getMembers()
							.stream()
							.filter(member -> member.getNicknameForUser(user)
													.equals(pastNickname))
							.findFirst()
							.ifPresentOrElse(member -> member.getProfileInfo()
															 .putNewNickname(user.getId(), newNickname), () -> {});
			});

			familyCommandHandler.saveFamily(familyRepository -> familyRepository.save(targetFamily));
		});
	}

	public FamilyResponse.Info get(User user) {
		return FamilyResponse.Info.from(user.getFamily(), user);
	}
}
