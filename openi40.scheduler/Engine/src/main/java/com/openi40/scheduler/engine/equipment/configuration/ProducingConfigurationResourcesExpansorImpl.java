package com.openi40.scheduler.engine.equipment.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.EquipmentSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.ResourceUse;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample.ResourceUseGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionModel.SecondaryModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.UseModel;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.tasks.Task;

@DefaultImplementation(implemented = IProducingConfigurationResourcesExpansor.class, entityClass = ApsData.class)
public class ProducingConfigurationResourcesExpansorImpl extends BusinessLogic<ApsData>
		implements IProducingConfigurationResourcesExpansor {
	public interface GroupRelatedInfo {
		ResourceGroup secondariesGroup = null;
	}

	public class DiscoveredPhaseSecondaries implements GroupRelatedInfo {
		UsedSecondaryResourcesInfo assigned = null;
		SecondaryResourceMissingConfig missing = null;
		ResourceGroup secondariesGroup = null;
	}

	public class DiscoveredWorkNSetupSecondaries implements GroupRelatedInfo {
		DiscoveredPhaseSecondaries work = new DiscoveredPhaseSecondaries();
		DiscoveredPhaseSecondaries setup = new DiscoveredPhaseSecondaries();
		ResourceGroup secondariesGroup = null;
	}

	protected class SecondaryResourceMissingConfig implements GroupRelatedInfo {
		ResourceGroup secondariesGroup = null;
		int missingNr = 0;
		List<Resource> availablesNotUsed = new ArrayList<Resource>();
	}

	public ProducingConfigurationResourcesExpansorImpl() {

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

	/*
	 * protected SecondaryResourceMissingConfig
	 * findMissingMatching(List<SecondaryResourceMissingConfig> list, ResourceGroup
	 * group) { for (SecondaryResourceMissingConfig sr : list) { if
	 * (sr.secondariesGroup == group) return sr; } return null; }
	 */

	protected UsedSecondaryResourcesInfo findUsedMatching(List<UsedSecondaryResourcesInfo> list, ResourceGroup group) {
		for (UsedSecondaryResourcesInfo usedSecondaryResourcesInfo : list) {
			if (usedSecondaryResourcesInfo.getResourceGroup() != null
					&& usedSecondaryResourcesInfo.getResourceGroup().equals(group.getCode())) {
				return usedSecondaryResourcesInfo;
			}
		}
		return null;
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

	private <UseModelType extends UseModel<ResourceGroup, Resource>> List<SecondaryResourceMissingConfig> missingSecondaries(
			List<UseModelType> secondaryResources, List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList) {
		List<SecondaryResourceMissingConfig> missingSecondaries = new ArrayList<SecondaryResourceMissingConfig>();
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
					missingConfig.secondariesGroup = secondaryModel.getGroup();
					missingConfig.missingNr = secondaryModel.getQty() - matchingExisting.getUsedResourcesCodes().size();
					for (Resource resource : missingConfig.secondariesGroup.getResources()) {
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
			taskEquipmentInfo.setPreparation(ConfigurationDataComposer.configure(modelInfo.getPreparationModel(),
					presetMachine, preparationSecondaryResourcesList, apsLogicOptions, task, scheduleDataHolder));
			taskEquipmentInfo.setExecution(ConfigurationDataComposer.configure(modelInfo.getExecutionModel(),
					presetMachine, executionSecondaryResourcesList, apsLogicOptions, task, scheduleDataHolder));
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
		// List of secondary resources meta info already set in both setup/work phases
		List<DiscoveredWorkNSetupSecondaries> bothMatching = this.findBothWorkSetupSecondaries(modelInfo, presetMachine,
				preparationSecondaryResourcesList, executionSecondaryResourcesList, missingPreparationSecondary,
				missingExecutionSecondary);
		// List of secondary resources meta info alreadi set in setup phase only
		List<DiscoveredPhaseSecondaries> setupOnly = findPhaseSecondaries(
				modelInfo.getPreparationModel().getSecondaryResources(), preparationSecondaryResourcesList,
				missingPreparationSecondary, bothMatching);
		// List of secondary resources meta info alreadi set in work phase only
		List<DiscoveredPhaseSecondaries> workOnly = findPhaseSecondaries(
				modelInfo.getExecutionModel().getSecondaryResources(), executionSecondaryResourcesList,
				missingExecutionSecondary, bothMatching);
		// List of unset secondary resources in setup phase
		List<TaskPreparationUseModel<Resource, ResourceGroup>> setupUnsetSecondaries = unsetSecondaries(
				modelInfo.getPreparationModel().getSecondaryResources(), bothMatching, setupOnly, workOnly);
		// List of unset secondary resources in work phase
		List<SecondaryModelInfo> execUnsetSecondaries = unsetSecondaries(
				modelInfo.getExecutionModel().getSecondaryResources(), bothMatching, setupOnly, workOnly);
		return permutations;
	}

	/**
	 * Returns list of setup/work secondary resources meta configurations not in
	 * already set state by received production informations data
	 * 
	 * @param <UseType>
	 * @param secondaryResources
	 * @param bothMatching
	 * @param setupOnly
	 * @param workOnly
	 * @return
	 */
	private <UseType extends UseModel<ResourceGroup, Resource>> List<UseType> unsetSecondaries(
			List<UseType> secondaryResources, List<DiscoveredWorkNSetupSecondaries> bothMatching,
			List<DiscoveredPhaseSecondaries> setupOnly, List<DiscoveredPhaseSecondaries> workOnly) {
		List<UseType> outVector = new ArrayList<UseType>();
		for (UseType ut : secondaryResources) {
			if (findByResourceGroup(bothMatching, ut.getGroup()) == null
					&& findByResourceGroup(setupOnly, ut.getGroup()) == null
					&& findByResourceGroup(workOnly, ut.getGroup()) == null) {
				outVector.add(ut);
			}
		}
		return outVector;
	}

	/**
	 * Find item with matching group
	 * 
	 * @param <EType>
	 * @param list
	 * @param rGroup
	 * @return
	 */
	private <EType extends GroupRelatedInfo> EType findByResourceGroup(List<EType> list, ResourceGroup rGroup) {
		for (EType e : list) {
			if (e.secondariesGroup == rGroup) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Consider secondary resource assignations/missing only in one phase
	 * 
	 * @param <UseType>
	 * @param models
	 * @param usedSecondary
	 * @param missingSecondary
	 * @param bothMatching
	 * @return
	 */
	private <UseType extends UseModel<ResourceGroup, Resource>> List<DiscoveredPhaseSecondaries> findPhaseSecondaries(
			List<UseType> models, List<UsedSecondaryResourcesInfo> usedSecondary,
			List<SecondaryResourceMissingConfig> missingSecondary, List<DiscoveredWorkNSetupSecondaries> bothMatching) {
		List<DiscoveredPhaseSecondaries> list = new ArrayList<>();
		for (UseType model : models) {
			boolean hasMatches = false;
			for (DiscoveredWorkNSetupSecondaries discoveredWorkNSetupSecondaries : bothMatching) {
				hasMatches = discoveredWorkNSetupSecondaries.secondariesGroup == model.getGroup() || hasMatches;
			}
			if (!hasMatches) {
				UsedSecondaryResourcesInfo assigned = findUsedMatching(usedSecondary, model.getGroup());
				SecondaryResourceMissingConfig missing = findByResourceGroup(missingSecondary, model.getGroup());
				if (assigned != null && missing != null) {
					DiscoveredPhaseSecondaries discovered = new DiscoveredPhaseSecondaries();
					discovered.assigned = assigned;
					discovered.missing = missing;
					discovered.secondariesGroup = model.getGroup();
					list.add(discovered);
				}
			}
		}

		return list;

	}

	/**
	 * Consider first secondaries both in setup and work configurations that must be
	 * reused from setup to work phase If some secondary resource instance is
	 * present in setup and not present in working phase even if the meta-infos
	 * suggests that they have to be used, but different instances are chosed the
	 * configuration is forced to try using the same instances for both setup and
	 * work.
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

	private List<DiscoveredWorkNSetupSecondaries> findBothWorkSetupSecondaries(TaskEquipmentModelInfo modelInfo,
			Machine presetMachine, List<UsedSecondaryResourcesInfo> preparationSecondaryResourcesList,
			List<UsedSecondaryResourcesInfo> executionSecondaryResourcesList,
			List<SecondaryResourceMissingConfig> missingPreparationSecondary,
			List<SecondaryResourceMissingConfig> missingExecutionSecondary) {
		List<DiscoveredWorkNSetupSecondaries> bothList = new ArrayList<>();
		List<TaskPreparationUseModel<Resource, ResourceGroup>> setupSecondaryModels = modelInfo.getPreparationModel()
				.getSecondaryResources();
		List<SecondaryModelInfo> workSecondaryModels = modelInfo.getExecutionModel().getSecondaryResources();
		for (TaskPreparationUseModel<Resource, ResourceGroup> setupSecondaryModel : setupSecondaryModels) {
			SecondaryModelInfo workMatchingSecondaryModel = null;
			UsedSecondaryResourcesInfo setupMatchingAssigned = findUsedMatching(preparationSecondaryResourcesList,
					setupSecondaryModel.getGroup());
			UsedSecondaryResourcesInfo workMatchingAssigned = findUsedMatching(executionSecondaryResourcesList,
					setupSecondaryModel.getGroup());
			;
			SecondaryResourceMissingConfig setupMatchingMissing = findByResourceGroup(missingPreparationSecondary,
					setupSecondaryModel.getGroup());
			SecondaryResourceMissingConfig workMatchingMissing = findByResourceGroup(missingExecutionSecondary,
					setupSecondaryModel.getGroup());
			for (SecondaryModelInfo secondaryModelInfo : workSecondaryModels) {
				if (secondaryModelInfo.getGroup() == setupSecondaryModel.getGroup()) {
					workMatchingSecondaryModel = secondaryModelInfo;
					break;
				}
			}
			if (workMatchingSecondaryModel != null) {
				DiscoveredWorkNSetupSecondaries discovered = new DiscoveredWorkNSetupSecondaries();
				discovered.work.assigned = workMatchingAssigned;
				discovered.work.missing = workMatchingMissing;
				discovered.work.secondariesGroup = setupSecondaryModel.getGroup();
				discovered.setup.assigned = setupMatchingAssigned;
				discovered.setup.missing = setupMatchingMissing;
				discovered.setup.secondariesGroup = setupSecondaryModel.getGroup();
				discovered.secondariesGroup = setupSecondaryModel.getGroup();
				bothList.add(discovered);
			}
		}
		return bothList;
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
