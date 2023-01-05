package com.openi40.scheduler.input.model.tasks;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.input.model.InputDto;

import lombok.Data;
@MappedSuperclass

public class UsedSecondaryResourcesInfoInputDto extends InputDto {
	private String taskCode=null;
	private String resourceGroup = null;
	@Transient
	private List<String> usedResourcesCodes = new ArrayList<String>();
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getResourceGroup() {
		return resourceGroup;
	}
	public void setResourceGroup(String resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
	@Transient
	public List<String> getUsedResourcesCodes() {
		return usedResourcesCodes;
	}
	public void setUsedResourcesCodes(List<String> usedResourcesCodes) {
		this.usedResourcesCodes = usedResourcesCodes;
	}
	
}
