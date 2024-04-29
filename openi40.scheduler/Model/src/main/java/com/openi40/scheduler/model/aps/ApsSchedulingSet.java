package com.openi40.scheduler.model.aps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.model.AbstractSchedulingEnvironmentNode;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class ApsSchedulingSet extends AbstractSchedulingEnvironmentNode {
	private static final String WORK_ORDERS = "WorkOrders";

	public static enum ApsSchedulingSetType {
		PRODUCTION_CONTROL, PLANNING
	}

	static Logger LOGGER = LoggerFactory.getLogger(ApsSchedulingSet.class);

	public ApsSchedulingSet(ApsData context) {
		super(context);
		this.code=UUID.randomUUID().toString();

	}

	protected ApsLogicDirection algorithmDirection = null;
	protected ApsSchedulingSetType schedulingSetType = ApsSchedulingSetType.PLANNING;
	protected String algorithmType;
	protected ApsLogicOptions options;
	protected boolean processed;
	protected boolean userReadOnly = false;
	private boolean scheduled = false;
	
	private List<WorkOrder> workOrders = createCleanChild(this, WORK_ORDERS, WorkOrder.class);

	public List<WorkOrder> getWorkOrders() {
		return new ArrayList(workOrders);
	}

	private void connectTask(Task task, WorkOrder wo, ApsSchedulingSet action) {
		if (task.getWorkOrder() == null || task.getWorkOrder() == wo) {
			task.setWorkOrder(wo);
			for (TaskEdge edge : task.getChildTasks()) {
				connectTask(edge.getProducerTask(), wo, action);
			}
			task.setParentSchedulingSet(action);
		}

	}

	public void addWorkOrder(WorkOrder wo) {
		wo.setParentSchedulingAction(this);
		workOrders.add(wo);
		if (wo.getRootTask() != null) {
			Task root = wo.getRootTask();
			connectTask(root, wo, this);

		}
	}

	public void removeWorkOrder(WorkOrder wo) {
		workOrders.remove(wo);
		wo.setParentSchedulingAction(null);
		if (wo.getRootTask() != null) {
			Task root = wo.getRootTask();
			connectTask(root, null, null);

		}
	}

	public void removeWorkOrders() {
		for (WorkOrder wo : workOrders) {
			wo.setParentSchedulingAction(null);
			if (wo.getRootTask() != null) {
				Task root = wo.getRootTask();
				connectTask(root, null, null);
			}
		}
		workOrders.clear();
	}

	@Override
	public void resetSchedulingData() {
		for (WorkOrder schedulable : getWorkOrders()) {
			schedulable.resetSchedulingData();
		}
	}

	/**
	 * Returns the list of root tasks being referenced as root tasks in work order
	 * being root of tree of work orders
	 * 
	 * @return
	 */
	public List<Task> getRootTasks() {
		List<Task> sTasks = new ArrayList<Task>();
		for (WorkOrder wo : getWorkOrders()) {
			if (wo.isRoot() && wo.getRootTask() != null) {
				sTasks.add(wo.getRootTask());
			}
		}
		return sTasks;
	}

	public boolean isUserReadOnly() {
		return this.userReadOnly || schedulingSetType == ApsSchedulingSetType.PRODUCTION_CONTROL;
	}

	public ApsLogicDirection getAlgorithmDirection() {
		return algorithmDirection;
	}

	public void setAlgorithmDirection(ApsLogicDirection algorithmDirection) {
		this.algorithmDirection = algorithmDirection;
	}

	public ApsSchedulingSetType getSchedulingSetType() {
		return schedulingSetType;
	}

	public void setSchedulingSetType(ApsSchedulingSetType schedulingSetType) {
		this.schedulingSetType = schedulingSetType;
	}

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

	public ApsLogicOptions getOptions() {
		return options;
	}

	public void setOptions(ApsLogicOptions options) {
		this.options = options;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public void setUserReadOnly(boolean userReadOnly) {
		this.userReadOnly = userReadOnly;
	}
}