package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.openi40.scheduler.model.resourcesdeps.ResourceDepsTreeMetaInfo;
import com.openi40.scheduler.model.tasks.Task;

public class TasksDependencySplitter {
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
				if (resources.containsKey(k))
					return true;
			}
			return hasResources;
		}
	}

	public List<List<Task>> splitByDependencies(List<Task> tasks) {

		List<TaskDependencyTree> dependencyInfos = new ArrayList<TasksDependencySplitter.TaskDependencyTree>();
		for (Task task : tasks) {
			dependencyInfos.add(new TaskDependencyTree(task));
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
				if (dt1.hasCommonResources(dt)) {
					sameGroup.add(dt1);
					tlist.add(dt1.task);
					dependencyInfos.remove(i);
					i--;
				}
			}
			groups.add(sameGroup);
			out.add(tlist);
		}
		
		
		return out;
	}

	final static TasksDependencySplitter Instance = new TasksDependencySplitter();
}
