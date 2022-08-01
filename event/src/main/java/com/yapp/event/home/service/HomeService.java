package com.yapp.event.home.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.family.persistence.handler.FamilyQueryHandler;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.handler.UserQueryHandler;
import com.yapp.event.home.response.HomeResponse;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
@Service
@RequiredArgsConstructor
public class HomeService {
	private final UserQueryHandler userQueryHandler;
	private final FamilyQueryHandler familyQueryHandler;

	@Transactional(readOnly = true)
	public HomeResponse.HomeStatusInfo getRealTimeStatus(Long userId, Long familyId) {
		User oriUser = userQueryHandler.findOne(userRepository -> userRepository.findById(userId))
									   .orElseThrow(() -> new BaseBusinessException(ErrorCode.NO_AUTHENTICATION_ACCESS));

		Family family = familyQueryHandler.findOne(familyRepository -> familyRepository.findById(familyId))
										  .orElseThrow(() -> new BaseBusinessException(ErrorCode.FAMILY_NOT_FOUND));

		return new HomeResponse.HomeStatusInfo(family.getMembers()
													 .stream()
													 .map(member -> HomeResponse.HomeMemberInfo.of(member, oriUser))
													 .collect(Collectors.toList()));
	}
}
