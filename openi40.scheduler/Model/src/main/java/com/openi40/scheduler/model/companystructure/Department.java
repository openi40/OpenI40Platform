package com.openi40.scheduler.model.companystructure;

import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.time.Timesheet;


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


public class Department extends AbstractPlantRelatedApsObject
		implements ICompanyStructureNode<Plant>, ITimesheetAllocableObject {
	private static final String SECONDARY_RESOURCE_GROUPS = "SecondaryResourceGroups";
	private static final String MACHINES_GROUPS = "MachinesGroups";
	private static final String WORK_CENTERS = "WorkCenters";

	public Department(ApsData c, Plant parent) {
		super(c);
		this.parent = parent;
	}

	public Department(ApsData c) {
		super(c);

	}

	private boolean infiniteCapacity = false;
	protected String timesheetMetaInfoCode = null;
	protected Timesheet timesheet = null;
	protected Plant parent = null;
	
	protected List<WorkCenter> workCenters = createCleanChild(this, WORK_CENTERS, WorkCenter.class);
	
	protected List<MachinesGroup> machineGroups = createCleanChild(this, MACHINES_GROUPS, MachinesGroup.class);
	
	protected List<ResourceGroup> secondaryResourceGroups = createCleanChild(this, SECONDARY_RESOURCE_GROUPS,
			ResourceGroup.class);

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		for (MachinesGroup machinesGroup : machineGroups) {
			machinesGroup.resetSchedulingData();
		}
		for (WorkCenter machinesGroup : workCenters) {
			machinesGroup.resetSchedulingData();
		}
	}

	public boolean isInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}

	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}

	public Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

	public Plant getParent() {
		return parent;
	}

	public void setParent(Plant parent) {
		this.parent = parent;
	}

	public List<WorkCenter> getWorkCenters() {
		return workCenters;
	}

	public List<MachinesGroup> getMachineGroups() {
		return machineGroups;
	}

	public List<ResourceGroup> getSecondaryResourceGroups() {
		return secondaryResourceGroups;
	}

}