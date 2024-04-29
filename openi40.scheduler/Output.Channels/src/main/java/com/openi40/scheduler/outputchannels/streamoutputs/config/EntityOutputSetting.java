package com.openi40.scheduler.outputchannels.streamoutputs.config;

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

public class EntityOutputSetting {
	private boolean relativePath = true;
	private boolean noContent=false;
	private String entityName = null;
	private int nEntriesGrouping=-1;
	private String path = null;
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
	public int getNEntriesGrouping() {
		return nEntriesGrouping;
	}
	public void setNEntriesGrouping(int nEntriesGrouping) {
		this.nEntriesGrouping = nEntriesGrouping;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
