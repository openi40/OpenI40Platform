package com.openi40.scheduler.engine.resallocator;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SchedulingPriorities;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.planning.equipment.EquipmentChoice;
import com.openi40.scheduler.model.planning.material.MaterialChoice;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IResourcesAllocator.class, entityClass = Task.class)
public class ResourcesAllocatorImpl extends BusinessLogic<Task> implements IResourcesAllocator {
	static Logger LOGGER = LoggerFactory.getLogger(ResourcesAllocatorImpl.class);

	@Override
	public ResourcesCombination elaborateAllocation(List<EquipmentChoice> equipmentPlans,
			List<MaterialChoice> materialPlans, Task task, TimeSegmentRequirement SetupTimeRange,
			TimeSegmentRequirement WorkTimeRange, ApsSchedulingSet action, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener) {
		IResourcesAllocationsPlanner resourcesPlanner = this.componentsFactory
				.create(IResourcesAllocationsPlanner.class, task, task.getContext());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin ResourcesAllocatorImpl.elaborateAllocation(...) on task " + task.getCode());
		}
		ResourcesCombination activeCombination = null;

		ApsLogicOptions schedulingOptions = action.getOptions();
		List<ResourcesCombination> equipmentAndMaterialValidCombinations = resourcesPlanner.elaborateAllocationPlans(
				equipmentPlans, materialPlans, task, SetupTimeRange, WorkTimeRange, action, direction,
				constraintSolutionListener);
		List<ResourcesCombination> unallocated = new ArrayList<ResourcesCombination>(
				equipmentAndMaterialValidCombinations);
		if (!equipmentAndMaterialValidCombinations.isEmpty()) {
			final ResourcesCombination goodCombination = chooseResources(equipmentAndMaterialValidCombinations,
					schedulingOptions, direction, action.getContext());
			unallocated.removeIf(X -> {
				return X == goodCombination;
			});
			activeCombination = goodCombination;
		}
		for (ResourcesCombination resourcesCombination : unallocated) {
			resourcesPlanner.discardResourcesOptions(resourcesCombination);
		}
		if (activeCombination != null) {

			resourcesPlanner.reserveResources(activeCombination);

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End ResourcesAllocatorImpl.elaborateAllocation(...) on task " + task.getCode());
		}
		return activeCombination;
	}

	protected final ResourcesCombination chooseResources(List<ResourcesCombination> equipmentAndMaterialList,
			ApsLogicOptions algorithmOptions, ApsLogicDirection direction, ApsData context) {
		List<ResourcesCombination> actualCombinations = equipmentAndMaterialList;
		for (SchedulingPriorities schedulingPriority : algorithmOptions.getSchedulingPrioritiesOptions()) {
			TreeMap<Double, List<ResourcesCombination>> sortedStructure = buildByOption(schedulingPriority, direction,
					actualCombinations, context);
			// Take the first in order set of values
			for (Map.Entry<Double, List<ResourcesCombination>> actualValue : sortedStructure.entrySet()) {
				actualCombinations = actualValue.getValue();
				break;
			}
		}
		return !actualCombinations.isEmpty() ? actualCombinations.get(0) : null;
	}

	private TreeMap<Double, List<ResourcesCombination>> buildByOption(
			ApsLogicOptions.SchedulingPriorities schedulingPriority, ApsLogicDirection direction,
			List<ResourcesCombination> equipmentAndMaterialList, ApsData context) {

		TreeMap<Double, List<ResourcesCombination>> outDictionary = new TreeMap<Double, List<ResourcesCombination>>();
		for (ResourcesCombination equipment : equipmentAndMaterialList) {
			double key = 0L;
			switch (schedulingPriority) {
			case MINIMIZE_SETUP: {
				key = equipment.getEquipmentAllocationOption().getSetup().getDurationMinutes();

			}
				break;
			case MINIMIZE_WORK_ELAPSED_TIME: {
				key = equipment.getEquipmentAllocationOption().getWork().getDurationMinutes();
			}
				break;
			case MINIMIZE_WORK_END_DATETIME: {
				switch (direction) {
				case FORWARD: {

					key = context.getSchedulingWindow().getStartDateTime().toInstant().until(
							equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant(),
							ChronoUnit.MINUTES);
				}
					break;
				case BACKWARD: {
					key = equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant()
							.until(context.getSchedulingWindow().getEndDateTime().toInstant(), ChronoUnit.MINUTES)
					/*
					 * context.getSchedulingWindow().getStartDateTime().toInstant().until(
					 * equipment.getEquipmentAllocationOption().getWork().getEndDateTime().toInstant
					 * (), ChronoUnit.MINUTES)
					 */;
				}
					break;
				}

			}
				break;
			}
			if (!outDictionary.containsKey(key)) {
				outDictionary.put(key, new ArrayList<ResourcesCombination>());
			}
			outDictionary.get(key).add(equipment);
		}
		return outDictionary;
	}

	@Override
	public ResourcesCombination elaborateUnderProductionAllocation(Machine usedMachine,
			List<EquipmentChoice> equipmentChoices, List<MaterialChoice> materialPlans, Task task,
			ApsSchedulingSet action, ApsLogicDirection direction, IRuleSolutionListener constraintSolutionListener) {

		IResourcesAllocationsPlanner resourcesPlanner = this.componentsFactory
				.create(IResourcesAllocationsPlanner.class, task, task.getContext());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"Begin ResourcesAllocatorImpl.elaborateUnderProductionAllocation(...) on task " + task.getCode());
		}
		ResourcesCombination activeCombination = null;

		ApsLogicOptions schedulingOptions = action.getOptions();
		List<ResourcesCombination> equipmentAndMaterialValidCombinations = resourcesPlanner
				.elaborateUnderProductionPlans(usedMachine, equipmentChoices, materialPlans, task, action, direction,
						constraintSolutionListener);
		List<ResourcesCombination> unallocated = new ArrayList<ResourcesCombination>(
				equipmentAndMaterialValidCombinations);
		if (!equipmentAndMaterialValidCombinations.isEmpty()) {
			final ResourcesCombination goodCombination = chooseResources(equipmentAndMaterialValidCombinations,
					schedulingOptions, direction, action.getContext());
			unallocated.removeIf(X -> {
				return X == goodCombination;
			});
			activeCombination = goodCombination;
		}
		for (ResourcesCombination resourcesCombination : unallocated) {
			resourcesPlanner.discardResourcesOptions(resourcesCombination);
		}
		if (activeCombination != null) {

			resourcesPlanner.reserveResources(activeCombination);

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"End ResourcesAllocatorImpl.elaborateUnderProductionAllocation(...) on task " + task.getCode());
		}
		return activeCombination;

	}

}
