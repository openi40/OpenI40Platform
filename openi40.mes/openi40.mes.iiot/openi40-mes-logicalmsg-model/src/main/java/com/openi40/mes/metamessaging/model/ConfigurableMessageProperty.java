package com.openi40.mes.metamessaging.model;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

@Retention(CLASS)

@Target(value = {ElementType.METHOD,ElementType.FIELD})
public @interface ConfigurableMessageProperty {
	public String propertyDescription() default "";
	public String[] possibleValues() default {};
	public Class[] possibleTypes() default {};
}
