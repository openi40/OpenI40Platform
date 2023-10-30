package com.openi40.mes.logical.apsbroker.config;

import java.io.Serializable;

import lombok.Data;
@Data
public class AuthConfig implements Serializable {
	private String username = null;
	private String token = null;
	private String password = null;
	public AuthConfig() {

	}

}
