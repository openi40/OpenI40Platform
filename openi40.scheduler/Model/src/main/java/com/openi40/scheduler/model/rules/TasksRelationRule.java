package com.openi40.scheduler.model.rules;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.TimeSegmentType;

import lombok.Data;
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
@Data
public class TasksRelationRule extends Rule {

	private TaskEdge Edge = null;

	private PeriodsAlignmentType PeriodAlignment = PeriodsAlignmentType.START_AFTER_END;

	private Task RelatedTask = null;

	private TimeSegmentType TimeRangeType = com.openi40.scheduler.model.time.TimeSegmentType.WORK_TIME;

	public TasksRelationRule(ApsData context) {
		super(context);
	}

	public TasksRelationRule(Task target, ConstraintOrigin origin, ConstraintPriority priority, Task relatedTask,
			PeriodsAlignmentType alignmentType, TimeSegmentType timeSegmentType) {
		super(target, origin, priority);

		setPeriodAlignment(alignmentType);
		setRelatedTask(relatedTask);
		setTimeRangeType(timeSegmentType);
	}

	public TasksRelationRule(Task target, ConstraintOrigin origin, ConstraintPriority priority, Task relatedTask,
			TaskEdge edge) {
		super(target, origin, priority);

		setPeriodAlignment(edge.getAlignmentType());
		setRelatedTask(relatedTask);
		setTimeRangeType(edge.getTimeRangeType());
		setEdge(edge);
	}

}