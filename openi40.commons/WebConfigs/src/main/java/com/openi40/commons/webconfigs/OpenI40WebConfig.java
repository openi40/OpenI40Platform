package com.openi40.commons.webconfigs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.openi40.webcfg")
public class OpenI40WebConfig {
	private Boolean angularUi=false;
	private Boolean swaggerUi=null;
	public OpenI40WebConfig() {
		
	}
	public Boolean getAngularUi() {
		return angularUi;
	}
	public void setAngularUi(Boolean angularUi) {
		this.angularUi = angularUi;
	}
	public Boolean getSwaggerUi() {
		return swaggerUi;
	}
	public void setSwaggerUi(Boolean swaggerUi) {
		this.swaggerUi = swaggerUi;
	}

}
