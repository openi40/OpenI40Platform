package com.openi40.scheduler.engine.contextualplugarch;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.openi40.scheduler.common.aps.IApsObject;
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
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface DefaultImplementation {
	public Class<? extends IBusinessLogic<?>> implemented();
	public Class<? extends IApsObject> entityClass();
	public String key() default "defaultImplementation";
	public String switchImplementationProperty() default "";
}
