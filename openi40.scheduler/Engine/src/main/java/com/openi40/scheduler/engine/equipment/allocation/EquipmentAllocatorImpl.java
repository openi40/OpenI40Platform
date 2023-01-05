package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.RealTimeSegmentRequirements;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;

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
@DefaultImplementation(implemented = IEquipmentAllocator.class, entityClass = EquipmentRule.class)
public class EquipmentAllocatorImpl extends BusinessLogic<EquipmentRule> implements IEquipmentAllocator {
	static Logger LOGGER = LoggerFactory.getLogger(EquipmentAllocatorImpl.class);

	public EquipmentAllocatorImpl() {

	}

	@Override
	public List<EquipmentAllocation> calculateAllocations(EquipmentRule constraint,
			TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange,
			ApsLogicOptions apsLogicOptions, Task task, ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin EquipmentAllocatorImpl.calculateAllocations(..) on task " + task.getCode());
			if (constraint.getEquipmentModelOptions() != null
					&& constraint.getEquipmentModelOptions().getEquipmentModels() != null
					&& constraint.getEquipmentModelOptions().getEquipmentModels().isEmpty()) {
				for (TaskEquipmentModelInfo em : constraint.getEquipmentModelOptions().getEquipmentModels()) {
					MachinesGroup wc = em.getExecutionModel() != null && em.getExecutionModel().getResource() != null
							? em.getExecutionModel().getResource().getGroup()
							: null;
					LOGGER.debug("Equipment model:" + em.getCode() + " for workcenter: "
							+ (wc != null ? wc.getCode() : "Null!"));
				}
			}
		}
		IApsLogic apsLogic = componentsFactory.create(IApsLogic.class, task.getParentSchedulingSet().getAlgorithmType(),
				task.getParentSchedulingSet(), task.getContext());
		ApsLogicDirection direction = apsLogic.getDirection();
		List<EquipmentAllocation> results = new ArrayList<>();
		if (constraint.getEquipmentModelOptions() != null
				&& constraint.getEquipmentModelOptions().getEquipmentModels() != null) {
			List<TaskEquipmentInfo> equipmentInfos = constraint.getTaskEquipmentInfos();
			results = calculateAllocations(constraint, equipmentInfos, SetupTimeRange, WorkTimeRange, apsLogicOptions,
					task, context);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End EquipmentAllocatorImpl.calculateAllocations(..) returning  " + results.size()
					+ " possible allocations");
		}
		return results;
	}
	@Override
	public List<EquipmentAllocation> calculateAllocations(EquipmentRule constraint,
			List<TaskEquipmentInfo> equipmentInfos, TimeSegmentRequirement SetupTimeRange,
			TimeSegmentRequirement WorkTimeRange, ApsLogicOptions apsLogicOptions, Task task, ApsData context) {
		IApsLogic apsLogic = componentsFactory.create(IApsLogic.class, task.getParentSchedulingSet().getAlgorithmType(),
				task.getParentSchedulingSet(), task.getContext());
		ApsLogicDirection direction = apsLogic.getDirection();
		List<EquipmentAllocation> results = new ArrayList<>();
		for (TaskEquipmentInfo taskEquipmentInfo : equipmentInfos) {

			if (taskEquipmentInfo.getExecution().getResource().getMetaInfo().getGroup() != taskEquipmentInfo
					.getPreparation().getResource().getMetaInfo().getGroup()) {
				String _msg = "Error in data structure, preparation and execution machine groups are not equal:execution: "
						+ taskEquipmentInfo.getExecution().getResource().getMetaInfo().getGroup().getCode()
						+ " preparation: "
						+ taskEquipmentInfo.getPreparation().getResource().getMetaInfo().getGroup().getCode();
				LOGGER.error(_msg);
				throw new OpenI40Exception(_msg);
			}
			List<Machine> machines = EquipmentUtil.getInstance().orderByLowerUsing(
					taskEquipmentInfo.getPreparation().getResource().getMetaInfo().getGroup().getResources(),
					componentsFactory, context);
			// if forced machine code is set than reduce the list of machines to try only to
			// that one
			String forcedMachine = task.getAcquiredMachineCode() != null
					&& task.getAcquiredMachineCode().trim().length() > 0 ? task.getAcquiredMachineCode()
							: task.getForcedMachineCode();
			if (forcedMachine != null && forcedMachine.trim().length() > 0) {
				machines.removeIf((machine) -> {
					return !machine.getCode().equals(forcedMachine);
				});
			}
			for (Machine resourceOption : machines) {
				if (resourceOption.isDisabled()) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Skipping machine: " + resourceOption.getCode() + " because disabled=true");
					}
					continue;
				}
				List<EquipmentAllocation> _results = MachineHipotesysActuator.Instance.allocateMachine(
						taskEquipmentInfo, direction, constraint, SetupTimeRange, WorkTimeRange, resourceOption,
						apsLogicOptions, componentsFactory, task, context);
				results.addAll(_results);
			}

		}
		return results;
	}

	@Override
	public List<EquipmentAllocation> calculateRealtimeProductionAllocations(Machine usedMachine,EquipmentRule constraint,
			TaskEquipmentInfo taskEquipmentInfo, RealTimeSegmentRequirements realtimeTaskRequirements,
			ApsSchedulingSet schedulingSet, Task task, ApsLogicDirection direction,
			IRuleSolutionListener constraintSolutionListener, ApsData context) {
		List<EquipmentAllocation> _results =new ArrayList<>();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin calculateRealtimeProductionAllocations(..) [machine="+usedMachine.getCode()+",task="+task.getCode()+"]");
		}
		_results = MachineHipotesysActuator.Instance.allocateUnderProductionMachine(
				taskEquipmentInfo, direction, constraint, realtimeTaskRequirements, usedMachine,
				schedulingSet.getOptions(), componentsFactory, task, context);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End calculateRealtimeProductionAllocations(..) [machine="+usedMachine.getCode()+",task="+task.getCode()+"]");
		}
		return _results;
	}

}
