package com.shrinivas.mockServiceForStatusCodes.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("=================================================");
		System.out.println("Incoming request!!!!!!!!!!");
		System.out.println("METHOD :  "+ req.getMethod());
		System.out.println("URI : "+req.getRequestURI());
		System.out.println("=================================================");

		//from here request is passing to controller
		chain.doFilter(request, response);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("=================================================");
        System.out.println("Response Status : " + res.getStatus());
        System.out.println("Time Taken      : " + (endTime - startTime) + " ms");
		System.out.println("=================================================");
	}
}
