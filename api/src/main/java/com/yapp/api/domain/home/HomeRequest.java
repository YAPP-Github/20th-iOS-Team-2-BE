package com.yapp.api.domain.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
public class HomeRequest {

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Greeting {
		private String content;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetWithEmoji {
		private Integer emojiNumber;
	}
}
