package com.openi40.scheduler.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.time.Timesheet;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
@Data

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
	@Setter(value = AccessLevel.NONE)
	protected List<WorkCenter> workCenters = createCleanChild(this, WORK_CENTERS, WorkCenter.class);
	@Setter(value = AccessLevel.NONE)
	protected List<MachinesGroup> machineGroups = createCleanChild(this, MACHINES_GROUPS, MachinesGroup.class);
	@Setter(value = AccessLevel.NONE)
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

}