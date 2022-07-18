package com.openi40.scheduler.model.material;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.tasks.Task;
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
public class ProductionSupply extends AbstractSupply implements IReferencingMetaInfo<ItemProducedMetaInfo> {
	public ProductionSupply(ApsData context) {
		super(context);
	}

	public ProductionSupply(ApsData context, Task ownerTask) {
		super(context);
		setOwnerTask(ownerTask);
	}

	String orderCode = null;
	String sequenceCode = null;
	String workOrderCode=null;
	String taskCode=null;
	String productionTaskId = null;
	BatchingInfo producingBatchInfo=null;
	public ProductionSupply(ApsData context, Task ownerTask, ItemProducedMetaInfo model) {
		super(context);
		setOwnerTask(ownerTask);
		setMetaInfo(model);
	}

	private Task OwnerTask;

	public Task getOwnerTask() {
		return OwnerTask;
	}

	public void setOwnerTask(Task value) {
		OwnerTask = value;
	}

	private ItemProducedMetaInfo MetaInfo;

	public ItemProducedMetaInfo getMetaInfo() {
		return MetaInfo;
	}

	public void setMetaInfo(ItemProducedMetaInfo value) {
		MetaInfo = value;
	}

	@Override
	public double getQtyTotal() {

		return OwnerTask != null ? OwnerTask.getQtyTotal() : 0.0;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getProductionTaskId() {
		return productionTaskId;
	}

	public void setProductionTaskId(String productionTaskId) {
		this.productionTaskId = productionTaskId;
	}

	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public BatchingInfo getProducingBatchInfo() {
		return producingBatchInfo;
	}

	public void setProducingBatchInfo(BatchingInfo producingBatchInfo) {
		this.producingBatchInfo = producingBatchInfo;
	}

}