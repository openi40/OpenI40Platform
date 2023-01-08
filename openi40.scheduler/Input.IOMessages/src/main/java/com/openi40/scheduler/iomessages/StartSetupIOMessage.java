package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class StartSetupIOMessage extends AbstractBaseTaskIOMessage  implements Serializable{
	private List<UsedSecondaryResourcesInfo> usedResources=new ArrayList<UsedSecondaryResourcesInfo>();
}
