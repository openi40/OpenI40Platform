package com.openi40.mes.io.logical.apsbroker.config;

import java.io.Serializable;

public class AuthConfig implements Serializable {
	private String username = null;
	private String token = null;
	private String password = null;
	public AuthConfig() {

	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
