package com.shrinivas.mockServiceForStatusCodes.interceptor;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor{

	private final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.warn("incoming request in prehandle method");
		log.warn("This is the testing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		// TODO Auto-generated method stub
		log.warn("Request in afterCompletion method");

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
