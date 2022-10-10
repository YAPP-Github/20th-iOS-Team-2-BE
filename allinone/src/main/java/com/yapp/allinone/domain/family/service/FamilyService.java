package com.yapp.allinone.domain.family.service;

import com.yapp.allinone.common.exception.ApiException;
import com.yapp.allinone.common.exception.RealTimeException;
import com.yapp.allinone.domain.calendar.persistence.query.handler.AppointmentQueryHandler;
import com.yapp.allinone.domain.family.controller.model.FamilyRequest;
import com.yapp.allinone.domain.family.controller.model.FamilyResponse;
import com.yapp.allinone.domain.family.persitence.command.handler.FamilyCommandHandler;
import com.yapp.allinone.domain.family.persitence.query.handler.FamilyQueryHandler;
import com.yapp.allinone.domain.home.model.HomeResponse;
import com.yapp.allinone.domain.user.persistence.command.handler.UserCommandHandler;
import com.yapp.allinone.domain.user.persistence.query.handler.UserQueryHandler;
import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.supporter.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FamilyService implements ExceptionThrowableLayer {
    private final FamilyCommandHandler familyCommandHandler;
    private final FamilyQueryHandler familyQueryHandler;
    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;
    private final AppointmentQueryHandler appointmentQueryHandler;

    @Transactional
    public FamilyResponse.Info get(User user) {
        Family family = familyQueryHandler.findOne(user.getFamily().getId()).orElseThrow(() -> new ApiException(ErrorCode.FAMILY_NOT_FOUND));
        return FamilyResponse.Info.from(family, user);
    }

    @Transactional
    public synchronized Family create(User user, String familyName, String familyMotto) {
        // user family 중복 검증
        if (user.getFamily() != null) {
            throw new ApiException(ErrorCode.ALREADY_JOINED, packageName(this.getClass()));
        }

        // family 검증

        Family createdFamily = familyCommandHandler.save(Family.of(user, familyName, familyMotto));
        userCommandHandler.update(user);

        return createdFamily;
    }

    @Transactional
    public void modify(
            User user, String familyName, String familyMotto, List<FamilyRequest.Modify.Nickname> nicknameList) {
        Family foundFamily = user.getFamily();

        foundFamily.patch(familyName, familyMotto);

        nicknameList.forEach(nameSet -> {
            String newNickname = nameSet.getNewNickname();
            User targetUser = userQueryHandler.findOne(nameSet.getUserId())
                    .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, packageName(this.getClass())));

            targetUser.patchNickname(user.getId(), newNickname);
            userCommandHandler.update(targetUser);
        });

        familyCommandHandler.save(foundFamily);
    }

    @Transactional
    public void join(User user, String code) {
        Family foundFamily = familyQueryHandler.findOne(code)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_VALID_CODE, packageName(this.getClass())));

        if (foundFamily.getMemberCount() > 11) {
            throw new ApiException(ErrorCode.FULL_MEMBER, packageName(this.getClass()));
        }

        foundFamily.addUser(user);
        userCommandHandler.update(user);
    }

    @Transactional(readOnly = true)
    public HomeResponse.HomeInfo getEvents(User user) {
        Family family = familyQueryHandler.findOne(user.getFamily().getId()).orElseThrow(() -> new RealTimeException(ErrorCode.FAMILY_NOT_FOUND));

        List<Appointment> appointments = appointmentQueryHandler.findAll(family, LocalDate.now(), LocalDate.now()
                .plusMonths(1));

        return HomeResponse.HomeInfo.of(family, appointments);
    }
}
