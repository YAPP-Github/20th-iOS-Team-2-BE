package com.yapp.realtime.home.socket.service;

import com.yapp.realtime.global.error.exception.RealTimeException;
import com.yapp.realtime.home.socket.repository.FamilyRepository;
import com.yapp.realtime.home.socket.repository.ProfileMessageRepository;
import com.yapp.realtime.home.socket.repository.UserRepository;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/28
 * Info :
 **/
@Service
public class GreetingService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final ProfileMessageRepository profileMessageRepository;

    public GreetingService(
            UserRepository userRepository,
            FamilyRepository familyRepository,
            ProfileMessageRepository profileMessageRepository) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
        this.profileMessageRepository = profileMessageRepository;
    }

    public void save(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RealTimeException(ErrorCode.USER_NOT_FOUND));

        profileMessageRepository.save(ProfileMessage.from(user, content, LocalDateTime.now()));
    }

    public List<User> findMembers(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RealTimeException(ErrorCode.FAMILY_NOT_FOUND));
        return userRepository.findAllByFamily(family);
    }
}
