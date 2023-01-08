package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class UsedSecondaryResourcesInfo  implements Serializable{
	private String resourceGroup = null;
	private List<String> usedResourcesCodes = new ArrayList<String>();
}
