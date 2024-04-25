package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.model.resourcesdeps.ResourceDepsTreeMetaInfo;
import com.openi40.scheduler.model.tasks.Task;

public class TasksDependencySplitter {
	static Logger LOGGER=LoggerFactory.getLogger(TasksDependencySplitter.class);
	private TasksDependencySplitter() {

	}

	static class TaskDependencyTree {
		Task task = null;
		TreeMap<String, Boolean> resources = new TreeMap<String, Boolean>();
		ResourceDepsTreeMetaInfo treeItem;

		TaskDependencyTree(Task task) {
			this.task = task;
			this.treeItem = task.getResourceTreeMetaInfo();
			this.fillResources(treeItem);
		}

		private void fillResources(ResourceDepsTreeMetaInfo treeItem) {
			if (treeItem.getItem() != null) {
				if (treeItem.getItem().isResource() || treeItem.getItem().isResourceGroup()) {
					resources.put(treeItem.getItem().getResourceUniqueCode(), true);
				}
			}
			List<ResourceDepsTreeMetaInfo> childs = treeItem.getChilds();
			for (ResourceDepsTreeMetaInfo resourceDepsTreeMetaInfo : childs) {
				fillResources(resourceDepsTreeMetaInfo);
			}
		}

		boolean hasCommonResources(TaskDependencyTree t) {
			boolean hasResources = false;
			Set<String> key = t.resources.keySet();
			for (String k : key) {
				if (resources.containsKey(k)) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Task "+t.task.getCode()+" and "+task.getCode()+" have at least resource "+k+" in common");
					}
					return true;
				}
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Task "+t.task.getCode()+" and "+task.getCode()+" have no resources in common");
			}
			return hasResources;
		}
	}

	public List<List<Task>> splitByDependencies(List<Task> tasks) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin splitByDependencies(...)");
		}
		List<TaskDependencyTree> dependencyInfos = new ArrayList<TasksDependencySplitter.TaskDependencyTree>();
		for (Task task : tasks) {
			TaskDependencyTree tdt = new TaskDependencyTree(task);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Task "+task.getCode()+" resources-->"+tdt.resources.keySet());
			}
			dependencyInfos.add(tdt);
		}
		List<List<TaskDependencyTree>> groups = new ArrayList<List<TaskDependencyTree>>();
		List<List<Task>> out = new ArrayList<List<Task>>();
		while (dependencyInfos.size() > 0) {
			TaskDependencyTree dt = dependencyInfos.get(0);
			dependencyInfos.remove(0);
			List<TaskDependencyTree> sameGroup = new ArrayList<TasksDependencySplitter.TaskDependencyTree>();
			sameGroup.add(dt);
			List<Task> tlist=new ArrayList<Task>();
			tlist.add(dt.task);
			for (int i = 0; i < dependencyInfos.size(); i++) {
				TaskDependencyTree dt1 = dependencyInfos.get(i);
				boolean isInThisGroup=dt1.hasCommonResources(dt);
				if (!isInThisGroup) {
					for(int j=0;j<sameGroup.size() && !isInThisGroup;j++) {
						TaskDependencyTree dt2=sameGroup.get(j);
						if (dt2!=dt1 && dt!=dt2) {
							isInThisGroup=isInThisGroup || dt2.hasCommonResources(dt1);
						}
						
					}
				}
				if (isInThisGroup) {
					sameGroup.add(dt1);
					tlist.add(dt1.task);
					dependencyInfos.remove(i);
					i--;
				}
			}
			groups.add(sameGroup);
			out.add(tlist);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("****************************************************");
			LOGGER.debug("Tasks splitting results:");
			int idx=1;
			for (List<Task> list : out) {
				StringBuffer buffer=new StringBuffer();
				for(int j=0;j<list.size();j++) {
					buffer.append(list.get(j).getCode());
					if (j<list.size()-1) {
						buffer.append(" , ");
					}
				}
				LOGGER.debug("Group of tasks #"+idx+ ": "+buffer.toString());
				idx++;
			}
			
			LOGGER.debug("****************************************************");
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End splitByDependencies(...) ");
		}
		return out;
	}

	final static TasksDependencySplitter Instance = new TasksDependencySplitter();
}
