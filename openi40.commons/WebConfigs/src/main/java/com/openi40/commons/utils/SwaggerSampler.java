package com.openi40.commons.utils;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
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
//This profile is switched on when you whant to sample swagger json of 
//one of the openi40 bootable applications
@Profile(SwaggerSampler.SWAGGER_SAMPLER_PROFILE)
@Component
public class SwaggerSampler {
	private static Logger LOGGER = LoggerFactory.getLogger(SwaggerSampler.class);
	public final static String SWAGGER_SAMPLER_PROFILE = "swagger-sampler-profile";

	public SwaggerSampler() {

	}

	static final String swaggerRelativePath = "/v2/api-docs";

	String appendWithSlash(String... values) {
		StringBuffer sb = new StringBuffer();
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				String thisComponent = values[i];
				while (thisComponent.endsWith("/")) {
					thisComponent = thisComponent.substring(0, thisComponent.length() - 1);
				}
				while (thisComponent.startsWith("/")) {
					thisComponent = thisComponent.substring(1, thisComponent.length());
				}
				if (i > 0)
					sb.append("/");
				sb.append(thisComponent);
			}

		}
		return sb.toString();
	}

	@Value("${server.port}")
	String serverPort;
	@Value("${server.servlet.contextPath}")
	String contextPath;
	@Value("${openi40.swaggerdump}")
	String swaggerDump;

	@EventListener
	public void handleContextStart(ContextRefreshedEvent cse) throws IOException {
		LOGGER.info("SwaggerSampler handling context started event.");
		final String completeUrl = appendWithSlash("http://127.0.0.1:" + serverPort, contextPath, swaggerRelativePath);
		LOGGER.info("Try sampling swagger from URL:" + completeUrl);
		URL url = new URL(completeUrl);

		InputStream is = null;
		FileOutputStream fos = null;
		try {
			LOGGER.info("Start copying swagger to:" + swaggerDump);
			is = url.openConnection().getInputStream();
			fos = new FileOutputStream(swaggerDump);
			FileCopyUtils.copy(is, fos);
			LOGGER.info("swagger " + swaggerDump + " write complete");
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				fos.flush();
				fos.close();
			} catch (Throwable t) {
			}
		}
		System.exit(0);
	}
}
