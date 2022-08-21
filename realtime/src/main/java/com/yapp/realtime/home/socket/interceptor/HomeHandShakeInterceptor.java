package com.yapp.realtime.home.socket.interceptor;

import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.realtime.global.validator.RealTimeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

import java.util.Map;

import static com.yapp.supporter.constant.RealTimeConstant.DEFAULT_SESSION_KEY;

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
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        if (super.beforeHandshake(request, response, wsHandler, attributes)) {
            RealTimeValidator.authentication(request, ErrorCode.NO_AUTHENTICATION_ACCESS);
            String authToken = request.getHeaders()
                    .get(AUTHORIZATION)
                    .get(0);

            RealTimeValidator.startsWith(authToken, BEARER, ErrorCode.BEARER_TOKEN_INVALID);
            String path = request.getURI()
                    .getPath()
                    .substring(PATH_PREFIX.length());

            attributes.put(DEFAULT_SESSION_KEY, Long.parseLong(path.split("/")[0]));
            attributes.put("authToken", authToken);
            return true;
        }

        return false;
    }
}
