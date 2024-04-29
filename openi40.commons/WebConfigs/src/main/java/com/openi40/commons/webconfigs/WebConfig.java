package com.openi40.commons.webconfigs;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Configuration
@EnableAsync
public class WebConfig extends WebMvcConfigurationSupport {

	ObjectMapper objectMapper = null;
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };
	OpenI40WebConfig webConfig = null;

	public WebConfig(@Autowired ObjectMapper objectMapper, @Autowired(required = false) OpenI40WebConfig webConfig) {
		this.objectMapper = objectMapper;
		this.webConfig = webConfig;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins("*");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (this.webConfig == null || this.webConfig.getSwaggerUi() == null || this.webConfig.getSwaggerUi()) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		if (this.webConfig != null && this.webConfig.getAngularUi() != null && this.webConfig.getAngularUi()) {
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(true)
					.addResolver(new PathResourceResolver() {
						@Override
						protected Resource getResource(String resourcePath, Resource location) throws IOException {
							Resource requestedResource = location.createRelative(resourcePath);

							return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
									: new ClassPathResource("/static/index.html");
						}
					});
			registry.addResourceHandler("").addResourceLocations("classpath:/static/").resourceChain(true)
					.addResolver(new PathResourceResolver() {
						@Override
						protected Resource getResource(String resourcePath, Resource location) throws IOException {
							Resource requestedResource = location.createRelative(resourcePath);

							return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
									: new ClassPathResource("/static/index.html");
						}
					});

		}
	}

	@Bean
	protected ConcurrentTaskExecutor getTaskExecutor() {
		return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5));
	}

	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
				jacksonConverter.setPrettyPrint(true);
				jacksonConverter.setObjectMapper(objectMapper);
			}
		}
	}

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(getTaskExecutor());
		super.configureAsyncSupport(configurer);
	}
	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/").setViewName("forward:/openi40/index.html"); }
	 */
}
