package com.openi40.scheduler.engine.realtime;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.setuptime.ISetupTimeLogic;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.RealTimeSegmentRequirements;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;

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
@DefaultImplementation(implemented = ITaskUnderProductionTimeRequirementsAnalyzer.class, entityClass = TaskEquipmentInfo.class)
public class TaskUnderProductionTimeRequirementsAnalyzerImpl extends BusinessLogic<TaskEquipmentInfo>
		implements ITaskUnderProductionTimeRequirementsAnalyzer {
	static Logger LOGGER = LoggerFactory.getLogger(TaskUnderProductionTimeRequirementsAnalyzerImpl.class);

	@Override
	public RealTimeSegmentRequirements analyzeUnderProductionTaskRequirements(Machine usedMachine,Task task,
			TaskEquipmentInfo taskEquipmentInfo, ApsSchedulingSet schedulingSet, ApsData context) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin analyzeUnderProductionTaskRequirements([" + task.getCode() + "],....)");
		}
		if (!context.isRealtime())
			throw new OpenI40Exception(
					"It does not make sense to analyze under production task if the system is not running realtime");		
		Date actualDateTime = context.getActualDateTime();
		RealTimeSegmentRequirements specs = new RealTimeSegmentRequirements(context);
		specs.getPreparationRequirement().setStartDateTime(task.getAcquiredStartSetup());
		specs.getPreparationRequirement().setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
		specs.getPreparationRequirement()
				.setEndAlignment(task.getAcquiredEndSetup() != null ? EndDateTimeAlignment.END_ON_END_PRECISELY : null);
		specs.getPreparationRequirement().setEndDateTime(task.getAcquiredEndSetup());
		specs.getExecutionRequirement().setStartDateTime(task.getAcquiredStartWork());
		specs.getExecutionRequirement().setEndDateTime(task.getAcquiredEndWork());
		specs.getExecutionRequirement()
				.setEndAlignment(task.getAcquiredEndWork() != null ? EndDateTimeAlignment.END_ON_END_PRECISELY : null);
		ISetupTimeLogic setupTimeLogic = this.componentsFactory.create(ISetupTimeLogic.class, taskEquipmentInfo,
				context);
		IWorkTimeLogic workTimeLogic = this.componentsFactory.create(IWorkTimeLogic.class, taskEquipmentInfo, context);
		ITimesheetLogic timesheetLogic = this.componentsFactory.create(ITimesheetLogic.class, usedMachine, context);
		double setupTime = taskEquipmentInfo.getTaskInfo().getSetupTime();
		double machineTime = taskEquipmentInfo.getTaskInfo().getMachineTime();

		if (setupTime <= 0.0) {
			String msg = "Setup time is unset in equipment taskInfo for task:" + task.getCode();
			LOGGER.error(msg);
			throw new OpenI40Exception(msg);

		}
		if (machineTime <= 0.0) {
			String msg = "Machine time is unset in equipment taskInfo for task:" + task.getCode();
			LOGGER.error(msg);
			throw new OpenI40Exception(msg);
		}
		switch (task.getStatus()) {
		case EXECUTING_SETUP: {
			// if still under the acquiredStartSetup + setupTime time inside the calendar
			// considering actualDateTime considering it as a upper boundary
			// otherwise considering actualDataTime as hypotetical end of setup time and
			// beginning working time
			TimeSegmentRequirement setupRequirement = new TimeSegmentRequirement(specs.getPreparationRequirement());
			// Ensure no yet endDateTime is set
			setupRequirement.setEndDateTime(null);
			setupRequirement.setAvailabilityDuration(setupTime);
			TimeSegmentEvaluationResult setupSegmentResult = timesheetLogic.calculateTimeSegmentLimits(usedMachine,
					setupRequirement);
			TimeSegment setupSegment = setupSegmentResult.getResult();
			switch (setupSegmentResult.getResultType()) {
			case SUCCESSFULLY_EVALUATED: {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Setup time successfully evaluated for task [" + task.getCode() + "]");
				}
				// if actualDateTime is internal to the range of setupSegment then use it as
				// setup real time segment
				if (setupSegment.isInRange(actualDateTime)) {
					specs.getPreparationRequirement().setEndDateTime(setupSegment.getEndDateTime());
				} else if (actualDateTime.after(setupSegment.getEndDateTime())) {
					// if actualDateTime after end of setupSegment we use the hipotesys of setup
					// ends now
					specs.getPreparationRequirement().setEndDateTime(actualDateTime);
				} else {
					String msg = "actualDateTime < notified acquiredSetupStartDateTime check system date/time on all systems being aligned";
					LOGGER.error(msg);
					specs.getPreparationRequirement().setEndDateTime(setupSegment.getEndDateTime());
				}
				completeWorkTimeRequirementsWithKnownSetupBoundaries(specs, taskEquipmentInfo);
			}
				break;
			default: {
				specs.setInvalidTaskConditions(true);
				specs.getInvalidTaskConditionsMessages()
						.add("cannot evaluate setup boundaries of task [" + task.getCode() + "] status:["
								+ enumToString(task.getStatus()) + "] calendar calculation with result code:"
								+ enumToString(setupSegmentResult.getResultType()));
				return specs;
			}

			}
		}
			break;
		case SETUP_DONE: {
			// consider start working at actualDateTime as hipotesys
			if (specs.getPreparationRequirement().getStartDateTime() == null
					|| specs.getPreparationRequirement().getEndDateTime() == null) {
				String msg = "preparation start date time ("
						+ dateToString(specs.getPreparationRequirement().getStartDateTime()) + ") and end date time ("
						+ dateToString(specs.getPreparationRequirement().getEndDateTime())
						+ ") are not both known, maybe missed some field notifications";
				LOGGER.warn(msg);
				// TODO: try reconstruct setup boundaries deducing them
				TimeSegmentRequirement setupRequirement = new TimeSegmentRequirement(specs.getPreparationRequirement());
				setupRequirement.setAvailabilityDuration(setupTime);
				if (specs.getPreparationRequirement().getStartDateTime() != null) {
					setupRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
				} else if (specs.getPreparationRequirement().getEndDateTime() != null) {
					setupRequirement.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
				}
				TimeSegmentEvaluationResult setupSegmentResult = timesheetLogic.calculateTimeSegmentLimits(usedMachine,
						setupRequirement);
				TimeSegment setupSegment = setupSegmentResult.getResult();
				switch (setupSegmentResult.getResultType()) {
				case SUCCESSFULLY_EVALUATED: {
					specs.getPreparationRequirement().setStartDateTime(setupSegment.getStartDateTime());
					specs.getPreparationRequirement().setEndDateTime(setupSegment.getEndDateTime());
					specs.getPreparationRequirement()
							.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				}
					break;
				default: {
					specs.setInvalidTaskConditions(true);
					specs.getInvalidTaskConditionsMessages()
							.add("cannot evaluate setup boundaries of task [" + task.getCode() + "] status:["
									+ enumToString(task.getStatus()) + "]  calendar calculation with result code:"
									+ enumToString(setupSegmentResult.getResultType()));
					return specs;
				}
				}
			}
			TimeSegmentRequirement workRequirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
			workRequirement.setStartDateTime(actualDateTime);
			workRequirement.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
			TimeSegmentEvaluationResult workSegmentResult = timesheetLogic.calculateTimeSegmentLimits(usedMachine,
					workRequirement);
			switch (workSegmentResult.getResultType()) {
			case SUCCESSFULLY_EVALUATED: {
				specs.getExecutionRequirement().setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				specs.getExecutionRequirement().setStartDateTime(workSegmentResult.getResult().getStartDateTime());
				specs.getExecutionRequirement().setEndDateTime(workSegmentResult.getResult().getEndDateTime());
			}
				break;
			default: {
				specs.setInvalidTaskConditions(true);
				specs.getInvalidTaskConditionsMessages()
						.add("cannot evaluate work boundaries of task [" + task.getCode() + "] status:["
								+ enumToString(task.getStatus()) + "]  calendar calculation with result code:"
								+ enumToString(workSegmentResult.getResultType()));
				return specs;
			}
			}

		}
			break;
		case EXECUTING_WORK: {
			// if still under the acquiredStartWork + setupTime time inside the calendar
			// considering actualDateTime considering it as a upper boundary
			// otherwise considering actualDataTime as hypotetical end of setup time and
			// beginning working time
			TimeSegmentRequirement workRequirement = new TimeSegmentRequirement(specs.getExecutionRequirement());
			// Ensure no yet endDateTime is set
			workRequirement.setEndDateTime(null);
			workRequirement.setAvailabilityDuration(machineTime);
			TimeSegmentEvaluationResult workSegmentResult = timesheetLogic.calculateTimeSegmentLimits(usedMachine,
					workRequirement);
			TimeSegment workSegment = workSegmentResult.getResult();
			switch (workSegmentResult.getResultType()) {
			case SUCCESSFULLY_EVALUATED: {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Work time successfully evaluated for task [" + task.getCode() + "]");
				}
				// if actualDateTime is internal to the range of workSegment then use it as
				// work real time segment
				if (workSegment.isInRange(actualDateTime)) {
					specs.getExecutionRequirement().setEndDateTime(workSegment.getEndDateTime());
				} else if (actualDateTime.after(workSegment.getEndDateTime())) {
					// if actualDateTime after end of workSegment we use the hipotesys of work
					// ends now
					specs.getExecutionRequirement().setEndDateTime(actualDateTime);
				} else {
					String msg = "actualDateTime < notified acquiredWorkStartDateTime check system date/time on all systems being aligned";
					LOGGER.error(msg);
					specs.getExecutionRequirement().setEndDateTime(workSegment.getEndDateTime());
				}
				completeSetupTimeRequirementsWithKnownWorkBoundaries(specs, taskEquipmentInfo);
			}
				break;
			default: {
				specs.setInvalidTaskConditions(true);
				specs.getInvalidTaskConditionsMessages()
						.add("cannot evaluate work boundaries of task [" + task.getCode() + "] status:["
								+ enumToString(task.getStatus()) + "] calendar calculation with result code:"
								+ enumToString(workSegmentResult.getResultType()));
				return specs;
			}

			}
		}
			break;
		case EXECUTED: {
			// Use already setted boundaries as fixed exact past allocation boundaries
			// Assume boundary are valid as first implementation
			if (specs.getPreparationRequirement().getStartDateTime() == null
					|| specs.getPreparationRequirement().getEndDateTime() == null
					|| specs.getExecutionRequirement().getStartDateTime() == null
					|| specs.getExecutionRequirement().getEndDateTime() == null) {
				if (specs.getPreparationRequirement().getStartDateTime() == null
						|| specs.getPreparationRequirement().getEndDateTime() == null) {
					fixPraparationRequirement(specs,taskEquipmentInfo);
				}
				if (specs.getExecutionRequirement().getStartDateTime() == null
					|| specs.getExecutionRequirement().getEndDateTime() == null) {
					fixExecutionRequirement(specs,taskEquipmentInfo);
				}
			}
			specs.getPreparationRequirement().setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
			specs.getExecutionRequirement().setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
		}
			break;

		case PAUSED: {
			// Consider declared resources
		}
			break;

		default: {
			// if ABORTED or YET_NOT_EXECUTED or incoherent states passed than throw an
			// exception
			throw new OpenI40Exception("Status of current task is " + task.getStatus().name()
					+ " incoherent with analyzing its under production time requirements");
		}

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End analyzeUnderProductionTaskRequirements([" + task.getCode() + "],....)");
		}
		return specs;
	}

	private void fixExecutionRequirement(RealTimeSegmentRequirements specs, TaskEquipmentInfo taskEquipmentInfo) {
		// TODO Auto-generated method stub
		
	}

	private void fixPraparationRequirement(RealTimeSegmentRequirements specs, TaskEquipmentInfo taskEquipmentInfo) {
		// TODO Auto-generated method stub
		
	}

	private void completeSetupTimeRequirementsWithKnownWorkBoundaries(RealTimeSegmentRequirements specs,
			TaskEquipmentInfo taskEquipmentInfo) {
		// TODO Auto-generated method stub

	}

	private String enumToString(Enum _enum) {

		return _enum != null ? _enum.name() : "null";
	}

	private String dateToString(Date dateTime) {

		return dateTime != null ? dateTime.toGMTString() : "null";
	}

	private void completeWorkTimeRequirementsWithKnownSetupBoundaries(RealTimeSegmentRequirements specs,
			TaskEquipmentInfo taskEquipmentInfo) {
		// TODO Auto-generated method stub

	}

}
