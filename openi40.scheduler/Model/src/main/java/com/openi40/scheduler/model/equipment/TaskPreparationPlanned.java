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
 * @author architectures@openi40.org
 *
 */

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

	private TimeSegmentsGroup equipmentEventsGroup;

	private List<TimeSegmentsGroup> executionSlots = new ArrayList<TimeSegmentsGroup>();

	private boolean usingChangeOver = false;
	private TaskPreparationModel metaInfo;
	private double nominalChangeOverTime = 0;

	private double nominalManteinanceBreakTime = 0;
	private double nominalManteinancePeriod = 0;
	private double nominalSetupTime = 0;

	private Task ownerTask;
	private SetupResourceInfos resource = new SetupResourceInfos();
	private List<SetupSecondaryResourceInfos> secondaryResources = new ArrayList<SetupSecondaryResourceInfos>();

	public TaskPreparationPlanned(ApsData context) {
		super(context);

		resetSchedulingData();

	}

	public double getNominalSetupTime() {
		return nominalSetupTime;
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
		return usingChangeOver ? nominalChangeOverTime : nominalSetupTime;
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

	public TimeSegmentsGroup getEquipmentEventsGroup() {
		return equipmentEventsGroup;
	}

	public void setEquipmentEventsGroup(TimeSegmentsGroup equipmentEventsGroup) {
		this.equipmentEventsGroup = equipmentEventsGroup;
	}

	public List<TimeSegmentsGroup> getExecutionSlots() {
		return executionSlots;
	}

	public void setExecutionSlots(List<TimeSegmentsGroup> executionSlots) {
		this.executionSlots = executionSlots;
	}

	public boolean isUsingChangeOver() {
		return usingChangeOver;
	}

	public void setUsingChangeOver(boolean usingChangeOver) {
		this.usingChangeOver = usingChangeOver;
	}

	public TaskPreparationModel getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(TaskPreparationModel metaInfo) {
		this.metaInfo = metaInfo;
	}

	public double getNominalChangeOverTime() {
		return nominalChangeOverTime;
	}

	public void setNominalChangeOverTime(double nominalChangeOverTime) {
		this.nominalChangeOverTime = nominalChangeOverTime;
	}

	public double getNominalManteinanceBreakTime() {
		return nominalManteinanceBreakTime;
	}

	public void setNominalManteinanceBreakTime(double nominalManteinanceBreakTime) {
		this.nominalManteinanceBreakTime = nominalManteinanceBreakTime;
	}

	public double getNominalManteinancePeriod() {
		return nominalManteinancePeriod;
	}

	public void setNominalManteinancePeriod(double nominalManteinancePeriod) {
		this.nominalManteinancePeriod = nominalManteinancePeriod;
	}

	public Task getOwnerTask() {
		return ownerTask;
	}

	public void setOwnerTask(Task ownerTask) {
		this.ownerTask = ownerTask;
	}

	public SetupResourceInfos getResource() {
		return resource;
	}

	public void setResource(SetupResourceInfos resource) {
		this.resource = resource;
	}

	public List<SetupSecondaryResourceInfos> getSecondaryResources() {
		return secondaryResources;
	}

	public void setSecondaryResources(List<SetupSecondaryResourceInfos> secondaryResources) {
		this.secondaryResources = secondaryResources;
	}

	public void setNominalSetupTime(double nominalSetupTime) {
		this.nominalSetupTime = nominalSetupTime;
	}

}