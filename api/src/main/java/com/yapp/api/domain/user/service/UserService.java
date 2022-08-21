package com.yapp.api.domain.user.service;

import com.yapp.api.domain.user.controller.model.ProfileResponse;
import com.yapp.api.domain.user.persistence.command.handler.ProfileMessageCommandHandler;
import com.yapp.api.domain.user.persistence.command.handler.UserCommandHandler;
import com.yapp.api.domain.user.persistence.query.handler.ProfileMessageQueryHandler;
import com.yapp.api.domain.user.persistence.query.handler.UserQueryHandler;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.realtime.entity.user.entity.ProfileMessage;
import com.yapp.realtime.entity.user.entity.User;
import com.yapp.realtime.error.exception.ErrorCode;
import com.yapp.realtime.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements ExceptionThrowableLayer {
    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;
    private final ProfileMessageQueryHandler profileMessageQueryHandler;
    private final ProfileMessageCommandHandler profileMessageCommandHandler;

    @Transactional
    public void create(User user, String name, String nickname, String roleInFamily, LocalDate birthday) {
        user.setUp(name, nickname, roleInFamily, birthday);
        userCommandHandler.save(user);
    }

    @Transactional
    public void modify(User user, String nickname, String imageLink, LocalDate birthDay, String roleInFamily) {
        user.modify(nickname, imageLink, birthDay, roleInFamily);
        userCommandHandler.update(user);
    }

    public ProfileResponse.UserSimple getSimple(User user) {
        return ProfileResponse.UserSimple.from(user);
    }

    public ProfileResponse.UserDetail getDetail(User user) {
        return ProfileResponse.UserDetail.from(user);
    }

    public ProfileResponse.MessageHistory history(User orderedUser, Long targetUserId) {
        User targetUser = userQueryHandler.findOne(targetUserId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, packageName(this.getClass())));

        List<ProfileMessage> messages = profileMessageQueryHandler.findAll(targetUser);

        return new ProfileResponse.MessageHistory(
                    targetUser.getNicknameForUser(orderedUser),
                    targetUser.getRoleInFamily(),
                    targetUser.getProfileInfo().getImageLink(),
                    messages.size(),
                    messages.stream().map(ProfileResponse.MessageHistory.MessageDetail::from).collect(Collectors.toList()));
    }

    @Transactional
    public void removeHistory(User user, Long messageId) {
        profileMessageCommandHandler.deleteOne(user, messageId);
    }
}
