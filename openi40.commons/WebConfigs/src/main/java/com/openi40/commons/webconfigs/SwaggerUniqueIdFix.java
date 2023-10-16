package com.openi40.commons.webconfigs;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
//import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
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
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
public class SwaggerUniqueIdFix implements OperationBuilderPlugin {
	static Logger LOGGER = LoggerFactory.getLogger(SwaggerUniqueIdFix.class);
	
	@Override
	public void apply(OperationContext context) {

		Optional<ApiOperation> methodAnnotation = context.findControllerAnnotation(ApiOperation.class);

		ResolvedType returnType = context.getReturnType();
		String typeName = returnType.getTypeName();
		Class<?> type = returnType.getErasedType();
		String typePostfix = type.getSimpleName();
		if (ResponseEntity.class.isAssignableFrom(type) ) {
			List<ResolvedType> typeParams = returnType.getTypeParameters();
			if (typeParams != null && !typeParams.isEmpty()) {
				returnType = typeParams.get(0);
				type = returnType.getErasedType();
				typeName = returnType.getTypeName();
				typePostfix = type.getSimpleName();
			}
		}
		if (Collection.class.isAssignableFrom(type) || type.getName().equals("org.springframework.data.domain.Page") ) {
			List<ResolvedType> typeParams = returnType.getTypeParameters();
			for (ResolvedType resolvedType : typeParams) {
				typePostfix += "_" + resolvedType.getErasedType().getSimpleName();
			}
		}
		Operation operationBuilder = context.operationBuilder().build();

		String uniqueId = operationBuilder.getUniqueId().replaceAll("Using(GET|POST|PUT|DELETE)", "");
		uniqueId = avoidNumberedNaming(uniqueId);
		uniqueId += "_" + typePostfix;
		// If nickname exists, populate the value of nickname annotation into uniqueId
		String fillId = methodAnnotation.transform(ApiOperation::nickname).or(uniqueId);

		context.operationBuilder().uniqueId(fillId);
		context.operationBuilder().codegenMethodNameStem(fillId);
	}

	@Override
	public boolean supports(DocumentationType delimiter) {
		return SwaggerPluginSupport.pluginDoesApply(delimiter);
	}

	String avoidNumberedNaming(String uniqueId) {
		int underlineOffset = uniqueId.lastIndexOf("_");
		if (underlineOffset != -1) {
			String nrSlice = uniqueId.substring(underlineOffset + 1);
			boolean number = true;
			char chars[] = nrSlice.toCharArray();
			number = chars.length > 0;
			for (char c : chars) {
				number = number && Character.isDigit(c);
			}
			if (number) {
				uniqueId = uniqueId.substring(0, underlineOffset);
			}
		}
		return uniqueId;
	}

}
