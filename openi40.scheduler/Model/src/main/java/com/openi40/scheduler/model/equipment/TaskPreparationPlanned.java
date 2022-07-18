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

import lombok.Data;
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
public class TaskPreparationPlanned extends AbstractApsObject implements IReferencingMetaInfo<TaskPreparationModel> {
	public static class SetupResourceInfos extends Use<Machine, TaskPreparationUseModel<Machine, MachinesGroup>, MachinesGroup> {
		public SetupResourceInfos() {
			super(null);
		}

		public SetupResourceInfos(ApsData context) {
			super(context);

		}
	}

	public static class SetupSecondaryResourceInfos extends Use<Resource, TaskPreparationUseModel<Resource, ResourceGroup>, ResourceGroup> {
		public SetupSecondaryResourceInfos() {
			super(null);
		}

		public SetupSecondaryResourceInfos(ApsData context) {
			super(context);

		}
	}

	private TimeSegmentsGroup EquipmentEventsGroup;

	private List<TimeSegmentsGroup> ExecutionSlots = new ArrayList<TimeSegmentsGroup>();

	private boolean UsingChangeOver = false;
	private TaskPreparationModel MetaInfo;
	private double NominalChangeOverTime = 0;

	private double nominalManteinanceBreakTime = 0;
	private double nominalManteinancePeriod = 0;
	private double NominalSetupTime = 0;

	private Task OwnerTask;
	private SetupResourceInfos Resource = new SetupResourceInfos();
	private List<SetupSecondaryResourceInfos> SecondaryResources = new ArrayList<SetupSecondaryResourceInfos>();

	public TaskPreparationPlanned(ApsData context) {
		super(context);

		resetSchedulingData();

	}

	public double getNominalSetupTime() {
		return NominalSetupTime;
	}

	@Override
	public ICloneable cleanClone() throws CloneNotSupportedException {
		TaskPreparationPlanned equipmentPlanned = new TaskPreparationPlanned(getContext());
		equipmentPlanned.setMetaInfo(getMetaInfo());
		equipmentPlanned.setNominalChangeOverTime(getNominalChangeOverTime());
		equipmentPlanned.setNominalManteinanceBreakTime(getNominalManteinanceBreakTime());
		equipmentPlanned.setNominalManteinancePeriod(getNominalManteinancePeriod());
		equipmentPlanned.setNominalSetupTime(getNominalSetupTime());
		equipmentPlanned.setUsingChangeOver(isUsingChangeOver());
		equipmentPlanned.setResource((SetupResourceInfos) getResource().cleanClone());
		equipmentPlanned.setOwnerTask(getOwnerTask());
		equipmentPlanned.owner = owner;
		for (SetupSecondaryResourceInfos secondaryResource : getSecondaryResources()) {
			equipmentPlanned.getSecondaryResources().add((SetupSecondaryResourceInfos) secondaryResource.cleanClone());
		}

		return equipmentPlanned;
	}

	public double getSetupTime() {
		return UsingChangeOver ? NominalChangeOverTime : NominalSetupTime;
	}

	@Override
	public void resetSchedulingData() {
		setEquipmentEventsGroup(new TimeSegmentsGroup(TimeSegmentType.SETUP_TIME, this));
		getResource().resetSchedulingData();
		for (SetupSecondaryResourceInfos secondaryResource : getSecondaryResources()) {
			secondaryResource.resetSchedulingData();
		}

	}

	public String toString() {
		return "{" + this.getCode() + "}";
	}

}