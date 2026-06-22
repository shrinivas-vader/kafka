package com.shrinivas.mockServiceForStatusCodes.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shrinivas.mockServiceForStatusCodes.interceptor.LoggingInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private LoggingInterceptor loggingInterceptor;
	
	public WebMvcConfig(LoggingInterceptor loggingInterceptor) {
		this.loggingInterceptor=loggingInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		System.out.println("From addinterceptor webmvcconfigurer");
		registry.addInterceptor(loggingInterceptor);
	}
	
}
