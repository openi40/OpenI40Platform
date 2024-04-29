package com.openi40.scheduler.model.tasks;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.ApsModelException;
import com.openi40.scheduler.model.TreeVisitType;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task.CollectorTreeVisitor;

import ch.qos.logback.core.joran.action.Action;
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
public class TaskVisitUtil {

	private TaskVisitUtil() {

	}

	public final static TaskVisitUtil Instance = new TaskVisitUtil();

	public void doFullTasksTreeVisit(Task task, ITasksVisitor visitor) {
		doFullTasksTreeVisit(task, visitor, TreeVisitType.NODES_FIRST);
	}
	public List<Task> getProcessedTasksList(ApsData data) {
		List<Task> tasks=new ArrayList<>();
		data.getSchedulingSets().forEach((action)->{
			action.getWorkOrders().forEach((wo)->{
				tasks.addAll(getTasksTreeList(wo));
			});
		});
		return tasks;
	}

	public void doFullTasksTreeVisit(Task task, ITasksVisitor visitor, TreeVisitType visitType) {
		doTasksTreeVisit(task, null, visitor, visitType);
	}

	public void doTasksTreeVisit(WorkOrder ownerOrder, ITasksVisitor visitor) {
		doTasksTreeVisit(ownerOrder.getRootTask(), ownerOrder, visitor, TreeVisitType.NODES_FIRST);
	}

	public void doTasksTreeVisit(Task task, WorkOrder ownerOrder, ITasksVisitor visitor, TreeVisitType visitType) {
		boolean sameHierarchy = ownerOrder == null || ownerOrder == task.getWorkOrder() || task.getWorkOrder() == null
				|| (task.getWorkOrder() != null && ownerOrder != null && task.getWorkOrder().equals(ownerOrder));
		if (sameHierarchy) {
			if (visitType == TreeVisitType.NODES_FIRST) {
				visitor.onNode(task);
			}
			for (TaskEdge edge : task.getChildTasks()) {
				if (visitType == TreeVisitType.NODES_FIRST) {
					visitor.onEdge(edge);
				}
				doTasksTreeVisit(edge.getProducerTask(), ownerOrder, visitor, visitType);
				if (visitType == TreeVisitType.LEAFS_FIRST) {
					visitor.onEdge(edge);
				}
			}
			if (visitType == TreeVisitType.LEAFS_FIRST) {
				visitor.onNode(task);
			}
		}
	}

	public void doTasksTreeVisitOnSameOrder(Task task, ITasksVisitor visitor) {
		doTasksTreeVisitOnSameOrder(task, visitor, TreeVisitType.NODES_FIRST);
	}

	public void doTasksTreeVisitOnSameOrder(Task task, ITasksVisitor visitor, TreeVisitType visitType) {
		if (task.getWorkOrder() == null) {
			throw new ApsModelException(
					"The method GetTasksTreeOnSameOrder is callable only on tasks being root for SchedulableWorkOrder");
		}
		doTasksTreeVisit(task, task.getWorkOrder(), visitor, visitType);
	}

	public java.util.List<Task> getTasksTree(Task task) {
		return getTasksTree(task, TreeVisitType.NODES_FIRST);
	}

	public List<Task> getTasksTree(Task task, TreeVisitType visitType) {
		CollectorTreeVisitor treeVisitor = new CollectorTreeVisitor();
		doFullTasksTreeVisit(task, treeVisitor, visitType);
		return treeVisitor.getVisited();
	}

	public java.util.List<Task> getTasksTreeList(WorkOrder ownerOrder) {
		return getTasksTreeList(ownerOrder, TreeVisitType.NODES_FIRST);
	}

	public List<Task> getTasksTreeList(WorkOrder ownerOrder, TreeVisitType visitType) {
		CollectorTreeVisitor treeVisitor = new CollectorTreeVisitor();
		doTasksTreeVisit(ownerOrder.getRootTask(), ownerOrder, treeVisitor, visitType);
		return treeVisitor.getVisited();
	}

	public java.util.List<Task> getTasksTreeOnSameOrder(Task task) {
		return getTasksTreeOnSameOrder(task, TreeVisitType.NODES_FIRST);
	}

	public java.util.List<Task> getTasksTreeList(Task task, WorkOrder parentWorkOrder) {
		List<Task> tasks = new ArrayList<>();
		doTasksTreeVisit(task, parentWorkOrder, new ITasksVisitor() {

			@Override
			public void onNode(Task task) {
				tasks.add(task);

			}

			@Override
			public void onEdge(TaskEdge edge) {

			}
		}, TreeVisitType.NODES_FIRST);
		return tasks;
	}

	public List<Task> getTasksTreeOnSameOrder(Task task, TreeVisitType visitType) {
		if (task.getWorkOrder() == null) {
			throw new ApsModelException(
					"The method GetTasksTreeOnSameOrder is callable only on tasks being root for SchedulableWorkOrder");
		}
		CollectorTreeVisitor treeVisitor = new CollectorTreeVisitor();
		doTasksTreeVisit(task, task.getWorkOrder(), treeVisitor, visitType);
		return treeVisitor.getVisited();
	}

}
