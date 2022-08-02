package com.yapp.api.domain.home;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.persistance.calander.appointment.persistence.entity.Appointment;
import com.yapp.core.persistance.calander.appointment.persistence.repository.AppointmentRepository;
import com.yapp.core.persistance.user.entity.ProfileMessage;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.repository.ProfileMessageRepository;
import com.yapp.core.persistance.user.repository.UserRepository;

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
	private final ProfileMessageRepository profileMessageRepository;
	private final UserRepository userRepository;

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
		profileMessageRepository.save(ProfileMessage.from(user, content, LocalDateTime.now()));
		userRepository.save(user);
	}

	@Transactional
	public void emoji(User user, Integer emojiNumber) {
		user.setEmoji(emojiNumber);

		userRepository.save(user);
	}
}
