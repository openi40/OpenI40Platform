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
package com.openi40.scheduler.client.model.aps;

public class ApsMessageDto {
	private String messageCategory = null;
	private String MessageCode;
	private String MessageDescription;
	private String MsgLevel = null;
	private String SourceModule;
	private String SourceObjectClass;
	public ApsMessageDto() {
		
	}
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	public String getMessageCode() {
		return MessageCode;
	}
	public void setMessageCode(String messageCode) {
		MessageCode = messageCode;
	}
	public String getMessageDescription() {
		return MessageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		MessageDescription = messageDescription;
	}
	public String getMsgLevel() {
		return MsgLevel;
	}
	public void setMsgLevel(String msgLevel) {
		MsgLevel = msgLevel;
	}
	public String getSourceModule() {
		return SourceModule;
	}
	public void setSourceModule(String sourceModule) {
		SourceModule = sourceModule;
	}
	public String getSourceObjectClass() {
		return SourceObjectClass;
	}
	public void setSourceObjectClass(String sourceObjectClass) {
		SourceObjectClass = sourceObjectClass;
	}

}
