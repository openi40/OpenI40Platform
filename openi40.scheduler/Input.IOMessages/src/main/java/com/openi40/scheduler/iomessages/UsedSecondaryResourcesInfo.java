package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsedSecondaryResourcesInfo  implements Serializable{
	private String resourceGroup = null;
	private List<String> usedResourcesCodes = new ArrayList<String>();
	public String getResourceGroup() {
		return resourceGroup;
	}
	public void setResourceGroup(String resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
	public List<String> getUsedResourcesCodes() {
		return usedResourcesCodes;
	}
	public void setUsedResourcesCodes(List<String> usedResourcesCodes) {
		this.usedResourcesCodes = usedResourcesCodes;
	}
}
