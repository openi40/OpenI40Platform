package com.openi40.scheduler.engine.equipment.allocation;

import java.util.Date;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.tasksoffset.ITasksRelationOffset;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult.TimeSegmentEvaluationResultType;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.TasksRelationRule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
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
class RelatedTasksOffsetsManager {
	final static RelatedTasksOffsetsManager Instance = new RelatedTasksOffsetsManager();

	private RelatedTasksOffsetsManager() {

	}

	TimeSegmentRequirement manageOffsets(TimeSegmentRequirement originalWorkRequirement, Machine resourceOption,
			TaskEquipmentInfo taskEquipmentInfo, double setupTime, double workTime,
			IContextualBusinessLogicFactory componentsFactory, List<TasksRelationRule> taskRelationRules, Task task,
			ApsLogicDirection direction, ApsData context) {
		TimeSegmentRequirement reqCopy = new TimeSegmentRequirement(originalWorkRequirement);
		ITimesheetLogic resourceOptionCalendarLogic = componentsFactory.create(ITimesheetLogic.class, resourceOption,
				context);
		switch (direction) {
		case FORWARD: {
			Date minimumStartBound = null;
			Date minimumEndBound = null;
			for (TasksRelationRule relationRule : taskRelationRules) {
				ITasksRelationOffset tasksRelationOffsetCalculator = componentsFactory
						.create(ITasksRelationOffset.class, relationRule, context);
				Task producer = relationRule.getEdge().getProducerTask();
				if (producer.isSuccessfullyScheduled()) {
					TimeSegment referredSegment = producer.getWorkPhaseExecution();
					if (relationRule.getPeriodAlignment() == PeriodsAlignmentType.START_AFTER_START) {
						double startOffset = tasksRelationOffsetCalculator.calculateStartWorkOffset(relationRule,
								producer.getEquipment(), taskEquipmentInfo, task.getParentSchedulingSet());
						double endOffset = tasksRelationOffsetCalculator.calculateEndWorkOffset(relationRule,
								producer.getEquipment(), taskEquipmentInfo, task.getParentSchedulingSet());
						// Calculating start time minimum in the producer machine task calendar
						Machine productorTaskMachine = producer.getEquipment().getExecution().getResource()
								.getChoosenEquipment();
						ITimesheetLogic productionCalendarLogic = componentsFactory.create(ITimesheetLogic.class,
								productorTaskMachine, context);

						TimeSegmentRequirement startCalcReq = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
						startCalcReq.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
						startCalcReq.setStartDateTime(referredSegment.getStartDateTime());
						startCalcReq.setAvailabilityDuration(startOffset);
						TimeSegmentEvaluationResult firstProducingTransfer = productionCalendarLogic
								.calculateTimeSegmentLimits(productorTaskMachine, startCalcReq);
						if (firstProducingTransfer
								.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
							minimumStartBound = minimumStartBound == null
									? firstProducingTransfer.getResult().getEndDateTime()
									: firstProducingTransfer.getResult().getEndDateTime().after(minimumStartBound)
											? firstProducingTransfer.getResult().getEndDateTime()
											: minimumStartBound;
						}
						// Calculate end time minimium in the actual machine task calendar
						TimeSegmentRequirement endCalcReq = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
						endCalcReq.setStartAlignment(StartDateTimeAlignment.START_AFTER_START_ASAP);
						endCalcReq.setStartDateTime(referredSegment.getEndDateTime());
						endCalcReq.setAvailabilityDuration(endOffset);
						TimeSegmentEvaluationResult lastProducingTransfer = resourceOptionCalendarLogic
								.calculateTimeSegmentLimits(resourceOption, endCalcReq);
						if (lastProducingTransfer
								.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
							minimumEndBound = minimumEndBound == null
									? lastProducingTransfer.getResult().getEndDateTime()
									: lastProducingTransfer.getResult().getEndDateTime().after(minimumEndBound)
											? lastProducingTransfer.getResult().getEndDateTime()
											: minimumEndBound;
						}
					} else  {
						//if (relationRule.getPeriodAlignment() == PeriodsAlignmentType.START_AFTER_END)
						minimumStartBound = minimumStartBound == null ? referredSegment.getEndDateTime()
								: referredSegment.getEndDateTime().after(minimumStartBound)
										? referredSegment.getEndDateTime()
										: minimumStartBound;
					}
				}

			}

			if (minimumStartBound != null) {
				if (reqCopy.isLowerLimited()) {
					if (minimumStartBound.after(reqCopy.getStartDateTime())) {
						reqCopy.setStartDateTime(minimumStartBound);
					}
				}
			}
			if (minimumEndBound != null) {
				TimeSegmentEvaluationResult reqResult = resourceOptionCalendarLogic
						.calculateTimeSegmentLimits(resourceOption, reqCopy);
				if (reqResult.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
					if (minimumEndBound.after(reqResult.getResult().getEndDateTime())) {
						TimeSegmentRequirement requirement = new TimeSegmentRequirement(TimeSegmentType.WORK_TIME);
						requirement.setAvailabilityDuration(workTime);
						requirement.setEndAlignment(EndDateTimeAlignment.END_ON_END_PRECISELY);
						requirement.setEndDateTime(minimumEndBound);
						reqResult = resourceOptionCalendarLogic.calculateTimeSegmentLimits(resourceOption, requirement);
						if (reqResult.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
							reqCopy.setStartDateTime(reqResult.getResult().getStartDateTime());
						}
					}
				}
			}

		}
			break;
		case BACKWARD: {
			Date maximumStartBound = null;
			Date maximumEndBound = null;
			for (TasksRelationRule relationRule : taskRelationRules) {
				ITasksRelationOffset tasksRelationOffsetCalculator = componentsFactory
						.create(ITasksRelationOffset.class, relationRule, context);
				Task consumer = relationRule.getEdge().getConsumerTask();
				TimeSegment referredSegment = consumer.getWorkPhaseExecution();
				if (relationRule.getPeriodAlignment() == PeriodsAlignmentType.START_AFTER_START) {
					double startOffset = tasksRelationOffsetCalculator.calculateStartWorkOffset(relationRule,
							taskEquipmentInfo, consumer.getEquipment(), task.getParentSchedulingSet());
					double endOffset = tasksRelationOffsetCalculator.calculateEndWorkOffset(relationRule,
							taskEquipmentInfo, consumer.getEquipment(), task.getParentSchedulingSet());

					// use startOffset to calculate from consumer start backward to obtain producer
					// (actual task) start
					// must calculate it backward on the producer calendar
					TimeSegmentRequirement endCalcReq = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
					endCalcReq.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
					endCalcReq.setEndDateTime(referredSegment.getStartDateTime());
					endCalcReq.setAvailabilityDuration(startOffset);
					TimeSegmentEvaluationResult firstProducingTransfer = resourceOptionCalendarLogic
							.calculateTimeSegmentLimits(resourceOption, endCalcReq);
					if (firstProducingTransfer != null && firstProducingTransfer
							.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
						maximumStartBound = maximumStartBound == null
								? firstProducingTransfer.getResult().getStartDateTime()
								: firstProducingTransfer.getResult().getStartDateTime().before(maximumStartBound)
										? firstProducingTransfer.getResult().getStartDateTime()
										: maximumStartBound;
					}
					// use endOffset to calculate from consumer end forward on the consumer calendar
					endCalcReq = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
					endCalcReq.setEndAlignment(EndDateTimeAlignment.END_BEFORE_END_ASAP);
					endCalcReq.setEndDateTime(referredSegment.getEndDateTime());
					endCalcReq.setAvailabilityDuration(endOffset);
					Machine consumerMaching = consumer.getEquipment().getPreparation().getResource()
							.getChoosenEquipment();
					ITimesheetLogic consumerCalendarLogic = componentsFactory.create(ITimesheetLogic.class,
							consumerMaching, context);
					TimeSegmentEvaluationResult lastProducingTransfer = consumerCalendarLogic
							.calculateTimeSegmentLimits(consumerMaching, endCalcReq);
					if (lastProducingTransfer != null && lastProducingTransfer
							.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
						maximumEndBound = maximumEndBound == null ? lastProducingTransfer.getResult().getStartDateTime()
								: lastProducingTransfer.getResult().getStartDateTime().before(maximumEndBound)
										? lastProducingTransfer.getResult().getStartDateTime()
										: maximumEndBound;
					}
				} else  { 
					//if (relationRule.getPeriodAlignment() == PeriodsAlignmentType.START_AFTER_END)
					maximumEndBound = maximumEndBound == null ? referredSegment.getStartDateTime()
							: referredSegment.getStartDateTime().before(maximumEndBound)
									? referredSegment.getStartDateTime()
									: maximumEndBound;
				}

			}
			if (maximumStartBound != null) {
				TimeSegmentRequirement req = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
				req.setStartAlignment(StartDateTimeAlignment.START_ON_START_PRECISELY);
				req.setStartDateTime(maximumStartBound);
				req.setAvailabilityDuration(workTime);
				TimeSegmentEvaluationResult simulateMaximumStartBound = resourceOptionCalendarLogic
						.calculateTimeSegmentLimits(resourceOption, req);
				if (simulateMaximumStartBound
						.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
					maximumEndBound = maximumEndBound == null ? simulateMaximumStartBound.getResult().getEndDateTime()
							: simulateMaximumStartBound.getResult().getEndDateTime().before(maximumEndBound)
									? simulateMaximumStartBound.getResult().getEndDateTime()
									: maximumEndBound;
				}
			}
			if (maximumEndBound != null) {
				TimeSegmentRequirement req = new TimeSegmentRequirement(TimeSegmentType.CALENDAR_TIME);
				req.setEndAlignment(EndDateTimeAlignment.END_ON_END_PRECISELY);
				req.setEndDateTime(maximumEndBound);
				req.setAvailabilityDuration(workTime);
				TimeSegmentEvaluationResult simulateMaximumEndBound = resourceOptionCalendarLogic
						.calculateTimeSegmentLimits(resourceOption, req);
				if (simulateMaximumEndBound.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
					maximumStartBound = maximumStartBound == null
							? simulateMaximumEndBound.getResult().getStartDateTime()
							: simulateMaximumEndBound.getResult().getStartDateTime().before(maximumStartBound)
									? simulateMaximumEndBound.getResult().getStartDateTime()
									: maximumStartBound;
				}
			}
			//reqCopy.setStartDateTime(maximumStartBound);
			reqCopy.setEndDateTime(maximumEndBound);

		}
			break;
		}

		return reqCopy;
	}
}
