package com.openi40.scheduler.engine.equipment.configuration;

import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationModel;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.UseModel;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.tasks.Task;

public class ConfigurationDataComposer {

	/**
	 * Configure runtime equipment configuration instance main data structure
	 * skeleton
	 * 
	 * @param toConfigure
	 * @param apsLogicOptions
	 * @param scheduledActivity
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskPreparationPlanned configure(TaskPreparationModel toConfigure, ApsLogicOptions apsLogicOptions,
			Task scheduledActivity, ApsData scheduleDataHolder) {
		TaskPreparationPlanned plannedSetup = new TaskPreparationPlanned(scheduleDataHolder);
	
		plannedSetup.getResource().setUsedQty(computeResourceQuantity(toConfigure.getResource(), scheduledActivity));
		plannedSetup.getResource().setMetaInfo(toConfigure.getResource());
		// Not already choosen equipment in the group
		plannedSetup.getResource().setChoosenEquipment(null);
		for (TaskPreparationUseModel<Resource, ResourceGroup> secondaryResource : toConfigure.getSecondaryResources()) {
			TaskPreparationPlanned.SetupSecondaryResourceInfos secondaryEntry = new TaskPreparationPlanned.SetupSecondaryResourceInfos(
					scheduleDataHolder);
			// Not already choosen equipment in the group
			secondaryEntry.setChoosenEquipment(null);
			secondaryEntry.setMetaInfo(secondaryResource);
			secondaryEntry.setUsedQty(computeResourceQuantity(secondaryResource, scheduledActivity));
	
			plannedSetup.getSecondaryResources().add(secondaryEntry);
		}
		return plannedSetup;
	}

	static <EquipmentType extends ITimesheetAllocableObject, EquipmentGroupType extends Group<EquipmentType>> int computeResourceQuantity(
			UseModel<EquipmentGroupType, EquipmentType> resource, Task scheduledActivity) {
		int qty = 0;
		switch (resource.getResourceRequiredCalculationType()) {
		case CONSTANT: {
			qty = resource.getQty();
		}
			break;
		case PROPORTIONAL: {
			int lotsQty = (int) Math.floor(scheduledActivity.getQtyTotal() / resource.getResourceCalculatedLot())
					* resource.getQty();
			qty = lotsQty;
		}
			break;
		}
		return qty;
	}

	/**
	 * Configure runtime equipment configuration instance main data structure
	 * skeleton
	 * 
	 * @param toConfigure
	 * @param apsLogicOptions
	 * @param scheduledActivity
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskExecutionPlanned configure(TaskExecutionModel toConfigure, ApsLogicOptions apsLogicOptions,
			Task scheduledActivity, ApsData scheduleDataHolder) {
		TaskExecutionPlanned workEquipment = new TaskExecutionPlanned();
		workEquipment.getResource().setUsedQty(computeResourceQuantity(toConfigure.getResource(), scheduledActivity));
		workEquipment.getResource().setMetaInfo(toConfigure.getResource());
		// Not already choosen equipment in the group
		workEquipment.getResource().setChoosenEquipment(null);
		for (SecondaryModelInfo secondaryResource : toConfigure.getSecondaryResources()) {
			TaskExecutionPlanned.WorkSecondaryResourceInfos secondaryEntry = new TaskExecutionPlanned.WorkSecondaryResourceInfos(
					scheduleDataHolder);
			// Not already choosen equipment in the group
			secondaryEntry.setChoosenEquipment(null);
			secondaryEntry.setMetaInfo(secondaryResource);
			secondaryEntry.setUsedQty(computeResourceQuantity(secondaryResource, scheduledActivity));
	
			workEquipment.getSecondaryResources().add(secondaryEntry);
		}
		return workEquipment;
	}

	/**
	 * Configure runtime equipment configuration instance main data structure
	 * skeleton preset with indicated machine and secondaryResource
	 * 
	 * @param model
	 * @param presetMachine
	 * @param secondaryResourceList
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskExecutionPlanned configure(TaskExecutionModel model, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> secondaryResourceList, ApsLogicOptions apsLogicOptions, Task task,
			ApsData scheduleDataHolder) {
		TaskExecutionPlanned planned = configure(model, apsLogicOptions, task, scheduleDataHolder);
		planned.getResource().setChoosenEquipment(presetMachine);
		for (SecondaryModelInfo secondaryModel : model.getSecondaryResources()) {
			WorkSecondaryResourceInfos matchingSecondary = null;
			UsedSecondaryResourcesInfo matchingResources = null;
			for (WorkSecondaryResourceInfos secondary : planned.getSecondaryResources()) {
				if (secondary.getMetaInfo() == secondaryModel) {
					matchingSecondary = secondary;
					break;
				}
			}
			for (UsedSecondaryResourcesInfo entry : secondaryResourceList) {
				if (secondaryModel.getGroup().getCode().equals(entry.getResourceGroup())) {
					matchingResources = entry;
					break;
				}
			}
			if (matchingSecondary != null && matchingResources != null) {
				for (Resource resource : secondaryModel.getGroup().getResources()) {
					if (matchingResources.getUsedResourcesCodes().contains(resource.getCode())) {
						matchingSecondary.getChoosenEquipmentList().add(resource);
					}
				}
			}
		}
		return planned;
	}

	/**
	 * Configure runtime equipment configuration instance main data structure
	 * skeleton preset with indicated machine and secondaryResource
	 * 
	 * @param model
	 * @param presetMachine
	 * @param secondaryResourceList
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskPreparationPlanned configure(TaskPreparationModel model, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> secondaryResourceList, ApsLogicOptions apsLogicOptions, Task task,
			ApsData scheduleDataHolder) {
		TaskPreparationPlanned planned = configure(model, apsLogicOptions, task, scheduleDataHolder);
		planned.getResource().setChoosenEquipment(presetMachine);
		for (TaskPreparationUseModel<Resource, ResourceGroup> secondaryModel : model.getSecondaryResources()) {
			SetupSecondaryResourceInfos matchingSecondary = null;
			UsedSecondaryResourcesInfo matchingResources = null;
			for (SetupSecondaryResourceInfos secondary : planned.getSecondaryResources()) {
				if (secondary.getMetaInfo() == secondaryModel) {
					matchingSecondary = secondary;
					break;
				}
			}
			for (UsedSecondaryResourcesInfo entry : secondaryResourceList) {
				if (secondaryModel.getGroup().getCode().equals(entry.getResourceGroup())) {
					matchingResources = entry;
					break;
				}
			}
			if (matchingSecondary != null && matchingResources != null) {
				for (Resource resource : secondaryModel.getGroup().getResources()) {
					if (matchingResources.getUsedResourcesCodes().contains(resource.getCode())) {
						matchingSecondary.getChoosenEquipmentList().add(resource);
					}
				}
			}
		}
		return planned;
	}

	

}
