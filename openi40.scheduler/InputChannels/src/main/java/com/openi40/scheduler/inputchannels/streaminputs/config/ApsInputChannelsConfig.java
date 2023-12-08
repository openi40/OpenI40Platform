package com.openi40.scheduler.inputchannels.streaminputs.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@ConfigurationProperties(prefix = "com.openi40.scheduler.apsinputchannels")

public class ApsInputChannelsConfig {
	List<JsonFilesInputConfig> jsonFileInputs = new ArrayList<JsonFilesInputConfig>();
	List<HttpClientInputDataConfig> httpClientInputs = new ArrayList<HttpClientInputDataConfig>();
	public List<JsonFilesInputConfig> getJsonFileInputs() {
		return jsonFileInputs;
	}
	public void setJsonFileInputs(List<JsonFilesInputConfig> jsonFileInputs) {
		this.jsonFileInputs = jsonFileInputs;
	}
	public List<HttpClientInputDataConfig> getHttpClientInputs() {
		return httpClientInputs;
	}
	public void setHttpClientInputs(List<HttpClientInputDataConfig> httpClientInputs) {
		this.httpClientInputs = httpClientInputs;
	}
}
