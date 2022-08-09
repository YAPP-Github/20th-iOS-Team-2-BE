package com.yapp.api.domain.home;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.persistence.calander.appointment.entity.Appointment;
import com.yapp.core.persistence.user.entity.ProfileMessage;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.repository.ProfileMessageCommand;
import com.yapp.core.persistence.user.repository.UserCommand;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {
	private final AppointmentRepository appointmentRepository;
	private final ProfileMessageCommand profileMessageCommand;
	private final UserCommand userCommand;

	public HomeResponse.Info info(User user) {

		LocalDate pd = LocalDate.now()
								.plusDays(31);
		List<Appointment> appointments = appointmentRepository.findAllByFamilyAndDateBetween(user.getFamily(),
																							 LocalDate.now(),
																							 LocalDate.now()
																									  .plusDays(31));
		return new HomeResponse.Info(user.getFamily()
										 .getName(),
									 appointments.stream()
												 .map(HomeResponse.Info.EventInfo::from)
												 .collect(Collectors.toList()));
	}

	@Transactional
	public void greet(User user, String content) {
		user.setContent(content);
		profileMessageCommand.save(ProfileMessage.from(user, content, LocalDateTime.now()));
		userCommand.save(user);
	}

	@Transactional
	public void emoji(User user, Integer emojiNumber) {
		user.setEmoji(emojiNumber);

		userCommand.save(user);
	}
}
