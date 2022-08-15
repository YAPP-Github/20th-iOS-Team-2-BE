package com.yapp.api.domain.home;

import com.yapp.api.domain.calendar.persistence.query.handler.AppointmentQueryHandler;
import com.yapp.api.domain.user.persistence.command.handler.ProfileMessageCommandHandler;
import com.yapp.api.domain.user.persistence.command.handler.UserCommandHandler;
import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.user.entity.ProfileMessage;
import com.yapp.core.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info :
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {
    private final AppointmentQueryHandler appointmentQueryHandler;
    private final ProfileMessageCommandHandler profileMessageCommandHandler;
    private final UserCommandHandler userCommandHandler;

    public HomeResponse.Info info(User user) {
        List<Appointment> appointments = appointmentQueryHandler.findAll(user.getFamily(), LocalDate.now(), LocalDate.now()
                .plusDays(31));

        return new HomeResponse.Info(user.getFamily()
                .getName(), appointments.stream()
                .map(HomeResponse.Info.EventInfo::from)
                .collect(Collectors.toList()));
    }

    @Transactional
    public void greet(User user, String content) {
        user.setContent(content);
        profileMessageCommandHandler.save(ProfileMessage.from(user, content, LocalDateTime.now()));
        userCommandHandler.save(user);
    }

    @Transactional
    public void emoji(User user, Integer emojiNumber) {
        user.setEmoji(emojiNumber);
        userCommandHandler.save(user);
    }
}
