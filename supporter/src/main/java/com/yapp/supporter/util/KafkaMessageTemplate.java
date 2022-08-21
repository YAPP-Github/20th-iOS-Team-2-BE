package com.yapp.supporter.util;

import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.supporter.entity.user.entity.User;

import com.yapp.supporter.error.exception.PersistenceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
public class KafkaMessageTemplate {
	public static <T> KafkaMessage message(User user, T body) {
		if (body instanceof String) {
			return KafkaMessage.content(user, body);
		}
		if (body instanceof Integer) {
			return KafkaMessage.emoji(user, body);
		}

		throw new PersistenceException(ErrorCode.CONSUMED_MESSAGE_EMPTY);
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class KafkaMessage<T> {
		private Long userId;
		private String name;
		private T content;
		private T emoji;

		public static <T> KafkaMessage content(User user, T body) {
			return new KafkaMessage(user.getId(), user.getName(), body, null);
		}

		public static <T> KafkaMessage emoji(User user, T emoji) {
			return new KafkaMessage(user.getId(), user.getName(), null, emoji);
		}
	}
}
