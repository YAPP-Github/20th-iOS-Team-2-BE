package com.yapp.core.util;

import com.yapp.core.persistance.user.entity.User;

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
	public static KafkaMessage<String> greetingProducing(User user, String content) {
		return KafkaMessage.from(user, content);
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class KafkaMessage<T> {
		private Long userId;
		private String name;
		private T content;

		public static <T> KafkaMessage from(User user, T body) {
			return new KafkaMessage(user.getId(), user.getName(), body);
		}
	}
}
