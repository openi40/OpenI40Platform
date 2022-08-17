package com.openi40.scheduler.model.messages;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UsedSecondaryResourcesInfo {
	private String resourceGroup = null;
	private List<String> usedResourcesCodes = new ArrayList<String>();

	public UsedSecondaryResourcesInfo() {

	}

}
