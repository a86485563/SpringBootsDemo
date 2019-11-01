package com.biquan.helloworld.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class InputStreamWrapperFilter extends OncePerRequestFilter {

	 @Override
	    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
	        ServletRequest servletRequest = new InputStreamHttpServletRequestWrapper(httpServletRequest);

	        filterChain.doFilter(servletRequest, httpServletResponse);
	    }

}
