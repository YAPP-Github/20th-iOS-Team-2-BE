package com.yapp.allinone.socket.validator;

import com.google.common.net.HttpHeaders;
import com.yapp.allinone.common.exception.RealTimeException;
import com.yapp.supporter.error.exception.ErrorCode;
import org.springframework.http.server.ServerHttpRequest;

import java.util.Objects;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
public class RealTimeValidator {

    public static void authentication(ServerHttpRequest request, ErrorCode noAuthenticationAccess) {
        if (Objects.isNull(request.getHeaders()
                .get(HttpHeaders.AUTHORIZATION))) {
            throw new RealTimeException(noAuthenticationAccess);
        }
    }

    public static void startsWith(String authToken, String bearer, ErrorCode bearerTokenInvalid) {
        if(!authToken.startsWith(bearer)) {
            throw new RealTimeException(bearerTokenInvalid);
        }
    }

    public static void familyId(boolean noFamilyId, ErrorCode noFamilyInHeader) {
        if(noFamilyId) {
            throw new RealTimeException(noFamilyInHeader);
        }
    }
}
