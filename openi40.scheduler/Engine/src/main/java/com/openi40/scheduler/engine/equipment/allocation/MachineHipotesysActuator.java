package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.changeover.IChangeOverLogic;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.equipment.allocation.SecondaryResourceAllocatorManager.SecondaryResourceAllocationResult;
import com.openi40.scheduler.engine.setuptime.ISetupTimeLogic;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult.TimeSegmentEvaluationResultType;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentChangeOverInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.EquipmentRule;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
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
public class MachineHipotesysActuator {
	public final static MachineHipotesysActuator Instance = new MachineHipotesysActuator();

	List<EquipmentAllocation> allocateMachine(TaskEquipmentInfo taskEquipmentInfo, ApsLogicDirection direction,
			EquipmentRule constraint, TimeSegmentRequirement _initialSetupTimeRange,
			TimeSegmentRequirement _initialWorkTimeRange, Machine resourceOption, ApsLogicOptions apsLogicOptions,
			IContextualBusinessLogicFactory componentsFactory, Task task, ApsData context) {
		List<EquipmentAllocation> results = new ArrayList<>();
		boolean correctlyAllocated = false;
		boolean stopIterations = false;
		
		int loopIteration = 0;
		ITimesheetLogic resourceOptionCalendarLogic = componentsFactory.create(ITimesheetLogic.class, resourceOption,
				context);
		ISetupTimeLogic setupTimeLogic = componentsFactory.create(ISetupTimeLogic.class, taskEquipmentInfo, context);
		IWorkTimeLogic workTimeLogic = componentsFactory.create(IWorkTimeLogic.class, taskEquipmentInfo, context);
		double changeOver = 0;
		double setupTime = setupTimeLogic.calculateSetupTime(taskEquipmentInfo, task);
		double nominalSetupTime = setupTime;
		double workTime = workTimeLogic.calculateWorkTime(task, taskEquipmentInfo);
		TaskEquipmentInfo thisTaskEquipmentInfo = null;
		try {
			thisTaskEquipmentInfo = (TaskEquipmentInfo) taskEquipmentInfo.cleanClone();
			thisTaskEquipmentInfo.getPreparation().getResource().setChoosenEquipment(resourceOption);
			thisTaskEquipmentInfo.getPreparation().setNominalSetupTime(nominalSetupTime);
			thisTaskEquipmentInfo.getExecution().getResource().setChoosenEquipment(resourceOption);
			thisTaskEquipmentInfo.getExecution().setNominalWorkTime(workTime);
		} catch (CloneNotSupportedException e) {
			throw new OpenI40Exception("Cloned equipment info error", e);
		}
		if (resourceOption.getCurrentEquipmentSetting() != null) {

			TaskEquipmentChangeOverInfo taskEquipmentChangeOver = new TaskEquipmentChangeOverInfo(context);
			taskEquipmentChangeOver.setAfterEquipment(thisTaskEquipmentInfo);
			taskEquipmentChangeOver.setBeforeEquipment(resourceOption.getCurrentEquipmentSetting());
			IChangeOverLogic changeOverLogic = componentsFactory.create(IChangeOverLogic.class, taskEquipmentChangeOver,
					context);
			if (changeOverLogic.isChangeOverManaged(task, taskEquipmentChangeOver)) {
				changeOver = changeOverLogic.calculateChangeOverTime(task, taskEquipmentChangeOver);
				if (changeOver > 0 && changeOver < setupTime) {
					setupTime = changeOver;
				}
			}
		}
		// Rationalize the main important work time requirement (that is subject for
		// both resources and material allocations and also subjected to related tasks
		// alignment)
		TimeSegmentRequirement WorkTimeRequirement = WorkTimeRequirementManager.Instance.rationalizeWorkTimeRequirement(
				_initialSetupTimeRange, _initialWorkTimeRange, direction, resourceOption, setupTime, workTime,
				resourceOptionCalendarLogic, task, context);

		// Collecting data on related tasks and related alignments
		List<TasksRelationRule> taskRelationRules = task.getRulesByClass(TasksRelationRule.class);
		if (!taskRelationRules.isEmpty()) {
			WorkTimeRequirement = RelatedTasksOffsetsManager.Instance.manageOffsets(WorkTimeRequirement, resourceOption,
					taskEquipmentInfo, setupTime, workTime, componentsFactory, taskRelationRules, task, direction,
					context);

		}
		do {

			TimesheetReservation workReservation = resourceOptionCalendarLogic.planReservation(resourceOption,
					WorkTimeRequirement, context);
			if (workReservation != null) {
				TimeSegmentRequirement SetupTimeRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
				SetupTimeRequirement.setEndAlignment(EndDateTimeAlignment.END_ON_END_PRECISELY);
				SetupTimeRequirement.setEndDateTime(workReservation.getStartDateTime());
				SetupTimeRequirement.setAvailabilityDuration(setupTime);
				TimesheetReservation setupReservation = resourceOptionCalendarLogic.planReservation(resourceOption,
						SetupTimeRequirement, context);
				if (setupReservation == null) {
					TimeSegmentEvaluationResult setupSegment = resourceOptionCalendarLogic
							.calculateTimeSegmentLimits(resourceOption, SetupTimeRequirement);
					if (setupSegment.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
						SetupTimeRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
						SetupTimeRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
						SetupTimeRequirement.setStartDateTime(setupSegment.getResult().getStartDateTime());
						SetupTimeRequirement.setAvailabilityDuration(setupTime);

					} else if (setupSegment
							.getResultType() == TimeSegmentEvaluationResultType.LOWER_BOUND_LIMIT_REACH) {
						SetupTimeRequirement = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
						SetupTimeRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
						SetupTimeRequirement.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
						SetupTimeRequirement.setAvailabilityDuration(setupTime);

					}
					setupReservation = resourceOptionCalendarLogic.planReservation(resourceOption, SetupTimeRequirement,
							context);
					if (setupReservation != null) {
						TimeSegmentRequirement WorkTimeReq = new TimeSegmentRequirement(WorkTimeRequirement);
						WorkTimeReq.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
						WorkTimeReq.setStartDateTime(setupReservation.getEndDateTime());
						WorkTimeReq.setAvailabilityDuration(workTime);
						workReservation = resourceOptionCalendarLogic.planReservation(resourceOption, WorkTimeReq,
								context);
					}
				}
				if (setupReservation != null && workReservation != null) {
					// Machine reservations allocated, requiring to combine those results with
					// secondary resources allocation
					SecondaryResourceAllocationResult secondaryResourcesAllocationResult = SecondaryResourceAllocatorManager.Instance
							.completeWithSecondaryResource(resourceOption, taskEquipmentInfo, setupTime, changeOver,
									nominalSetupTime, workTime, setupReservation, workReservation, WorkTimeRequirement,
									direction, componentsFactory, task, context, resourceOptionCalendarLogic);
					if (!secondaryResourcesAllocationResult.allocationSettings.isEmpty()) {
						correctlyAllocated = true;
						results.addAll(secondaryResourcesAllocationResult.allocationSettings);
					} else if (!secondaryResourcesAllocationResult.timeRelocations.isEmpty()) {
						List<AllocationTimeSegments> timeRelocations = secondaryResourcesAllocationResult.timeRelocations;
						TimeSegment _SetupTimeRange = timeRelocations.get(0).SetupRequirement;
						TimeSegment _WorkTimeRange = timeRelocations.get(0).WorkRequirement;
						stopIterations = _SetupTimeRange == null && _WorkTimeRange == null;
						switch (direction) {
						case FORWARD: {
							/*if (SetupTimeRange != null && _SetupTimeRange != null)
								SetupTimeRange.setStartDateTime(_SetupTimeRange.getStartDateTime());*/
							if (WorkTimeRequirement != null && _WorkTimeRange != null)
								WorkTimeRequirement.setStartDateTime(_WorkTimeRange.getStartDateTime());
						}
							break;
						case BACKWARD: {
							/*if (SetupTimeRange != null && _SetupTimeRange != null)
								SetupTimeRange.setEndDateTime(_SetupTimeRange.getEndDateTime());*/
							if (WorkTimeRequirement != null && _WorkTimeRange != null)
								WorkTimeRequirement.setEndDateTime(_WorkTimeRange.getEndDateTime());
						}
							break;
						}

					} else
						stopIterations = true;
				} else
					stopIterations = true;
			} else
				stopIterations = true;
			loopIteration++;
		} while (!correctlyAllocated && !stopIterations);
		return results;
	}
}
