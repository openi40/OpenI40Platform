package com.openi40.scheduler.model.planning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.model.time.TimeSegment;
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

public class PlanChoice implements Serializable{
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
	public Plan getParent() {
		return parent;
	}
	public void setParent(Plan parent) {
		this.parent = parent;
	}
	public TimeSegment getSetup() {
		return setup;
	}
	public void setSetup(TimeSegment setup) {
		this.setup = setup;
	}
	public TimeSegment getWork() {
		return work;
	}
	public void setWork(TimeSegment work) {
		this.work = work;
	}
	public List<IOperation> getOperations() {
		return operations;
	}
	public void setOperations(List<IOperation> operations) {
		this.operations = operations;
	}

}