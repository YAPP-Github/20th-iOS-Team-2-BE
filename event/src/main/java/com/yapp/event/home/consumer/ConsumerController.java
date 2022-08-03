package com.yapp.event.home.consumer;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.family.persistence.repository.FamilyRepository;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.repository.UserRepository;
import com.yapp.core.util.KafkaMessageTemplate;
import com.yapp.event.home.dictionary.SessionDictionary;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
@RestController
@RequiredArgsConstructor
public class ConsumerController {
	private final SessionDictionary<Long, WebSocketSession> sessionDictionary;
	private final UserRepository userRepository;
	private final FamilyRepository familyRepository;

	@KafkaListener(topics = "yapp-topic")
	void get(@Payload String message) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		KafkaMessageTemplate.KafkaMessage kafkaMessage = objectMapper.readValue(message,
																				KafkaMessageTemplate.KafkaMessage.class);

		CompletableFuture.runAsync(() -> {

			Long userId = kafkaMessage.getUserId();
			User sender = userRepository.findById(userId)
										.orElseThrow(() -> new BaseBusinessException(ErrorCode.USER_NOT_FOUND));

			Family family = familyRepository.findById(sender.getFamily()
															.getId())
											.orElseThrow(() -> new BaseBusinessException(ErrorCode.FAMILY_NOT_FOUND));

			if (Objects.nonNull(kafkaMessage)) {
				if (sessionDictionary.isEmpty()) {
					return;
				}
				family.getMembers()
					  .parallelStream()
					  .filter(user -> user != sender)
					  .map(User::getId)
					  .forEach(receiver -> {
						  try {
							  sessionDictionary.get(receiver)
											   .sendMessage(new TextMessage(message));
						  } catch (IOException e) {
							  e.printStackTrace();
						  }
					  });
				return;
			}
			throw new BaseBusinessException(ErrorCode.CONSUMED_MESSAGE_EMPTY);
		});
	}

}
