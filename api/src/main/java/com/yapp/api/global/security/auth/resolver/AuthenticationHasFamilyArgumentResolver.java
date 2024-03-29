package com.yapp.api.global.security.auth.resolver;

import static com.yapp.supporter.error.exception.ErrorCode.*;

import com.yapp.api.global.error.exception.ApiException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yapp.api.global.security.auth.bearer.service.JwtUserDetailsService;
import com.yapp.supporter.entity.user.entity.User;

/**
 * Author : daehwan2yo
 * Date : 2022/07/17
 * Info : 
 **/
@Component
public class AuthenticationHasFamilyArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthenticationHasFamily.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext()
															 .getAuthentication();
		if (authentication.isAuthenticated()) {
			User user = ((JwtUserDetailsService.JwtUserDetails)(authentication.getDetails())).getUser();
			if (user.getFamily() != null) {
				return user;
			}
			throw new ApiException(USER_NOT_FOUND_FAMILY);
		}

		throw new ApiException(NO_AUTHENTICATION_ACCESS);
	}
}
