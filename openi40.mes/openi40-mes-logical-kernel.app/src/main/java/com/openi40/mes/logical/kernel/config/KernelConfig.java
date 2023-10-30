package com.openi40.mes.logical.kernel.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(value = "com.openi40.mes.logical.kernel.config")
@Data
public class KernelConfig implements Serializable {
	public KernelConfig() {

	}

}
