package com.yapp.api.global.security.auth.bearer.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yapp.core.error.exception.BaseBusinessException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (BaseBusinessException authException) {
			setErrorResponse(authException, response);
		}
	}

	private void setErrorResponse(BaseBusinessException userException, HttpServletResponse response) throws
																									 IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.sendError(userException.getErrorCode()
										.getStatus(),
						   userException.getErrorCode()
										.getDetail());
	}
}
