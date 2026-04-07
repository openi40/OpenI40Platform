package com.openi40.commons.webconfigs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Sort;
import org.springframework.util.StopWatch;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomizer;
import com.openi40.commons.webconfigs.SwaggerAdditionalModelsProvider;
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
public class SwaggerConfig {
	@Autowired(required = false)
	List<SwaggerAdditionalModelsProvider> swaggerAdditionalModelsProviders;

	public static class OrderEntry {
		String property = null, direction = null, nullHandling = null;
		boolean ignoreCase = false;

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}

		public String getNullHandling() {
			return nullHandling;
		}

		public void setNullHandling(String nullHandling) {
			this.nullHandling = nullHandling;
		}

		public boolean isIgnoreCase() {
			return ignoreCase;
		}

		public void setIgnoreCase(boolean ignoreCase) {
			this.ignoreCase = ignoreCase;
		}
	}

	public static class OvverridenSort extends ArrayList<OrderEntry> {
	};


	private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

	@Bean
	public OpenAPI openi40OpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Openi40")
				.description("The open source industry 4.0 production scheduler & MES platform")
				.version("v0.0.1")
				.contact(new Contact().name("Openi40").url("http://www.openi40.org/"))
				.license(new License().name("License of API").url("#API license URL")))
				.externalDocs(new ExternalDocumentation()
				.description("Term of Services")
				.url("#Term of Services"));
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("openi40-public")
				.packagesToScan("com.openi40")
				.pathsToMatch("/**")
				.build();
	}







}
