package com.openi40.scheduler.model.aps;

import lombok.Data;

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
@Data
public class ApsMessageConstrants {
	String code = null, description = null;
	ApsMessageLevel msgLevel = ApsMessageLevel.INFO;
	ApsMessageCategory msgCategory = ApsMessageCategory.CONSTRAINT_UNSATISFIED;

	public ApsMessageConstrants(ApsMessageCategory msgCategory, ApsMessageLevel msgLevel, String code,
			String description) {
		this.msgCategory = msgCategory;
		this.msgLevel = msgLevel;
		this.code = code;
		this.description = description;
	}

	public final static ApsMessageConstrants PREDECESSOR_TASK_UNSCHEDULED = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"PREDECESSOR_TASK_UNSCHEDULED",
			"The preceiding task ${relatedTask.workOrderCode}/${relatedTask.sequenceCode} is not scheduled");
	public final static ApsMessageConstrants SUCCESSIVE_TASK_UNSCHEDULED = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"SUCCESSIVE_TASK_UNSCHEDULED",
			"The following task ${relatedTask.workOrderCode}/${relatedTask.sequenceCode} is not scheduled");
	public final static ApsMessageConstrants DELIVERY_DATE_CONSTRAINT_NOT_MET = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.WARNING, "DELIVERY_DATE_CONSTRAINT_NOT_MET",
			"The  task ${task.workOrderCode}/${task.sequenceCode} will be in late, original due date whas ${task.askedDeliveryDateTime} but it will end at ${task.endDateTime}");
	public final static ApsMessageConstrants WINDOW_DATE_START_DATE_CONSTRAINT_NOT_MET = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"WINDOW_DATE_START_DATE_CONSTRAINT_NOT_MET",
			"The  task ${task.workOrderCode}/${task.sequenceCode} out of begin scheduling window limit");
	public final static ApsMessageConstrants WINDOW_DATE_END_DATE_CONSTRAINT_NOT_MET = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"WINDOW_DATE_END_DATE_CONSTRAINT_NOT_MET",
			"The  task ${task.workOrderCode}/${task.sequenceCode} out of end scheduling window limit");
	public static final ApsMessageConstrants MATERIAL_SUPPLY_UNAVAILABLE = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"MATERIAL_SUPPLY_UNAVAILABLE",
			"Unavailable material material ${product.code}  for  task ${task.workOrderCode}/${task.sequenceCode}");
	public static final ApsMessageConstrants UNAVAILABLE_EQUIPMENT = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION, "UNAVAILABLE_EQUIPMENT",
			"Cannot allocate full equipment   task ${task.workOrderCode}/${task.sequenceCode}");
	public static final ApsMessageConstrants FINISH_TASK_TIME_FOLLOWING_TASK_START_TIME_RELATION = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"FINISH_TASK_TIME_FOLLOWING_TASK_START_TIME_RELATION",
			"Relation between task ${task.workOrderCode}/${task.sequenceCode} finish time  and  task ${relatedTask.workOrderCode}/${relatedTask.sequenceCode} start time not met");
	public static final ApsMessageConstrants FINISH_AFTER_CHILD_CONSTRAINT_RULE = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"FINISH_AFTER_CHILD_CONSTRAINT_RULE",
			"Task ${task.workOrderCode}/${task.sequenceCode}  depends on task ${relatedTask.workOrderCode}/${relatedTask.sequenceCode} but it finishes after");
	public static final ApsMessageConstrants MIN_PRODUCTION_START_DATE_CONSTRAINT_NOT_MET = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"MIN_PRODUCTION_START_DATE_CONSTRAINT_NOT_MET",
			"The  task ${task.workOrderCode}/${task.sequenceCode} out of begin scheduling time after ${task.minProductionDateConstraint}");
	public static final ApsMessageConstrants MAX_PRODUCTION_END_DATE_CONSTRAINT_NOT_MET = new ApsMessageConstrants(
			ApsMessageCategory.CONSTRAINT_UNSATISFIED, ApsMessageLevel.UNSCHEDULABLE_CONDITION,
			"MAX_PRODUCTION_START_DATE_CONSTRAINT_NOT_MET",
			"The  task ${task.workOrderCode}/${task.sequenceCode} out of end scheduling time before ${task.minProductionDateConstraint}");

}
