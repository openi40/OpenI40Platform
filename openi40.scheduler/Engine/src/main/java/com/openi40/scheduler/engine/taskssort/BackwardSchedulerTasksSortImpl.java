package com.openi40.scheduler.engine.taskssort;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.contextualplugarch.AlternativeImplementation;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.tasks.Task;
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
@AlternativeImplementation(implemented = ITasksSort.class, entityClass = ApsSchedulingSet.class, key = ApsLogics.BACKWARD_APS, switchImplementationProperty = "algorithmType")
public class BackwardSchedulerTasksSortImpl extends AbstractTasksSort {
	static Logger LOGGER = LoggerFactory.getLogger(BackwardSchedulerTasksSortImpl.class);

	@Override
	public List<Task> sort(ApsSchedulingSet EntityObject) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Begin BackwardSchedulerTasksSortImpl.sort(..)", "custom ordering task list handler");
		List<Task> rootTasks = sortRootTasks(EntityObject);
		List<Task> outTasks =TasksSorterUtil.getInstance().listInDependenceReverseOrder(rootTasks, EntityObject);
		if (LOGGER.isDebugEnabled()) LOGGER.debug("End BackwardSchedulerTasksSortImpl.sort(..)", "custom ordering task list handler");
		return outTasks;
	}
}