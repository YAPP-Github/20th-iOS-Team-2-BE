package com.yapp.event.home.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

import com.yapp.core.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
@RequiredArgsConstructor
public class HomeHandShakeInterceptor extends OriginHandshakeInterceptor {
	private static final String PATH_PREFIX = "/home/";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
								   ServerHttpResponse response,
								   WebSocketHandler wsHandler,
								   Map<String, Object> attributes) throws Exception {
		if (super.beforeHandshake(request, response, wsHandler, attributes)) {

			try {
				String authToken = request.getHeaders()
										  .get(AUTHORIZATION)
										  .get(0);

				if (authToken.startsWith(BEARER)) {
					String path = request.getURI()
										   .getPath()
										   .substring(PATH_PREFIX.length());

					attributes.put("userId", Long.parseLong(path.split("/")[0]));
					attributes.put("authToken", authToken);
					return true;
				}

				throw new BaseBusinessException(ErrorCode.NO_AUTHENTICATION_ACCESS);
			} catch (NullPointerException n) {
				throw new BaseBusinessException(ErrorCode.TOKEN_IS_BLANK);
			}
		}
		throw new BaseBusinessException(ErrorCode.CONNECTION_PREFIX_ERROR);
	}
}
