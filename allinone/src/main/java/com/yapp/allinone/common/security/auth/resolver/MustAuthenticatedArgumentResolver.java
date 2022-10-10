package com.yapp.allinone.common.security.auth.resolver;

import com.yapp.allinone.common.exception.ApiException;
import com.yapp.allinone.common.security.auth.bearer.service.JwtUserDetailsService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.yapp.supporter.error.exception.ErrorCode.NO_AUTHENTICATION_ACCESS;

@Component
public class MustAuthenticatedArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MustAuthenticated.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication.isAuthenticated()) {
            return ((JwtUserDetailsService.JwtUserDetails) (authentication.getDetails())).getUser();
        }

        throw new ApiException(NO_AUTHENTICATION_ACCESS);
    }
}