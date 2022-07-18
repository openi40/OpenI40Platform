package com.openi40.scheduler.input.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
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
@MappedSuperclass
public class InputDto implements Serializable {
	protected String code = null;
	protected String description = null;
	protected Date modifiedTimestamp = null;
	protected Boolean removed=false;
	protected Map<String, Object> attributesMap = new HashMap<>();
	@Id
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getModifiedTimestamp() {
		return modifiedTimestamp;
	}
	public void setModifiedTimestamp(Date modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}
	public Boolean getRemoved() {
		return removed;
	}
	public void setRemoved(Boolean deleted) {
		this.removed = deleted;
	}
	@javax.persistence.Transient
	public Map<String, Object> getAttributesMap() {
		return attributesMap;
	}
	public void setAttributesMap(Map<String, Object> attributesMap) {
		this.attributesMap = attributesMap;
	}

}
