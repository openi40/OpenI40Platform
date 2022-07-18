package com.openi40.scheduler.model.planning;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.time.TimeSegment;

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
public class PlanChoice {
	public PlanChoice(Plan parentPlan, TimeSegment setupTimeRange, TimeSegment workTimeRange, List<IOperation> reversibleOperations) {
		setSetup(setupTimeRange);
		setWork(workTimeRange);
		operations = reversibleOperations;
		parent = parentPlan;
	}
	private Plan parent;
	private TimeSegment setup;
	private TimeSegment work;
	private List<IOperation> operations = new ArrayList<IOperation>();
	public void choose() {
		parent.setChoosed(this);

	}

}