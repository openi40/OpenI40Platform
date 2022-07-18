package com.openi40.scheduler.model.orders;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class Pegging extends AbstractApsObject // implements IObjectWithModel<SchedulablePegging>
{
	private WorkOrder consumer = null;
	private WorkOrder supplier = null;
	private TaskEdge edge = null;

	public Pegging(ApsData context, WorkOrder consumerWorkOrder, WorkOrder producerWorkOrder) {
		super(context);
		setConsumer(consumerWorkOrder);
		setSupplier(producerWorkOrder);

	}

	public Pegging(ApsData context) {
		super(context);
	}

	public WorkOrder getConsumer() {
		return consumer;
	}

	public void setConsumer(WorkOrder value) {
		consumer = value;
	}

	public WorkOrder getSupplier() {
		return supplier;
	}

	public void setSupplier(WorkOrder value) {
		supplier = value;
	}

	public TaskEdge getEdge() {
		return edge;
	}

	public void setEdge(TaskEdge value) {
		edge = value;
	}

	private double totalQty = 0.0;

	public double getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(double value) {
		totalQty = value;
	}

	private double transferredQty = 0.0;

	public double getTransferredQty() {
		return transferredQty;
	}

	public void setTransferredQty(double value) {
		transferredQty = value;
	}

	public double getResidualQty() {
		return getTotalQty() >= getTransferredQty() ? getTotalQty() - getTransferredQty() : 0.0;
	}

	protected void finalize() throws Throwable {
		setConsumer(null);
		setSupplier(null);

		setEdge(null);

	}
}