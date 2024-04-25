package com.openi40.scheduler.input.model.tasks;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;


@MappedSuperclass
public class ApsMessageInputDto extends InputDto {
	private Integer position=0;
	private String taskCode = null;
	private String messageCategory = null;
	private String messageCode = null;
	private String messageDescription = null;
	private String msgLevel = null;
	private String sourceModule = null;
	private String sourceObjectClass = null;	
	private Integer globalPosition=0;
	public ApsMessageInputDto() {

	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	public String getMsgLevel() {
		return msgLevel;
	}
	public void setMsgLevel(String msgLevel) {
		this.msgLevel = msgLevel;
	}
	public String getSourceModule() {
		return sourceModule;
	}
	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}
	public String getSourceObjectClass() {
		return sourceObjectClass;
	}
	public void setSourceObjectClass(String sourceObjectClass) {
		this.sourceObjectClass = sourceObjectClass;
	}
	public Integer getGlobalPosition() {
		return globalPosition;
	}
	public void setGlobalPosition(Integer globalPosition) {
		this.globalPosition = globalPosition;
	}

}
