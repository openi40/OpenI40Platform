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

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.openi40.commons.webconfigs.SwaggerAdditionalModelsProvider;
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

@EnableSwagger2WebFlux
@EnableSwagger2WebMvc
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
	protected Docket swaggerApi() {
		log.info("Begin swaggerAPI()");
		StopWatch watch = new StopWatch();
		watch.start();
		Contact contact = new Contact("Openi40", "http://www.openi40.org/", "");
		ApiInfo apiInfo = new ApiInfo("Openi40", "The open source industy 4.0 production scheduler & MES platform", "", "#Term of Services", contact, "License of API", "#API license URL", Collections.emptyList());

		// view https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
		Docket d = new Docket(DocumentationType.SWAGGER_2).directModelSubstitute(LocalDate.class, String.class).host("127.0.0.1")
				// .securitySchemes(Arrays.asList(securityScheme()))
				//.directModelSubstitute(Sort.class, OvverridenSort.class)
		// .securityContexts(Arrays.asList(securityContext()));
		;

		if (swaggerAdditionalModelsProviders != null) {
			TypeResolver typeResolver = new TypeResolver();
			for (SwaggerAdditionalModelsProvider swaggerAdditionalModelsProvider : swaggerAdditionalModelsProviders) {
				List<Class> newModels = swaggerAdditionalModelsProvider.get();
				for (Class newModel : newModels) {
					ResolvedType first = typeResolver.resolve(newModel);
					log.info("Adding custom swagger model:" + newModel.getName());
					d = d.additionalModels(first);
				}
			}
		}
		
		String restApiBasePackage="com.openi40";
		d =d.select().apis(RequestHandlerSelectors.basePackage(restApiBasePackage))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo);		
		log.info("End swaggerAPI()");
		return d;
	}


	@Bean
	protected UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().build();
	}



}
