package com.openi40.scheduler.engine.taskssort;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsLogicOptions.SortDirection;
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
public abstract class AbstractTasksSort extends BusinessLogic<ApsSchedulingSet> implements ITasksSort {

	/**
	 * Sorts root tasks by parameters in the SchedulingAction options
	 * 
	 * @param EntityObject
	 * @return
	 */
	protected List<Task> sortRootTasks(ApsSchedulingSet EntityObject) {
		List<Task> taskOut = new ArrayList<Task>();
		for (WorkOrder wo : EntityObject.getWorkOrders()) {
			// wo.isRoot() &&
			if (wo.getRootTask() != null) {
				taskOut.add(wo.getRootTask());

			}
		}
		return applyCustomSorting(taskOut, EntityObject.getOptions().getSortOptions());
	}

	protected List<Task> applyCustomSorting(List<Task> rootTasks, List<ApsLogicOptions.SortOption> sortOptions) {
		for (ApsLogicOptions.SortOption sortOption : sortOptions) {
			rootTasks = applyCustomSorting(rootTasks, sortOption);
		}
		return rootTasks;
	}

	protected List<Task> applyCustomSorting(List<Task> rootTasks, ApsLogicOptions.SortOption sortOption) {
		SorterHelper sortHelper = null;
		for (SorterHelper helper : SorterHelper.PROPERTIES_LIST) {
			if (helper.getPropertyName().equalsIgnoreCase(sortOption.getPropertyName())) {
				sortHelper = helper;
			}
		}
		TreeMap<Object, List<Task>> orderedMap = new TreeMap<Object, List<Task>>();
		if (sortHelper == null)
			throw new OpenI40Exception("SorterHelper " + sortOption.getPropertyName() + " not found");
		for (Task task : rootTasks) {
			Object key = sortHelper.extractData(task);
			if (key == null)
				key = "";
			if (!orderedMap.containsKey(key)) {
				orderedMap.put(key, new ArrayList<Task>());
			}
			orderedMap.get(key).add(task);
		}

		if (sortOption.getSortDirection() == null || sortOption.getSortDirection() == SortDirection.ASC) {
			rootTasks = new ArrayList();
			for (List<Task> tasks : orderedMap.values()) {
				rootTasks.addAll(tasks);
			}
		} else {
			List<List<Task>> toReverse = new ArrayList<List<Task>>(orderedMap.values());
			rootTasks = new ArrayList();
			for (int i = toReverse.size() - 1; i >= 0; i--) {
				rootTasks.addAll(toReverse.get(i));
			}
		}
		return rootTasks;
	}

	public abstract List<Task> sort(ApsSchedulingSet EntityObject);
}