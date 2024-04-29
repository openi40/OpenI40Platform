package com.openi40.scheduler.engine.equipment.allocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult.TimeSegmentEvaluationResultType;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
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
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
class WorkTimeRequirementManager {
	static final WorkTimeRequirementManager Instance = new WorkTimeRequirementManager();
	static Logger LOGGER = LoggerFactory.getLogger(WorkTimeRequirementManager.class);

	private WorkTimeRequirementManager() {

	}

	TimeSegmentRequirement rationalizeWorkTimeRequirement(TimeSegmentRequirement setupTimeRange,
			TimeSegmentRequirement workTimeRange, ApsLogicDirection direction, Machine resourceOption, double setupTime,
			double workTime, ITimesheetLogic timesheetLogic, Task task, ApsData context) {
		TimeSegmentRequirement outRequirement = null;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin WorkTimeRequirementManager.rationalizeWorkTimeRequirement(..)");
		}

		if (workTimeRange != null) {
			if (workTimeRange.isLowerLimited() && workTimeRange.getStartAlignment() != null) {
				outRequirement = new TimeSegmentRequirement(workTimeRange);
				outRequirement.setAvailabilityDuration(workTime);
				return outRequirement;
			} else if (workTimeRange.isUpperLimited() && workTimeRange.getEndAlignment() != null) {
				outRequirement = new TimeSegmentRequirement(workTimeRange);
				outRequirement.setAvailabilityDuration(workTime);
				return outRequirement;
			}
		}
		if (setupTimeRange != null) {
			if ((setupTimeRange.isLowerLimited() && setupTimeRange.getStartAlignment() != null)
					|| (setupTimeRange.isUpperLimited() && setupTimeRange.getEndAlignment() != null)) {
				outRequirement = rationalizeWorkTimeRequirementFromSetupTime(setupTimeRange, direction, resourceOption,
						setupTime, workTime, timesheetLogic, task, context);
			}
		}
		if (outRequirement == null) {
			outRequirement = buildDefaultWorkTimeRequirement(resourceOption, setupTime, workTime, timesheetLogic,
					direction, task, context);

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End WorkTimeRequirementManager.rationalizeWorkTimeRequirement(..)");
		}
		return outRequirement;
	}

	TimeSegmentRequirement buildDefaultWorkTimeRequirement(Machine resourceOption, double setupTime, double workTime,
			ITimesheetLogic timesheetLogic, ApsLogicDirection direction, Task task, ApsData context) {
		TimeSegmentRequirement outValue = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
		outValue.setAvailabilityDuration(workTime);
		switch (direction) {
		case FORWARD: {
			TimeSegmentRequirement setupReq = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
			setupReq.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			setupReq.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
			setupReq.setAvailabilityDuration(setupTime);
			TimeSegmentEvaluationResult ts = timesheetLogic.calculateTimeSegmentLimits(resourceOption, setupReq);
			if (ts.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
				outValue.setStartDateTime(ts.getResult().getEndDateTime());
				TimesheetReservation simulatedReservation = timesheetLogic.planReservation(resourceOption, setupReq,
						task);
				if (simulatedReservation != null) {
					outValue.setStartDateTime(simulatedReservation.getEndDateTime());
				}
			}

		}
			break;
		case BACKWARD: {
			outValue.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
			outValue.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
		}
			break;
		}
		return outValue;
	}

	private TimeSegmentRequirement rationalizeWorkTimeRequirementFromSetupTime(TimeSegmentRequirement setupTimeRange,
			ApsLogicDirection direction, Machine resourceOption, double setupTime, double workTime,
			ITimesheetLogic timesheetLogic, Task task, ApsData context) {
		TimeSegmentRequirement copyReq = new TimeSegmentRequirement(setupTimeRange);
		copyReq.setAvailabilityDuration(setupTime);
		
		TimeSegmentEvaluationResult returned = timesheetLogic.calculateTimeSegmentLimits(resourceOption, copyReq);

		if (returned.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
			// Using same alignment type asked for setup so can roll in time or not
			// according to original condition
			TimeSegmentRequirement outRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
			outRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			outRequirement.setStartDateTime(returned.getResult().getEndDateTime());
			outRequirement.setAvailabilityDuration(workTime);
			outRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
			return outRequirement;
		} else if (returned.getResultType() == TimeSegmentEvaluationResultType.LOWER_BOUND_LIMIT_REACH) {
			// if lower bound limit reach than try to start from minimum window value and
			// reallocating
			TimeSegmentRequirement startReq = new TimeSegmentRequirement(TimeSegmentType.SETUP_TIME);
			startReq.setAvailabilityDuration(setupTime);
			startReq.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			startReq.setStartDateTime(context.getSchedulingWindow().getStartDateTime());
			returned = timesheetLogic.calculateTimeSegmentLimits(resourceOption, startReq);
			if (returned.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
				// Using same alignment type asked for setup so can roll in time or not
				// according to original condition
				TimeSegmentRequirement outRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
				outRequirement.setStartAlignment(setupTimeRange.getStartAlignment());
				outRequirement.setStartDateTime(returned.getResult().getEndDateTime());
				outRequirement.setAvailabilityDuration(workTime);
				outRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
				return outRequirement;
			}
		} else if (returned.getResultType() == TimeSegmentEvaluationResultType.UPPER_BOUND_LIMIT_REACH) {
			TimeSegmentRequirement workReq = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
			workReq.setAvailabilityDuration(workTime);
			workReq.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
			workReq.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
			returned = timesheetLogic.calculateTimeSegmentLimits(resourceOption, workReq);
			if (returned.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
				// Using same alignment type asked for setup so can roll in time or not
				// according to original condition
				TimeSegmentRequirement outRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
				outRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
				outRequirement.setStartDateTime(returned.getResult().getStartDateTime());
				outRequirement.setAvailabilityDuration(workTime);
				outRequirement.setEndDateTime(context.getSchedulingWindow().getEndDateTime());
				return outRequirement;
			}
		}
		return null;
	}
}
