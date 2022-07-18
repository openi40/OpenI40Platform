package com.openi40.scheduler.model.equipment;

import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
public class Group<IObjectWithCalendarType extends ITimesheetAllocableObject> extends AbstractApsObject {
	String departmentCode=null;
	protected Group(Class<IObjectWithCalendarType> childType) {
		super(null);
		this.resources=createCleanChild(this, "Resources", childType);
	}

	protected Group(ApsData context,Class<IObjectWithCalendarType> childType) {
		super(context);
		this.resources=createCleanChild(this, "Resources", childType);
	}

	private List<IObjectWithCalendarType> resources = null;

	public  List<IObjectWithCalendarType> getResources() {
		return resources;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	
}