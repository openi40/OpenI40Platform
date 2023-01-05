package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationModel;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.equipment.UseModel;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.tasks.Task;

 class ProducingConfigurationDataComposer {

	/**
	 * Configure runtime equipment configuration instance main data structure
	 * skeleton
	 * 
	 * @param toConfigure
	 * @param scheduledActivity
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskPreparationPlanned configure(TaskPreparationModel toConfigure, Task scheduledActivity,
			ApsData scheduleDataHolder) {
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
	 * @param scheduledActivity
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskExecutionPlanned configure(TaskExecutionModel toConfigure, Task scheduledActivity,
			ApsData scheduleDataHolder) {
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
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskExecutionPlanned configure(TaskExecutionModel model, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> secondaryResourceList, Task task, ApsData scheduleDataHolder) {
		TaskExecutionPlanned planned = configure(model, task, scheduleDataHolder);
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
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	static TaskPreparationPlanned configure(TaskPreparationModel model, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> secondaryResourceList, Task task, ApsData scheduleDataHolder) {
		TaskPreparationPlanned planned = configure(model, task, scheduleDataHolder);
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

	static SetupSecondaryResourceInfos clone(SetupSecondaryResourceInfos s) {
		SetupSecondaryResourceInfos clone = new SetupSecondaryResourceInfos(s.getContext());
		clone.setMetaInfo(s.getMetaInfo());
		clone.setChoosenEquipment(s.getChoosenEquipment());
		clone.setChoosenEquipmentList(new ArrayList<>(s.getChoosenEquipmentList()));
		return clone;
	}

	static TaskEquipmentInfo clone(TaskEquipmentInfo info, ApsLogicOptions apsLogicOptions, Task task,
			ApsData scheduleDataHolder) {
		TaskEquipmentInfo clone = createTaskEquipmentInfo(info.getMetaInfo(),
				info.getExecution().getResource().getChoosenEquipment(), task, scheduleDataHolder);
		for (SetupSecondaryResourceInfos s : info.getPreparation().getSecondaryResources()) {
			info.getPreparation().getSecondaryResources().add(clone(s));
		}
		for (WorkSecondaryResourceInfos s : info.getExecution().getSecondaryResources()) {
			info.getExecution().getSecondaryResources().add(clone(s));
		}
		return clone;
	}

	static WorkSecondaryResourceInfos clone(WorkSecondaryResourceInfos s) {
		WorkSecondaryResourceInfos clone = new WorkSecondaryResourceInfos(s.getContext());
		clone.setMetaInfo(s.getMetaInfo());
		clone.setChoosenEquipment(s.getChoosenEquipment());
		clone.setChoosenEquipmentList(new ArrayList<>(s.getChoosenEquipmentList()));
		return clone;
	}

	static TaskEquipmentInfo createTaskEquipmentInfo(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			Task task, ApsData scheduleDataHolder) {
		TaskEquipmentInfo tei = new TaskEquipmentInfo(scheduleDataHolder);
		tei.setMetaInfo(modelInfo);
		tei.setPreparation(new TaskPreparationPlanned(scheduleDataHolder));
		tei.setExecution(new TaskExecutionPlanned(scheduleDataHolder));
		tei.getPreparation().setMetaInfo(modelInfo.getPreparationModel());
		tei.getExecution().setMetaInfo(modelInfo.getExecutionModel());
		tei.setTaskInfo(new TaskProcessInfo(scheduleDataHolder, modelInfo.getTaskMetaInfo()));
		tei.getPreparation().getResource().setMetaInfo(modelInfo.getPreparationModel().getResource());
		tei.getExecution().getResource().setMetaInfo(modelInfo.getExecutionModel().getResource());
		tei.getPreparation().getResource().setChoosenEquipment(presetMachine);
		tei.getExecution().getResource().setChoosenEquipment(presetMachine);
		return tei;
	}

	static TaskEquipmentInfo createTaskEquipmentInfo(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList,
			List<UsedSecondaryResourcesInfo> executionSecondaryResourcesList, Task task,
			ApsData scheduleDataHolder) {
		TaskEquipmentInfo info = createTaskEquipmentInfo(modelInfo, presetMachine, task, scheduleDataHolder);
		info.setPreparation(configure(modelInfo.getPreparationModel(), presetMachine, preparationSecondaryResourcesList,
				task, scheduleDataHolder));
		info.setExecution(configure(modelInfo.getExecutionModel(), presetMachine, executionSecondaryResourcesList,
				task, scheduleDataHolder));
		return null;
	}

}
