package com.openi40.scheduler.common.datamodel;
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
public class ValidationMessage {

	String message = null;
	String propertyName = null;
	Class referencedType = null;
	Object entry = null;
	Object invalidReferenceValue = null;

	public ValidationMessage(String message, Object entry) {
		this.message = message;
		this.entry = entry;
	}

	public ValidationMessage(String message, String name, Class referencedType, Object entry) {
		this.message = message;
		this.propertyName = name;
		this.entry = entry;
		this.referencedType = referencedType;
	}

	public ValidationMessage(String message, String name, Class referencedType, Object value, Object entry) {
		this.message = message;
		this.propertyName = name;
		this.entry = entry;
		this.referencedType = referencedType;
		this.invalidReferenceValue = value;
	}

	public String getMessage() {
		return message;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Class getReferencedType() {
		return referencedType;
	}

	public Object getEntry() {
		return entry;
	}

	public Object getInvalidReferenceValue() {
		return invalidReferenceValue;
	}

	@Override
	public String toString() {
		String _value = "{message=" + message + ",propertyName=" + propertyName + ",referencedType=" + referencedType
				+ ",invalidReferenceValue=" + invalidReferenceValue + ",entry=" + entry + "}";
		return _value;
	}
}
