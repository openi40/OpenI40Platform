package com.openi40.mes.io.logical.apsbroker.config;

public class WebInterfaceConfig {
	private String url = null;
	private AuthConfig authorization=null;
	public WebInterfaceConfig() {

	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public AuthConfig getAuthorization() {
		return authorization;
	}
	public void setAuthorization(AuthConfig authorization) {
		this.authorization = authorization;
	}

}
