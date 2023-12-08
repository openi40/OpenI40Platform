package com.openi40.scheduler.model.messages;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;


public class UsedSecondaryResourcesInfo extends AbstractApsObject {

	private String resourceGroup = null;
	private List<String> usedResourcesCodes = new ArrayList<String>();

	public UsedSecondaryResourcesInfo(ApsData context) {
		super(context);
	}

	public UsedSecondaryResourcesInfo(UsedSecondaryResourcesInfo c) {
		super(c.getContext());
		this.resourceGroup = c.resourceGroup;
		if (c.usedResourcesCodes != null)
			this.usedResourcesCodes = new ArrayList<String>(c.usedResourcesCodes);
	}

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
