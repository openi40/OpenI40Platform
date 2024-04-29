package com.openi40.scheduler.model.companystructure;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.ChangeOverMatrixItem;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.time.Timesheet;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class WorkCenter extends MachinesGroup
		implements ICompanyStructureNode<Department>, IPlantRelatedApsObject, ITimesheetAllocableObject {
	private boolean infiniteCapacity = false;
	private List<ChangeOverMatrixItem> changeOverMatrixItems = new ArrayList<>();
	
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

	public boolean isInfiniteCapacity() {
		return infiniteCapacity;
	}

	public void setInfiniteCapacity(boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}

	public List<ChangeOverMatrixItem> getChangeOverMatrixItems() {
		return changeOverMatrixItems;
	}

	public void setChangeOverMatrixItems(List<ChangeOverMatrixItem> changeOverMatrixItems) {
		this.changeOverMatrixItems = changeOverMatrixItems;
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

	public Department getParent() {
		return parent;
	}
}