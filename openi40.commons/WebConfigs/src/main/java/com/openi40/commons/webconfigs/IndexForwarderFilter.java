package com.openi40.commons.webconfigs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
public class IndexForwarderFilter extends OncePerRequestFilter {
	static final String WEBCONTEXT_VARIABLE = "${server.servlet.context-path}";
	@Value(WEBCONTEXT_VARIABLE)
	String contextPath;
	static Logger LOGGER = LoggerFactory.getLogger(IndexForwarderFilter.class);

	public IndexForwarderFilter() {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String method = request.getMethod();
		String uri = request.getRequestURI();
		if (method != null && method.equalsIgnoreCase("GET") && uri != null
				&& (uri.equals(contextPath) || uri.equals(contextPath + "/"))) {
			response.sendRedirect("index.html");
		} else
			filterChain.doFilter(request, response);

	}

}
