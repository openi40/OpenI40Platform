package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;

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
public class Group<IObjectWithCalendarType extends ITimesheetAllocableObject> extends AbstractApsObject
		implements IApsResourcesDependencyTreeObject {
	String departmentCode = null;

	protected Group(Class<IObjectWithCalendarType> childType) {
		super(null);
		this.resources = createCleanChild(this, "Resources", childType);
	}

	protected Group(ApsData context, Class<IObjectWithCalendarType> childType) {
		super(context);
		this.resources = createCleanChild(this, "Resources", childType);
	}

	private List<IObjectWithCalendarType> resources = null;

	public List<IObjectWithCalendarType> getResources() {
		return resources;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		String type = getClass().getSimpleName();
		String uniqueCode = type + ":" + getCode();
		ResourceDepsItemMetaInfo iteminfo = new ResourceDepsItemMetaInfo(type, uniqueCode, false);
		iteminfo.setResource(false);
		iteminfo.setLeafNode(false);
		iteminfo.setResourceGroup(true);
		return iteminfo;
	}

	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		return new ArrayList<IApsResourcesDependencyTreeObject>(resources);
	}

}