package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult.TimeSegmentEvaluationResultType;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.Use;
import com.openi40.scheduler.model.equipment.UseModel;
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
class SecondaryResourceAllocatorExecutor {
	static final SecondaryResourceAllocatorExecutor Instance = new SecondaryResourceAllocatorExecutor();

	private SecondaryResourceAllocatorExecutor() {

	}

	private <EquipmentType extends ITimesheetAllocableObject, EquipmentModelType extends UseModel<EquipmentGroupType, EquipmentType>, EquipmentGroupType extends Group<EquipmentType>> int computeResourceQuantity(
			Use<EquipmentType, EquipmentModelType, EquipmentGroupType> resource, Task scheduledActivity) {
		int qty = 0;
		if (resource != null && resource.getResourceRequiredCalculationType() != null) {
			switch (resource.getResourceRequiredCalculationType()) {
			case CONSTANT: {
				qty = resource.getUsedQty();
			}
				break;
			case PROPORTIONAL: {
				int lotsQty = (int) Math.floor(scheduledActivity.getQtyTotal() / resource.getResourceCalculatedLot())
						* resource.getUsedQty();
				qty = lotsQty;
			}
				break;
			}
		}
		return qty;
	}

	private <EquipmentType extends ITimesheetAllocableObject, EquipmentModelType extends UseModel<EquipmentGroupType, EquipmentType>, EquipmentGroupType extends Group<EquipmentType>> TimeSegmentRequirement evaluateTimeRequirement(
			TimeSegment timeSegment, Use<EquipmentType, EquipmentModelType, EquipmentGroupType> resourceUse) {
		// TODO: ALLOCATE COHERENTLY WITH isFullTime() isFromBegin()... but putting in
		// useModel the using time
		TimeSegmentRequirement requirement = new TimeSegmentRequirement(timeSegment.getType(), timeSegment);
		return requirement;

	}

	MatchingSecondaryResourcePhasesAllocations planReservations(ApsLogicDirection direction, double setupTime,
			double changeOver, double nominalSetupTime, double workTime, Machine mainMachine,
			ITimesheetLogic mainResourceCalendarLogic, IContextualBusinessLogicFactory componentsFactory,
			TimesheetReservation setupReservation, TimesheetReservation workReservation,
			MatchingSecondaryResourcePhases matchingSecondaryResourcePhases, Task task, ApsData context) {
		// Organize secondary resource from those having lower already reserved time to
		// higher reserved time
		// to avoid overload some secondary resource instead of others

		ResourceGroup group = null;
		if (matchingSecondaryResourcePhases.execution != null) {
			group = matchingSecondaryResourcePhases.execution.getMetaInfo().getGroup();
		} else if (matchingSecondaryResourcePhases.preparation != null) {
			group = matchingSecondaryResourcePhases.preparation.getMetaInfo().getGroup();
		}
		MatchingSecondaryResourcePhasesAllocations allocations = new MatchingSecondaryResourcePhasesAllocations(
				matchingSecondaryResourcePhases);
		if (group != null) {
			List<Resource> resources = EquipmentUtil.getInstance().orderByLowerUsing(group.getResources(),
					componentsFactory, context);
			int nPreparationRequired = computeResourceQuantity(matchingSecondaryResourcePhases.preparation, task);
			int nExecutionRequired = computeResourceQuantity(matchingSecondaryResourcePhases.execution, task);
			TimeSegmentRequirement setupRequirement = evaluateTimeRequirement(setupReservation,
					matchingSecondaryResourcePhases.preparation);
			TimeSegmentRequirement workRequirement = evaluateTimeRequirement(workReservation,
					matchingSecondaryResourcePhases.execution);

			for (Resource resource : resources) {

				ITimesheetLogic calendarCalculator = componentsFactory.create(ITimesheetLogic.class, resource, context);
				if (allocations.preparationReservations.size() < nPreparationRequired) {
					TimesheetReservation reservation = calendarCalculator.planReservation(resource, setupRequirement,
							task);
					if (reservation != null) {
						allocations.preparationReservations.add(reservation);
						allocations.preparation.getChoosenEquipmentList().add(resource);
						allocations.preparation.getTimesheetReservations().add(reservation);

					}

				}
				if (allocations.executionReservations.size() < nExecutionRequired) {
					TimesheetReservation reservation = calendarCalculator.planReservation(resource, workRequirement,
							task);
					if (reservation != null) {
						allocations.executionReservations.add(reservation);
						allocations.execution.getChoosenEquipmentList().add(resource);
						allocations.execution.getTimesheetReservations().add(reservation);
					}

				}

			}
			allocations.resourceRequirementMet = nPreparationRequired == allocations.preparationReservations.size()
					&& nExecutionRequired == allocations.executionReservations.size();
			if (!allocations.resourceRequirementMet) {
				// TODO: Metterci la rilocazione in avanti/indietro del tentativo di allocazione
				boolean preparationAllocationOk = false, executionAllocationOk = false;
				TimeSegmentRequirement PreparationRequirement = new TimeSegmentRequirement(setupRequirement);
				PreparationRequirement.setAvailabilityDuration(setupTime);
				TimeSegmentRequirement ExecutionRequirement = new TimeSegmentRequirement(workRequirement);
				ExecutionRequirement.setAvailabilityDuration(workTime);
				preparationAllocationOk = nPreparationRequired == 0;
				executionAllocationOk = nExecutionRequired == 0;
				TimeSegment findPreparationSegment = null;
				TimeSegment findExecutionSegment = null;
				switch (direction) {
				case FORWARD: {
					PreparationRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
					PreparationRequirement.setEndAlignment(null);
					PreparationRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
					ExecutionRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
					ExecutionRequirement.setEndAlignment(null);
					ExecutionRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());

					findPreparationSegment = findAllocationTimeSegment(direction, nPreparationRequired,
							PreparationRequirement, resources, mainMachine, mainResourceCalendarLogic,
							componentsFactory, task, context);
					preparationAllocationOk = findPreparationSegment != null;

					if (preparationAllocationOk) {
						ExecutionRequirement.setStartDateTime(findPreparationSegment.getEndDateTime());
						findExecutionSegment = findAllocationTimeSegment(direction, nExecutionRequired,
								ExecutionRequirement, resources, mainMachine, mainResourceCalendarLogic,
								componentsFactory, task, context);
						executionAllocationOk = findExecutionSegment != null;
						if (executionAllocationOk) {
							// recalculate setup segment relative to actual work segment
							findPreparationSegment = getRelatedSetupTimeSegment(mainMachine, mainResourceCalendarLogic,
									findExecutionSegment.getStartDateTime(), setupTime, context);
						}

					}
				}
					break;
				case BACKWARD: {
					ExecutionRequirement.setStartAlignment(null);
					ExecutionRequirement.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
					ExecutionRequirement.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
					PreparationRequirement.setStartAlignment(null);
					PreparationRequirement.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
					PreparationRequirement.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);

					findExecutionSegment = findAllocationTimeSegment(direction, nExecutionRequired,
							ExecutionRequirement, resources, mainMachine, mainResourceCalendarLogic, componentsFactory,
							task, context);
					executionAllocationOk = findExecutionSegment != null;

					if (executionAllocationOk) {
						PreparationRequirement.setEndDateTime(findExecutionSegment.getStartDateTime());
						findPreparationSegment = findAllocationTimeSegment(direction, nPreparationRequired,
								PreparationRequirement, resources, mainMachine, mainResourceCalendarLogic,
								componentsFactory, task, context);
						preparationAllocationOk = findPreparationSegment != null;
						if (preparationAllocationOk) {
							findExecutionSegment = getRelatedWorkTimeSegment(mainMachine, mainResourceCalendarLogic,
									findPreparationSegment.getEndDateTime(), workTime, context);
						}
					}

				}
					break;
				}
				if (preparationAllocationOk && executionAllocationOk) {
					AllocationTimeSegments timeRelocation = new AllocationTimeSegments();
					timeRelocation.SetupRequirement = findPreparationSegment != null
							? new TimeSegmentRequirement(findPreparationSegment)
							: null;
					timeRelocation.WorkRequirement = findExecutionSegment != null
							? new TimeSegmentRequirement(findExecutionSegment)
							: null;
					allocations.timeRelocations.add(timeRelocation);
				}
			}

		}
		return allocations;
	}

	private TimeSegment findAllocationTimeSegment(ApsLogicDirection direction, int howMany,
			TimeSegmentRequirement requirement, List<Resource> resources, Machine mainMachine,
			ITimesheetLogic mainResourceCalendarLogic, IContextualBusinessLogicFactory componentsFactory, Task task,
			ApsData context) {
		TreeMap<Date, List<TimesheetReservation>> reservations = new TreeMap<>();
		boolean allocationOk = false;
		boolean voidResults = false;
		TimeSegment timeSegment = null;
		TimeSegmentRequirement tmpRequirement = new TimeSegmentRequirement(requirement);
		if (howMany == 0) {
			// if no required secondary resource than evaluate time in main resource
			// timesheet
			TimeSegmentEvaluationResult limits = mainResourceCalendarLogic.calculateTimeSegmentLimits(mainMachine,
					tmpRequirement);
			return limits.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED ? limits.getResult()
					: null;
		}
		do {
			for (Resource resource : resources) {
				ITimesheetLogic logic = componentsFactory.create(ITimesheetLogic.class, resource, context);
				TimesheetReservation reservation = logic.planReservation(resource, tmpRequirement, context);
				if (reservation != null) {
					Date key = null;
					switch (direction) {
					case FORWARD: {
						key = reservation.getStartDateTime();
					}
						break;
					case BACKWARD: {
						key = reservation.getEndDateTime();
					}
						break;
					}
					if (key != null) {
						if (!reservations.containsKey(key)) {
							reservations.put(key, new ArrayList<>());
						}
						reservations.get(key).add(reservation);
					}
				}
			}
			int resolved = 0;
			NavigableSet<Date> keySet = null;
			switch (direction) {
			case FORWARD: {
				keySet = reservations.navigableKeySet();
			}
				break;
			case BACKWARD: {
				keySet = reservations.descendingKeySet();
			}
				break;
			}
			Date edgeDate = null;
			for (Date date : keySet) {
				List<TimesheetReservation> entries = reservations.get(date);
				resolved += entries.size();
				if (resolved >= howMany) {
					edgeDate = date;
					break;
				}
			}
			voidResults = edgeDate == null;
			TimeSegmentRequirement fixedAllocationRequirement = null;
			switch (direction) {
			case FORWARD: {
				tmpRequirement.setStartDateTime(edgeDate);
				fixedAllocationRequirement = new TimeSegmentRequirement(tmpRequirement);
				fixedAllocationRequirement.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
			}
				break;
			case BACKWARD: {
				tmpRequirement.setEndDateTime(edgeDate);
				fixedAllocationRequirement = new TimeSegmentRequirement(tmpRequirement);
				fixedAllocationRequirement.setEndAlignment(EndDateTimeAlignment.END_ON_END_PRECISELY);
			}
				break;
			}
			resolved = 0;
			for (Resource resource : resources) {
				ITimesheetLogic logic = componentsFactory.create(ITimesheetLogic.class, resource, context);
				TimesheetReservation reservation = logic.planReservation(resource, fixedAllocationRequirement, context);
				if (reservation != null) {
					resolved++;
					allocationOk = resolved >= howMany;
					if (allocationOk) {
						timeSegment = reservation;
						break;
					}
				}
			}

		} while (!allocationOk && !voidResults);
		return timeSegment;
	}

	TimeSegment getRelatedSetupTimeSegment(Machine resourceOption, ITimesheetLogic mainResourceCalendarLogic,
			Date currentWorkStartDateTime, double setupTime, ApsData context) {
		TimeSegment ts = null;
		TimeSegmentRequirement req = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
		req.setEndAlignment(EndDateTimeAlignment.END_ON_END_PRECISELY);
		req.setAvailabilityDuration(setupTime);
		req.setEndDateTime(currentWorkStartDateTime);
		TimeSegmentEvaluationResult res = mainResourceCalendarLogic.calculateTimeSegmentLimits(resourceOption, req);
		if (res.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
			ts = res.getResult();
		}
		return ts;
	}

	TimeSegment getRelatedWorkTimeSegment(Machine resourceOption, ITimesheetLogic mainResourceCalendarLogic,
			Date currentSetupEndDateTime, double workTime, ApsData context) {
		TimeSegment ts = null;
		TimeSegmentRequirement req = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
		req.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
		req.setAvailabilityDuration(workTime);
		req.setStartDateTime(currentSetupEndDateTime);
		TimeSegmentEvaluationResult res = mainResourceCalendarLogic.calculateTimeSegmentLimits(resourceOption, req);
		if (res.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
			ts = res.getResult();
		}
		return ts;
	}

}
