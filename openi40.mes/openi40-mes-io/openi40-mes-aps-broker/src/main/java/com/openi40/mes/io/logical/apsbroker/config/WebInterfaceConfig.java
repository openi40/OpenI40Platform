package com.openi40.mes.io.logical.apsbroker.config;

import lombok.Data;

@Data
public class WebInterfaceConfig {
	private String url = null;
	private AuthConfig authorization=null;
	public WebInterfaceConfig() {

	}

}
