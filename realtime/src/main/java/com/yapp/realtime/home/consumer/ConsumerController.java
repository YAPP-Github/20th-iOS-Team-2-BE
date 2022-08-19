package com.yapp.realtime.home.consumer;

import com.yapp.realtime.home.dictionary.SessionDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info :
 **/
@RestController
@RequiredArgsConstructor
public class ConsumerController {
    private final SessionDictionary<Long, WebSocketSession> sessionDictionary;


/*	@KafkaListener(topics = "sofa-home")
	void get(@Payload String message) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		KafkaMessageTemplate.KafkaMessage kafkaMessage = objectMapper.readValue(message,
																				KafkaMessageTemplate.KafkaMessage.class);

		CompletableFuture.runAsync(() -> {

			Long userId = kafkaMessage.getUserId();
			User sender = userCommand.findById(userId)
										.orElseThrow(() -> new BaseBusinessException(ErrorCode.USER_NOT_FOUND));

			Family family = familyCommand.findById(sender.getFamily()
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
	}*/

}
