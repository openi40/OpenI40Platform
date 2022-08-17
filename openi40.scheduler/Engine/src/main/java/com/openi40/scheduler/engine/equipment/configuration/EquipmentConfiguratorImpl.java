package com.openi40.scheduler.engine.equipment.configuration;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.asm.AsmManager.ModelInfo;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.equipment.TaskPreparationModel;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.equipment.UseModel;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.tasks.Task;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IEquipmentConfigurator.class, entityClass = TaskEquipmentModelOptions.class)
public class EquipmentConfiguratorImpl extends BusinessLogic<TaskEquipmentModelOptions>
		implements IEquipmentConfigurator {
	protected TaskPreparationPlanned Configure(TaskPreparationModel toConfigure, ApsLogicOptions apsLogicOptions,
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

	private <EquipmentType extends ITimesheetAllocableObject, EquipmentGroupType extends Group<EquipmentType>> int computeResourceQuantity(
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

	protected final TaskExecutionPlanned Configure(TaskExecutionModel toConfigure, ApsLogicOptions apsLogicOptions,
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

	private List<TaskExecutionModel.ResourceModelInfo> Permutate(TaskExecutionModel.ResourceModelInfo v, Task task,
			ApsSchedulingSet action) {
		IWorkResourceConfigurator workResourceConfigurator = componentsFactory.create(IWorkResourceConfigurator.class,
				v, action);
		return workResourceConfigurator.explodeConfigurations(v, task, action);
	}

	private List<TaskExecutionModel.SecondaryModelInfo> Permutate(TaskExecutionModel.SecondaryModelInfo v, Task task,
			ApsSchedulingSet action) {

		IWorkSecondaryResourceConfigurator workResourceConfigurator = componentsFactory
				.create(IWorkSecondaryResourceConfigurator.class, v, action);
		return workResourceConfigurator.explodeConfigurations(v, task, action);
	}

	private <RC extends ITimesheetAllocableObject, RCGroup extends Group<RC>, WEquipUM extends TaskExecutionUseModel<RC, RCGroup>> List<List<WEquipUM>> RotatePermutations(
			List<List<WEquipUM>> inVals) {

		List<List<WEquipUM>> outRows = new ArrayList<List<WEquipUM>>();
		if (!inVals.isEmpty()) {
			List<WEquipUM> firstRow = inVals.get(0);
			List<List<WEquipUM>> remainingRows = new ArrayList<List<WEquipUM>>();
			for (int i = 1; i < inVals.size(); i++) {
				remainingRows.add(inVals.get(i));
			}

			for (WEquipUM firstRowValue : firstRow) {
				if (!remainingRows.isEmpty()) {
					List<List<WEquipUM>> allRemainingPermutations = this
							.<RC, RCGroup, WEquipUM>RotatePermutations(remainingRows);
					for (List<WEquipUM> remainingPermutation : allRemainingPermutations) {
						List<WEquipUM> thisLevelVector = new ArrayList<WEquipUM>();
						thisLevelVector.add(firstRowValue);
						for (WEquipUM element : remainingPermutation) {
							thisLevelVector.add(element);
						}
						outRows.add(thisLevelVector);
					}
				} else {
					List<WEquipUM> thisLevelVector = new ArrayList<WEquipUM>();
					thisLevelVector.add(firstRowValue);
					outRows.add(thisLevelVector);
				}

			}
		}
		return outRows;
	}

	private List<List<TaskExecutionModel.ResourceModelInfo>> PermutateResources(
			List<TaskExecutionModel.ResourceModelInfo> v, Task task, ApsSchedulingSet action) {
		List<List<TaskExecutionModel.ResourceModelInfo>> outPermutations = new ArrayList<List<TaskExecutionModel.ResourceModelInfo>>();
		List<List<TaskExecutionModel.ResourceModelInfo>> levelsPermutations = new ArrayList<List<TaskExecutionModel.ResourceModelInfo>>();
		for (TaskExecutionModel.ResourceModelInfo equipment : v) {
			List<TaskExecutionModel.ResourceModelInfo> thisLevelPermutation = Permutate(equipment, task, action);
			levelsPermutations.add(thisLevelPermutation);
		}

		outPermutations = this.<Machine, MachinesGroup, TaskExecutionModel.ResourceModelInfo>RotatePermutations(
				levelsPermutations);
		return outPermutations;
	}

	private List<List<TaskExecutionModel.SecondaryModelInfo>> PermutateSecondaryResources(
			List<TaskExecutionModel.SecondaryModelInfo> v, Task task, ApsSchedulingSet action) {
		List<List<TaskExecutionModel.SecondaryModelInfo>> outPermutations = new ArrayList<List<TaskExecutionModel.SecondaryModelInfo>>();
		List<List<TaskExecutionModel.SecondaryModelInfo>> levelsPermutations = new ArrayList<List<TaskExecutionModel.SecondaryModelInfo>>();
		for (TaskExecutionModel.SecondaryModelInfo equipment : v) {
			List<TaskExecutionModel.SecondaryModelInfo> thisLevelPermutation = Permutate(equipment, task, action);
			levelsPermutations.add(thisLevelPermutation);
		}

		outPermutations = this.<Resource, ResourceGroup, TaskExecutionModel.SecondaryModelInfo>RotatePermutations(
				levelsPermutations);
		return outPermutations;
	}

	private List<TaskEquipmentModelInfo> ExpandList(List<TaskEquipmentModelInfo> models, Task task,
			ApsSchedulingSet action) {
		List<TaskEquipmentModelInfo> outList = new ArrayList<TaskEquipmentModelInfo>();
		for (TaskEquipmentModelInfo model : models) {
			List<TaskExecutionModel.ResourceModelInfo> resourcesPermutations = Permutate(
					model.getExecutionModel().getResource(), task, action);
			List<List<TaskExecutionModel.SecondaryModelInfo>> secondaryListPermutations = PermutateSecondaryResources(
					model.getExecutionModel().getSecondaryResources(), task, action);

			// delete constraints
			for (TaskExecutionModel.ResourceModelInfo resource : resourcesPermutations) {
				if (!secondaryListPermutations.isEmpty()) {
					for (List<TaskExecutionModel.SecondaryModelInfo> secondary : secondaryListPermutations) {
						TaskEquipmentModelInfo modelInfo = new TaskEquipmentModelInfo(action.getContext());
						modelInfo.setCode(model.getCode());
						modelInfo.setSetupGroupCode(model.getSetupGroupCode());
						modelInfo.setPreparationModel(model.getPreparationModel());
						outList.add(modelInfo);
						modelInfo.getExecutionModel().setResource(resource);
						modelInfo.getExecutionModel().setSecondaryResources(secondary);
						modelInfo.setTaskMetaInfo(model.getTaskMetaInfo());

					}
				} else {
					TaskEquipmentModelInfo modelInfo = new TaskEquipmentModelInfo(action.getContext());
					modelInfo.setCode(model.getCode());
					modelInfo.setPreparationModel(model.getPreparationModel());
					modelInfo.getExecutionModel().setResource(resource);
					modelInfo.setTaskMetaInfo(model.getTaskMetaInfo());
					modelInfo.setSetupGroupCode(model.getSetupGroupCode());
					outList.add(modelInfo);
				}
			}
		}
		
		return outList;
	}

	public List<TaskEquipmentInfo> calculateConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task scheduledActivity, ApsData scheduleDataHolder) {
		List<TaskEquipmentInfo> outList = new ArrayList<TaskEquipmentInfo>();
		List<TaskEquipmentModelInfo> expanded = ExpandList(toConfigure.getEquipmentModels(), scheduledActivity,
				scheduledActivity.getParentSchedulingSet());
		for (TaskEquipmentModelInfo model : expanded) {
			TaskPreparationPlanned setup = Configure(model.getPreparationModel(), apsLogicOptions, scheduledActivity,
					scheduleDataHolder);
			TaskExecutionPlanned working = Configure(model.getExecutionModel(), apsLogicOptions, scheduledActivity,
					scheduleDataHolder);
			TaskEquipmentInfo tinfo = new TaskEquipmentInfo(scheduleDataHolder);
			tinfo.setSetupGroupCode(model.getSetupGroupCode());
			tinfo.setMetaInfo(model);
			tinfo.setPreparation(setup);
			tinfo.setExecution(working);
			tinfo.setTaskInfo(new TaskProcessInfo(scheduleDataHolder, model.getTaskMetaInfo()));
			tinfo.setSetupGroupCode(model.getSetupGroupCode());
			outList.add(tinfo);
		}

		return outList;
	}

	@Override
	public List<TaskEquipmentInfo> calculateConfigurations(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder,
			TaskEquipmentInfoSample taskEquipmentInfoSample, List<UsedSecondaryResourcesInfo> setupUsedResourcesInfo,
			List<UsedSecondaryResourcesInfo> workUsedResourcesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}