package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
class SecondaryResourceAllocatorManager {
	static final SecondaryResourceAllocatorManager Instance = new SecondaryResourceAllocatorManager();

	static class SecondaryResourceAllocationResult {
		List<EquipmentAllocation> allocationSettings = new ArrayList<>();
		List<AllocationTimeSegments> timeRelocations = new ArrayList<>();
	}

	private SecondaryResourceAllocatorManager() {

	}

	/****************************************************************************************************************************************************
	 * Tries to allocate secondary resources according machine setup/work
	 * reservation or finds alternative time positioning specifications to allocate
	 * first the secondary equipment and outputs time positioning specification
	 * according to first secondaries and then primary
	 * 
	 * @param resourceOption
	 * @param equipmentInfo
	 * @param setupTime
	 * @param changeOver
	 * @param nominalSetupTime
	 * @param workTime
	 * @param setupReservation
	 * @param workReservation
	 * @param workTimeRequirement
	 * @param direction
	 * @param componentsFactory
	 * @param task
	 * @param context
	 * @param machineTimesheetAllocationLogic 
	 * @return
	 */
	SecondaryResourceAllocationResult completeWithSecondaryResource(Machine resourceOption,
			TaskEquipmentInfo equipmentInfo, double setupTime, double changeOver, double nominalSetupTime,
			double workTime, TimesheetReservation setupReservation, TimesheetReservation workReservation,
			TimeSegmentRequirement workTimeRequirement, ApsLogicDirection direction,
			IContextualBusinessLogicFactory componentsFactory, Task task, ApsData context, ITimesheetLogic machineTimesheetAllocationLogic) {
		SecondaryResourceAllocationResult outValue = new SecondaryResourceAllocationResult();

		try {
			TaskEquipmentInfo clonedInfo = (TaskEquipmentInfo) equipmentInfo.cleanClone();
			clonedInfo.getPreparation().setNominalChangeOverTime(changeOver);
			clonedInfo.getPreparation().setNominalSetupTime(nominalSetupTime);
			clonedInfo.getPreparation().getResource().setChoosenEquipment(resourceOption);
			clonedInfo.getPreparation().getResource().getTimesheetReservations().add(setupReservation);
			clonedInfo.getPreparation().getEquipmentEventsGroup().Add(setupReservation);
			
			clonedInfo.getExecution().setNominalWorkTime(workTime);
			clonedInfo.getExecution().getResource().setChoosenEquipment(resourceOption);
			clonedInfo.getExecution().getResource().getTimesheetReservations().add(setupReservation);
			clonedInfo.getExecution().getEquipmentEventsGroup().Add(workReservation);
			List<MatchingSecondaryResourcePhases> secondaryWS = SecondaryResourceRationalizer.Instance
					.pairMatchingGroups(clonedInfo);
			boolean allSecondaryAllocationsMet = true;
			List<MatchingSecondaryResourcePhasesAllocations> validAllocations = new ArrayList<>();
			for (MatchingSecondaryResourcePhases matchingSecondaryResourcePhases : secondaryWS) {
				MatchingSecondaryResourcePhasesAllocations allocations = SecondaryResourceAllocatorExecutor.Instance
						.planReservations(direction, setupTime,  changeOver,  nominalSetupTime,
								 workTime, resourceOption, machineTimesheetAllocationLogic, componentsFactory, setupReservation, workReservation,
								matchingSecondaryResourcePhases, task, context);
				allSecondaryAllocationsMet = allSecondaryAllocationsMet && allocations.resourceRequirementMet;
				if (allocations.resourceRequirementMet) {
					validAllocations.add(allocations);
				} else
					outValue.timeRelocations.addAll(allocations.timeRelocations);
			}
			if (allSecondaryAllocationsMet) {

				List<IOperation> reservations = new ArrayList<>();
				reservations.add(setupReservation);
				reservations.add(workReservation);
				for (MatchingSecondaryResourcePhasesAllocations allocations : validAllocations) {
					reservations.addAll(allocations.executionReservations);
					reservations.addAll(allocations.preparationReservations);
					List<TimesheetReservation> prepRes = allocations.preparationReservations;
					for (TimesheetReservation res : prepRes) {
						clonedInfo.getPreparation().getEquipmentEventsGroup().Add(res);
						

					}
					List<TimesheetReservation> execRes = allocations.executionReservations;
					for (TimesheetReservation res : execRes) {
						clonedInfo.getExecution().getEquipmentEventsGroup().Add(res);
					}

				}
				EquipmentAllocation allocationSetting = new EquipmentAllocation(reservations, clonedInfo);
				outValue.allocationSettings.add(allocationSetting);
				
			}
		} catch (CloneNotSupportedException e) {
			throw new OpenI40Exception("Cloning problem", e);
		}
		return outValue;
	}

}
