package com.openi40.scheduler.engine.equipment.configuration;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.tasks.Task;
@DefaultImplementation(implemented = IEquipmentConfigurationResourcesExpansor.class, entityClass = ApsData.class)
public class EquipmentConfigurationResourcesExpansorImpl extends BusinessLogic<ApsData> implements IEquipmentConfigurationResourcesExpansor {

	public List<TaskEquipmentModelInfo> expandList(List<TaskEquipmentModelInfo> models, Task task, ApsSchedulingSet action) {
		List<TaskEquipmentModelInfo> outList = new ArrayList<TaskEquipmentModelInfo>();
		for (TaskEquipmentModelInfo model : models) {
	
			List<TaskExecutionModel.ResourceModelInfo> resourcesPermutations = permutate(
					model.getExecutionModel().getResource(), task, action);
			List<List<TaskExecutionModel.SecondaryModelInfo>> secondaryListPermutations = permutateSecondaryResources(
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
	protected List<List<TaskExecutionModel.SecondaryModelInfo>> permutateSecondaryResources(
			List<TaskExecutionModel.SecondaryModelInfo> v, Task task, ApsSchedulingSet action) {
		List<List<TaskExecutionModel.SecondaryModelInfo>> outPermutations = new ArrayList<List<TaskExecutionModel.SecondaryModelInfo>>();
		List<List<TaskExecutionModel.SecondaryModelInfo>> levelsPermutations = new ArrayList<List<TaskExecutionModel.SecondaryModelInfo>>();
		for (TaskExecutionModel.SecondaryModelInfo equipment : v) {
			List<TaskExecutionModel.SecondaryModelInfo> thisLevelPermutation = permutate(equipment, task, action);
			levelsPermutations.add(thisLevelPermutation);
		}

		outPermutations = this.<Resource, ResourceGroup, TaskExecutionModel.SecondaryModelInfo>rotatePermutations(
				levelsPermutations);
		return outPermutations;
	}
	List<TaskExecutionModel.ResourceModelInfo> permutate(TaskExecutionModel.ResourceModelInfo v, Task task,
			ApsSchedulingSet action) {
		IWorkResourceConfigurator workResourceConfigurator = componentsFactory.create(IWorkResourceConfigurator.class,
				v, action);
		return workResourceConfigurator.explodeConfigurations(v, task, action);
	}

	List<TaskExecutionModel.SecondaryModelInfo> permutate(TaskExecutionModel.SecondaryModelInfo v, Task task,
			ApsSchedulingSet action) {
	
		IWorkSecondaryResourceConfigurator workResourceConfigurator = componentsFactory
				.create(IWorkSecondaryResourceConfigurator.class, v, action);
		return workResourceConfigurator.explodeConfigurations(v, task, action);
	}
	protected <RC extends ITimesheetAllocableObject, RCGroup extends Group<RC>, WEquipUM extends TaskExecutionUseModel<RC, RCGroup>> List<List<WEquipUM>> rotatePermutations(
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
							.<RC, RCGroup, WEquipUM>rotatePermutations(remainingRows);
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
	

	

}
