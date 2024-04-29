package com.openi40.scheduler.model.planning;

import java.util.ArrayList;
import java.util.List;

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

public class PlanGraphItem {
	private Task task;
	private boolean missingTask = false;
	private boolean scheduled = false;
	private PlanGraphItem parentItem = null;
	private List<PlanGraphItem> childs = new ArrayList<PlanGraphItem>();

	private List<Plan> plans = new ArrayList<Plan>();

	public boolean isRoot() {
		return getParentItem() == null;
	}

	public  boolean isLeaf() {
		return getChilds().isEmpty();
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isMissingTask() {
		return missingTask;
	}

	public void setMissingTask(boolean missingTask) {
		this.missingTask = missingTask;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public PlanGraphItem getParentItem() {
		return parentItem;
	}

	public void setParentItem(PlanGraphItem parentItem) {
		this.parentItem = parentItem;
	}

	public List<PlanGraphItem> getChilds() {
		return childs;
	}

	public void setChilds(List<PlanGraphItem> childs) {
		this.childs = childs;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

}