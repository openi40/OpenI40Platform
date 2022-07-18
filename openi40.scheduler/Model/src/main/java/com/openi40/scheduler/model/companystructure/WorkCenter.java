package com.openi40.scheduler.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.ChangeOverMatrixItem;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
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
public class WorkCenter extends MachinesGroup
		implements ICompanyStructureNode<Department>, IPlantRelatedApsObject, ITimesheetAllocableObject {
	private boolean infiniteCapacity = false;
	private List<ChangeOverMatrixItem> changeOverMatrixItems = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	Department parent = null;
	protected String timesheetMetaInfoCode = null;
	protected Timesheet timesheet = null;

	public WorkCenter(ApsData c, Department parentDept) {
		super(c);
		this.parent = parentDept;
	}

	public WorkCenter(ApsData c) {
		super(c);

	}

	@Override
	public String getCompanyCode() {

		return parent != null ? parent.getCompanyCode() : null;
	}

	@Override
	public String getPlantCode() {
		return parent != null ? parent.getPlantCode() : null;
	}

	@Override
	public void resetSchedulingData() {
		for (Machine machine : getResources()) {
			machine.resetSchedulingData();
		}
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}
}