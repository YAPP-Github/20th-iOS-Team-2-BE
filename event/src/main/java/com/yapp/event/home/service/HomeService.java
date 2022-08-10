package com.yapp.event.home.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.entity.family.persistence.handler.FamilyQueryHandler;
import com.yapp.core.entity.user.entity.User;
import com.yapp.core.entity.user.handler.user.UserQueryHandler;
import com.yapp.event.home.response.HomeResponse;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {
	private final UserQueryHandler userQueryHandler;
	private final FamilyQueryHandler familyQueryHandler;

	public HomeResponse.HomeStatusInfo getRealTimeStatus(Long userId) {
		User oriUser = userQueryHandler.findOne(userRepository -> userRepository.findById(userId))
									   .orElseThrow(() -> new BaseBusinessException(ErrorCode.NO_AUTHENTICATION_ACCESS));

		return new HomeResponse.HomeStatusInfo(oriUser.getFamily()
													  .getMembers()
													  .stream()
													  .map(member -> HomeResponse.HomeMemberInfo.of(member, oriUser))
													  .collect(Collectors.toList()));
	}
}
