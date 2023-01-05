package com.openi40.scheduler.engine.rules.planner;

import com.openi40.scheduler.common.utils.EnumUtil;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

public class ProductionMonitoringUtil {
	public static final TaskStatus UNDER_PRODUCTION_STATES[] = new TaskStatus[] { TaskStatus.EXECUTING_SETUP,
			TaskStatus.SETUP_DONE, TaskStatus.EXECUTING_WORK, TaskStatus.EXECUTED, TaskStatus.ABORTED };

	public static boolean isUnderProduction(Task task) {
		return EnumUtil.isInList(task.getStatus(), UNDER_PRODUCTION_STATES);
	}

}
