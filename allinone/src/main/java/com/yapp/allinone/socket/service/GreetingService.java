package com.yapp.allinone.socket.service;

import com.yapp.allinone.common.exception.RealTimeException;
import com.yapp.allinone.domain.family.persitence.query.handler.FamilyQueryHandler;
import com.yapp.allinone.domain.user.persistence.query.handler.UserQueryHandler;
import com.yapp.allinone.socket.repository.ProfileMessageRepository;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/28
 * Info :
 **/
@Service
@RequiredArgsConstructor
public class GreetingService {
    private final UserQueryHandler userQueryHandler;
    private final FamilyQueryHandler familyQueryHandler;
    private final ProfileMessageRepository profileMessageRepository;

    public void save(Long userId, String content) {
        User user = userQueryHandler.findOne(userId)
                .orElseThrow(() -> new RealTimeException(ErrorCode.USER_NOT_FOUND));

        profileMessageRepository.save(ProfileMessage.from(user, content, LocalDateTime.now()));
    }

    public List<User> findMembers(Long familyId) {
        Family family = familyQueryHandler.findOne(familyId)
                .orElseThrow(() -> new RealTimeException(ErrorCode.FAMILY_NOT_FOUND));
        return userQueryHandler.findAllByFamily(family);
    }
}
