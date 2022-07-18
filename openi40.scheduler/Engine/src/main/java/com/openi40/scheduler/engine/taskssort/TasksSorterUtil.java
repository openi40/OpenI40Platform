package com.openi40.scheduler.engine.taskssort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
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
public class TasksSorterUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(TasksSorterUtil.class);
	private static TasksSorterUtil Instance = new TasksSorterUtil();

	public static TasksSorterUtil getInstance() {
		return Instance;
	}

	

	private List<Task> listInDependenceOrder(Task rootTask, ApsSchedulingSet action, Map<String, Task> avoidDuplicates) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin TasksSorterUtil.ListInDependenceOrder");
		}
		List<Task> list = new ArrayList<Task>();
		assert rootTask.getParentSchedulingSet() != null;
		if (rootTask.getParentSchedulingSet() == action && !avoidDuplicates.containsKey(rootTask.getId())) {
			avoidDuplicates.put(rootTask.getId(), rootTask);
			for (TaskEdge child : rootTask.getChildTasks()) {
				List<Task> collection = listInDependenceOrder(child.getProducerTask(), action, avoidDuplicates);

				CollectionUtil.getInstance().AddCollection(list, collection);
			}
			list.add(rootTask);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End TasksSorterUtil.ListInDependenceOrder");
		}
		return list;
	}
	public List<Task> avoidDuplicates(List<Task> list) {
		
		List<Task> out=new ArrayList<>();
		Map<String,Task> avoidDuplicates=new HashMap<>();
		for (Task task : list) {
			if (!avoidDuplicates.containsKey(task.getId())) {
				out.add(task);
				avoidDuplicates.put(task.getId(), task);
			}
		}
		return out;
	}
	public List<Task> listInDependenceOrder(List<Task> rootTasks, ApsSchedulingSet action) {
		Map<String, Task> avoidDuplicates = new HashMap<String, Task>();
		List<Task> list = new ArrayList<Task>();
		for (Task rootTask : rootTasks) {
			assert rootTask.getParentSchedulingSet() != null;
			list.addAll(listInDependenceOrder(rootTask, action, avoidDuplicates));
		}
		return avoidDuplicates(list);
	}

	private List<Task> listInDependenceReverseOrder(Task rootTask, ApsSchedulingSet action,
			Map<String, Task> avoidDuplicates) {
		List<Task> list = new ArrayList<Task>();
		if (rootTask.getParentSchedulingSet() == action && !avoidDuplicates.containsKey(rootTask.getId())) {
			avoidDuplicates.put(rootTask.getId(), rootTask);
			list.add(rootTask);
			for (TaskEdge child : rootTask.getChildTasks()) {
				CollectionUtil.getInstance().AddCollection(list,
						listInDependenceReverseOrder(child.getProducerTask(), action, avoidDuplicates));
			}
		}
		return list;
	}

	public List<Task> listInDependenceReverseOrder(List<Task> rootTasks, ApsSchedulingSet action) {
		Map<String, Task> avoidDuplicates = new HashMap<String, Task>();
		List<Task> list = new ArrayList<Task>();
		for (Task rootTask : rootTasks) {
			list.addAll(listInDependenceReverseOrder(rootTask, action, avoidDuplicates));

		}
		return avoidDuplicates(list);
	}
}