package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;
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
public class TaskExecutionPlanned extends AbstractApsObject implements IReferencingMetaInfo<TaskExecutionModel> {
	public static class WorkResourceInfos extends Use<Machine, TaskExecutionUseModel<Machine, MachinesGroup>, MachinesGroup> {
		public WorkResourceInfos(ApsData context) {
			super(context);
		}
	}

	public static class WorkSecondaryResourceInfos extends Use<Resource, TaskExecutionUseModel<Resource, ResourceGroup>, ResourceGroup> {
		public WorkSecondaryResourceInfos(ApsData context) {
			super(context);
		}
	}

	public TaskExecutionPlanned() {
		super(null);
		setEquipmentEventsGroup(new TimeSegmentsGroup(TimeSegmentType.RESOURCE_USE_TIME, this));
		setResource(new WorkResourceInfos(null));
	}

	public TaskExecutionPlanned(ApsData context) {
		super(context);
		setEquipmentEventsGroup(new TimeSegmentsGroup(TimeSegmentType.RESOURCE_USE_TIME, this));
		setResource(new WorkResourceInfos(context));
	}

	@Override
	public void resetSchedulingData() {
		setEquipmentEventsGroup(new TimeSegmentsGroup(TimeSegmentType.SETUP_TIME, this));
		getResource().resetSchedulingData();
		for (WorkSecondaryResourceInfos secondaryResource : getSecondaryResources()) {
			secondaryResource.resetSchedulingData();
		}

	}

	private WorkResourceInfos Resource;

	public final WorkResourceInfos getResource() {
		return Resource;
	}

	public final void setResource(WorkResourceInfos value) {
		Resource = value;
	}

	private List<WorkSecondaryResourceInfos> SecondaryResources = new ArrayList<WorkSecondaryResourceInfos>();

	public final List<WorkSecondaryResourceInfos> getSecondaryResources() {
		return SecondaryResources;
	}

	public final void setSecondaryResources(List<WorkSecondaryResourceInfos> value) {
		SecondaryResources = value;
	}

	private List<TimeSegmentsGroup> ExecutionSlots = new ArrayList<TimeSegmentsGroup>();

	public final List<TimeSegmentsGroup> getExecutionSlots() {
		return ExecutionSlots;
	}

	public final void setExecutionSlots(List<TimeSegmentsGroup> value) {
		ExecutionSlots = value;
	}

	private Task OwnerTask;

	public final Task getOwnerTask() {
		return OwnerTask;
	}

	public final void setOwnerTask(Task value) {
		OwnerTask = value;
	}

	private TaskExecutionModel Model;

	public  TaskExecutionModel getMetaInfo() {
		return Model;
	}

	public  void setMetaInfo(TaskExecutionModel value) {
		Model = value;
	}

	private double NominalWorkTime;

	public final double getNominalWorkTime() {
		return NominalWorkTime;
	}

	public final void setNominalWorkTime(double value) {
		NominalWorkTime = value;
	}

	private TimeSegmentsGroup EquipmentEventsGroup;

	public final TimeSegmentsGroup getEquipmentEventsGroup() {
		return EquipmentEventsGroup;
	}

	public final void setEquipmentEventsGroup(TimeSegmentsGroup value) {
		EquipmentEventsGroup = value;
	}

	@Override
	public ICloneable cleanClone() throws CloneNotSupportedException {
		TaskExecutionPlanned equipmentPlanned = new TaskExecutionPlanned();
		equipmentPlanned.setMetaInfo(getMetaInfo());
		equipmentPlanned.setNominalWorkTime(getNominalWorkTime());

		equipmentPlanned.setResource((WorkResourceInfos) getResource().cleanClone());
		equipmentPlanned.setOwnerTask(getOwnerTask());
		equipmentPlanned.owner = owner;
		for (WorkSecondaryResourceInfos secondaryResource : getSecondaryResources()) {
			equipmentPlanned.getSecondaryResources().add((WorkSecondaryResourceInfos) secondaryResource.cleanClone());
		}
		return equipmentPlanned;
	}
}