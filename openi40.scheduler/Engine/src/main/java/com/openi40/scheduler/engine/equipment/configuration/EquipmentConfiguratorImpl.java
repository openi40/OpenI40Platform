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
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.EquipmentSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.ResourceUse;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.ResourceUseGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.equipment.TaskPreparationModel;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
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
	protected TaskPreparationPlanned configure(TaskPreparationModel toConfigure, ApsLogicOptions apsLogicOptions,
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

	protected <EquipmentType extends ITimesheetAllocableObject, EquipmentGroupType extends Group<EquipmentType>> int computeResourceQuantity(
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
	protected TaskExecutionPlanned configure(TaskExecutionModel toConfigure, ApsLogicOptions apsLogicOptions,
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
	protected TaskExecutionPlanned configure(TaskExecutionModel model, Machine presetMachine,
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
	protected TaskPreparationPlanned configure(TaskPreparationModel model, Machine presetMachine,
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

	protected List<TaskExecutionModel.ResourceModelInfo> permutate(TaskExecutionModel.ResourceModelInfo v, Task task,
			ApsSchedulingSet action) {
		IWorkResourceConfigurator workResourceConfigurator = componentsFactory.create(IWorkResourceConfigurator.class,
				v, action);
		return workResourceConfigurator.explodeConfigurations(v, task, action);
	}

	protected List<TaskExecutionModel.SecondaryModelInfo> permutate(TaskExecutionModel.SecondaryModelInfo v, Task task,
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

	protected List<List<TaskExecutionModel.ResourceModelInfo>> permutateResources(
			List<TaskExecutionModel.ResourceModelInfo> v, Task task, ApsSchedulingSet action) {
		List<List<TaskExecutionModel.ResourceModelInfo>> outPermutations = new ArrayList<List<TaskExecutionModel.ResourceModelInfo>>();
		List<List<TaskExecutionModel.ResourceModelInfo>> levelsPermutations = new ArrayList<List<TaskExecutionModel.ResourceModelInfo>>();
		for (TaskExecutionModel.ResourceModelInfo equipment : v) {
			List<TaskExecutionModel.ResourceModelInfo> thisLevelPermutation = permutate(equipment, task, action);
			levelsPermutations.add(thisLevelPermutation);
		}

		outPermutations = this.<Machine, MachinesGroup, TaskExecutionModel.ResourceModelInfo>rotatePermutations(
				levelsPermutations);
		return outPermutations;
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

	protected List<TaskEquipmentModelInfo> expandList(List<TaskEquipmentModelInfo> models, Task task,
			ApsSchedulingSet action) {
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

	public List<TaskEquipmentInfo> calculateConfigurations(TaskEquipmentModelOptions toConfigure,
			ApsLogicOptions apsLogicOptions, Task scheduledActivity, ApsData scheduleDataHolder) {
		List<TaskEquipmentInfo> outList = new ArrayList<TaskEquipmentInfo>();
		List<TaskEquipmentModelInfo> expanded = expandList(toConfigure.getEquipmentModels(), scheduledActivity,
				scheduledActivity.getParentSchedulingSet());
		for (TaskEquipmentModelInfo model : expanded) {
			TaskPreparationPlanned setup = configure(model.getPreparationModel(), apsLogicOptions, scheduledActivity,
					scheduleDataHolder);
			TaskExecutionPlanned working = configure(model.getExecutionModel(), apsLogicOptions, scheduledActivity,
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

	/***
	 * Returning list of secondary resource instances taken matching
	 * meta-informations that is registered as used in the task or already scheduled
	 * as used.
	 * 
	 * @param <UseType>
	 * @param secondaries
	 * @param equipmentSet
	 * @param usedResourcesInfo
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	protected <UseType extends UseModel<ResourceGroup, Resource>> List<UsedSecondaryResourcesInfo> getPresetSecondaryResourcesList(
			List<UseType> secondaries, EquipmentSet equipmentSet, List<UsedSecondaryResourcesInfo> usedResourcesInfo,
			Task task, ApsData scheduleDataHolder) {
		List<UsedSecondaryResourcesInfo> resulting = new ArrayList<UsedSecondaryResourcesInfo>();
		for (UseType metaInfo : secondaries) {
			UsedSecondaryResourcesInfo matchingInfo = null;
			ResourceUseGroup<Resource> stickyInfo = null;
			// Search matching on usedResourcesInfo
			for (UsedSecondaryResourcesInfo maxPriorityInfo : usedResourcesInfo) {
				if (maxPriorityInfo.getResourceGroup() != null
						&& maxPriorityInfo.getResourceGroup().equals(metaInfo.getGroup().getCode())) {
					matchingInfo = new UsedSecondaryResourcesInfo(matchingInfo);
					break;
				}
			}
			for (ResourceUseGroup<Resource> secondaryInfo : equipmentSet.getSecondaryResources()) {
				if (secondaryInfo.getGroup() == metaInfo.getGroup()) {
					stickyInfo = secondaryInfo;
					break;
				}
			}
			if (matchingInfo != null || stickyInfo != null) {
				UsedSecondaryResourcesInfo outInfo = null;
				if (matchingInfo != null) {
					outInfo = matchingInfo;
				} else {
					outInfo = new UsedSecondaryResourcesInfo();
					outInfo.setResourceGroup(stickyInfo.getGroup().getCode());
				}
				if (stickyInfo != null) {
					if (outInfo.getUsedResourcesCodes().size() < metaInfo.getQty()) {
						for (ResourceUse<Resource> use : stickyInfo.getResources()) {
							if (use.getResource() != null
									&& !outInfo.getUsedResourcesCodes().contains(use.getResource().getCode())
									&& outInfo.getUsedResourcesCodes().size() < metaInfo.getQty()) {
								outInfo.getUsedResourcesCodes().add(use.getResource().getCode());
							}
						}
					}
				}
				resulting.add(outInfo);
			}
		}
		return resulting;

	}

	protected class SecondaryResourceMissingConfig {
		Group<Resource> group = null;
		int missingNr = 0;
		List<Resource> availablesNotUsed = new ArrayList<Resource>();
	}

	/***
	 * Considering the presetMachine,the preparation and execution secondary
	 * resources indicated by parameters creates all possible combination of
	 * configuration that matches with those "pre-selections" and complements
	 * resources to match modelMetaInfo
	 * 
	 * @param modelInfo
	 * @param presetMachine
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @param preparationSecondaryResourcesList
	 * @param executionSecondaryResourcesList
	 * @return
	 */
	protected List<TaskEquipmentInfo> permutateMissingSecondaryResources(TaskEquipmentModelInfo modelInfo,
			Machine presetMachine, ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder,
			List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList,
			List<UsedSecondaryResourcesInfo> executionSecondaryResourcesList) {
		List<SecondaryResourceMissingConfig> missingPreparationSecondary = missingSecondaries(
				modelInfo.getPreparationModel().getSecondaryResources(), preparationSecondaryResourcesList);
		List<SecondaryResourceMissingConfig> missingExecutionSecondary = missingSecondaries(
				modelInfo.getExecutionModel().getSecondaryResources(), executionSecondaryResourcesList);
		// TODO: express configurations that are mixed expressions on what's specified
		// as preparationSecondaryResourcesList, executionSecondaryResourcesList and
		// presetMachine as "fixed" used resources
		// and combination of the remaining resources.
		List<TaskEquipmentInfo> configurationsList = new ArrayList<TaskEquipmentInfo>();
		if ((missingPreparationSecondary == null || missingPreparationSecondary.isEmpty())
				&& (missingExecutionSecondary == null || missingExecutionSecondary.isEmpty())) {
			TaskEquipmentInfo taskEquipmentInfo = new TaskEquipmentInfo(scheduleDataHolder);
			taskEquipmentInfo.setMetaInfo(modelInfo);
			taskEquipmentInfo.setPreparation(configure(modelInfo.getPreparationModel(), presetMachine,
					preparationSecondaryResourcesList, apsLogicOptions, task, scheduleDataHolder));
			taskEquipmentInfo.setExecution(configure(modelInfo.getExecutionModel(), presetMachine,
					executionSecondaryResourcesList, apsLogicOptions, task, scheduleDataHolder));
			configurationsList.add(taskEquipmentInfo);
		} else {
			configurationsList = permutateSecondaryResources(modelInfo, presetMachine,
					preparationSecondaryResourcesList, executionSecondaryResourcesList, missingPreparationSecondary,
					missingExecutionSecondary, apsLogicOptions, task, scheduleDataHolder);
		}

		return configurationsList;
	}

	/**
	 * Creates permutations with missing secondary resources filled with
	 * consideration that secondary resources instances used in setup have to be
	 * used also for work and the opposite according to equipment meta informations
	 * 
	 * @param modelInfo
	 * @param presetMachine
	 * @param preparationSecondaryResourcesList
	 * @param executionSecondaryResourcesList
	 * @param missingPreparationSecondary
	 * @param missingExecutionSecondary
	 * @param apsLogicOptions
	 * @param task
	 * @param scheduleDataHolder
	 * @return
	 */
	private List<TaskEquipmentInfo> permutateSecondaryResources(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList,
			List<UsedSecondaryResourcesInfo> executionSecondaryResourcesList,
			List<SecondaryResourceMissingConfig> missingPreparationSecondary,
			List<SecondaryResourceMissingConfig> missingExecutionSecondary, ApsLogicOptions apsLogicOptions, Task task,
			ApsData scheduleDataHolder) {
		List<TaskEquipmentInfo> permutations = new ArrayList<TaskEquipmentInfo>();
		// Consider first secondaries both in setup and work configurations
		// that must be reused from setup to work phase
		List<TaskPreparationUseModel<Resource,ResourceGroup>> setupSecondaryModels = modelInfo.getPreparationModel().getSecondaryResources();
		List<SecondaryModelInfo> workSecondaryModels =modelInfo.getExecutionModel().getSecondaryResources();
		for(TaskPreparationUseModel<Resource,ResourceGroup> setupSecondaryModel:setupSecondaryModels) {
			SecondaryModelInfo workMatchingSecondaryModel=null;
			for (SecondaryModelInfo secondaryModelInfo : workSecondaryModels) {
				if (secondaryModelInfo.getGroup()==setupSecondaryModel.getGroup()) {
					workMatchingSecondaryModel=secondaryModelInfo;
					break;
				}
			}
			if (workMatchingSecondaryModel!=null) {
				
			}
		}
		return permutations;
	}

	private <UseModelType extends UseModel<ResourceGroup, Resource>> List<SecondaryResourceMissingConfig> missingSecondaries(
			List<UseModelType> secondaryResources, List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList) {
		List<SecondaryResourceMissingConfig> missingSecondaries = new ArrayList<EquipmentConfiguratorImpl.SecondaryResourceMissingConfig>();
		for (UseModelType secondaryModel : secondaryResources) {
			UsedSecondaryResourcesInfo matchingExisting = null;
			for (UsedSecondaryResourcesInfo info : preparationSecondaryResourcesList) {
				if (info.getResourceGroup().equals(secondaryModel.getGroup().getCode())) {
					matchingExisting = info;
					break;
				}
			}
			if (matchingExisting != null) {
				if (secondaryModel.getQty() > matchingExisting.getUsedResourcesCodes().size()) {
					SecondaryResourceMissingConfig missingConfig = new SecondaryResourceMissingConfig();
					missingConfig.group = secondaryModel.getGroup();
					missingConfig.missingNr = secondaryModel.getQty() - matchingExisting.getUsedResourcesCodes().size();
					for (Resource resource : missingConfig.group.getResources()) {
						if (!resource.isDisabled()
								&& !matchingExisting.getUsedResourcesCodes().contains(resource.getCode())) {
							missingConfig.availablesNotUsed.add(resource);
						}
					}
					missingSecondaries.add(missingConfig);
				}
			}
		}
		return missingSecondaries;
	}

	@Override
	public List<TaskEquipmentInfo> calculateConfigurations(TaskEquipmentModelInfo modelInfo, Machine presetMachine,
			ApsLogicOptions apsLogicOptions, Task task, ApsData scheduleDataHolder,
			TaskEquipmentInfoSample taskEquipmentInfoSample, List<UsedSecondaryResourcesInfo> setupUsedResourcesInfo,
			List<UsedSecondaryResourcesInfo> workUsedResourcesInfo) {
		List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList = getPresetSecondaryResourcesList(
				modelInfo.getPreparationModel().getSecondaryResources(),
				taskEquipmentInfoSample != null ? taskEquipmentInfoSample.getPreparation() : null,
				setupUsedResourcesInfo, task, scheduleDataHolder);
		List<UsedSecondaryResourcesInfo> executionSecondaryResourcesList = getPresetSecondaryResourcesList(
				modelInfo.getExecutionModel().getSecondaryResources(),
				taskEquipmentInfoSample != null ? taskEquipmentInfoSample.getExecution() : null, workUsedResourcesInfo,
				task, scheduleDataHolder);
		List<TaskEquipmentInfo> outList = permutateMissingSecondaryResources(modelInfo, presetMachine, apsLogicOptions,
				task, scheduleDataHolder, preparationSecondaryResourcesList, executionSecondaryResourcesList);

		return outList;
	}

}