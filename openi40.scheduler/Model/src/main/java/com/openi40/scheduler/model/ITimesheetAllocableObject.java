package com.openi40.scheduler.model;

import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;
import com.openi40.scheduler.model.time.Timesheet;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public interface ITimesheetAllocableObject extends IApsObject, IApsResourcesDependencyTreeObject {
	String getTimesheetMetaInfoCode();

	void setTimesheetMetaInfoCode(String value);

	Timesheet getTimesheet();

	void setTimesheet(Timesheet timesheet);

	ApsData getContext();

	boolean isInfiniteCapacity();

	void setInfiniteCapacity(boolean c);

	@Override
	default ResourceDepsItemMetaInfo getResourceItemInfo() {
		String type=getClass().getSimpleName();
		String uniqueCode=type+":"+getCode();
		ResourceDepsItemMetaInfo iteminfo= new ResourceDepsItemMetaInfo(type,uniqueCode,true);
		iteminfo.setResource(true);
		iteminfo.setLeafNode(true);
		return iteminfo;
	}

	@Override
	default Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		return List.of();
	}

}