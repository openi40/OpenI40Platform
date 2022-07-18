package com.openi40.commons.webconfigs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
		httpServletResponse.addHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST, DELETE");
		httpServletResponse.addHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Headers, Origin , Accept , Authorization , X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

		if (httpServletRequest.getMethod().equals("OPTIONS")) {
			httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
