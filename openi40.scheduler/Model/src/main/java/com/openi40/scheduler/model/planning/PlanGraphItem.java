package com.openi40.scheduler.model.planning;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.tasks.Task;

import lombok.Data;
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
@Data
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

}