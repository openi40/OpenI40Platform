package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartWorkIOMessage extends AbstractBaseTaskIOMessage  implements Serializable{
	private List<UsedSecondaryResourcesInfo> usedResources=new ArrayList<UsedSecondaryResourcesInfo>();

	public List<UsedSecondaryResourcesInfo> getUsedResources() {
		return usedResources;
	}

	public void setUsedResources(List<UsedSecondaryResourcesInfo> usedResources) {
		this.usedResources = usedResources;
	}
}
