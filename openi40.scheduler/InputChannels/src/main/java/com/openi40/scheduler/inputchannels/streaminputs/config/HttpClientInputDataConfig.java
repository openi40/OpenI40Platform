package com.openi40.scheduler.inputchannels.streaminputs.config;

import org.springframework.context.annotation.Configuration;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Configuration

public class HttpClientInputDataConfig extends AbstractInputDataStreamFactoryConfig<EntityImportSetting> {
	private boolean useBasicAuthentication = false;
	private String username = null;
	private String password = null;
	private String authenticationToken=null;
	private String baseURL=null;
	public boolean isUseBasicAuthentication() {
		return useBasicAuthentication;
	}
	public void setUseBasicAuthentication(boolean useBasicAuthentication) {
		this.useBasicAuthentication = useBasicAuthentication;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
