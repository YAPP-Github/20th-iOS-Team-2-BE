package com.yapp.realtime.home.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public class SocketResponse<T> {
	private T result;

	public SocketResponse(T result) {
		this.result = result;
	}

	public static <T> SocketResponse from(T detail) {
		return new SocketResponse(detail);
	}

	public T getResult() {
		return result;
	}

	public String getAsJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(result);
	}
}
