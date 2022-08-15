package com.yapp.event.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info :
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {
/*
    public HomeResponse.HomeStatusInfo getRealTimeStatus(Long userId) {
        User oriUser = userQueryHandler.findOne(userRepository -> userRepository.findById(userId))
                .orElseThrow(() -> new BaseBusinessException(ErrorCode.NO_AUTHENTICATION_ACCESS));

        return new HomeResponse.HomeStatusInfo(oriUser.getFamily()
                .getMembers()
                .stream()
                .map(member -> HomeResponse.HomeMemberInfo.of(member, oriUser))
                .collect(Collectors.toList()));
    }*/
}
