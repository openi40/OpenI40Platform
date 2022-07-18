package com.openi40.scheduler.engine.taskssort;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = ITasksSort.class, entityClass = ApsSchedulingSet.class, key = "DEFAULT", switchImplementationProperty = "algorithmType")
public class TasksSortImpl extends BusinessLogic<ApsSchedulingSet> implements ITasksSort {
	static Logger LOGGER = LoggerFactory.getLogger(TasksSortImpl.class);

	protected List<Task> sortRootTasks(ApsSchedulingSet EntityObject) {
		List<Task> taskOut = new ArrayList<Task>();
		for (WorkOrder wo : EntityObject.getWorkOrders()) {
			if (wo.getRootTask() != null) {
				taskOut.add(wo.getRootTask());

			}
		}
		return taskOut;
	}

	public List<Task> sort(ApsSchedulingSet EntityObject) {

		List<Task> rootTasks = sortRootTasks(EntityObject);
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Begin DefaultTaskOrderEvaluator.GetOrderedList(..)", "custom ordering task list handler");

		List<Task> outTasks = TasksSorterUtil.getInstance().listInDependenceOrder(rootTasks, EntityObject);
		
		if (LOGGER.isDebugEnabled()) LOGGER.debug("End DefaultTaskOrderEvaluator.GetOrderedList(..)", "custom ordering task list handler");
		return outTasks;
	}
}