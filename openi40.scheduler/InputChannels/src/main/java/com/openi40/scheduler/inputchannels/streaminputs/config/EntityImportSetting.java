package com.openi40.scheduler.inputchannels.streaminputs.config;

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

public class EntityImportSetting {
	private boolean relativePath = true;
	private boolean noContent=false;
	private String entityName = null;
	private String path = null;
	private boolean multipleObjectsAsArray=true;
	public boolean isRelativePath() {
		return relativePath;
	}
	public void setRelativePath(boolean relativePath) {
		this.relativePath = relativePath;
	}
	public boolean isNoContent() {
		return noContent;
	}
	public void setNoContent(boolean noContent) {
		this.noContent = noContent;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isMultipleObjectsAsArray() {
		return multipleObjectsAsArray;
	}
	public void setMultipleObjectsAsArray(boolean multipleObjectsAsArray) {
		this.multipleObjectsAsArray = multipleObjectsAsArray;
	}
}
