package com.openi40.scheduler.model.tasks;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.cycle.OperationEdgeModel;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.TimeSegmentType;
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
public class TaskEdge extends AbstractApsObject implements IReferencingMetaInfo<OperationEdgeModel> {

	public TaskEdge(ApsData context) {
		super(context);
	}

	public TaskEdge(ApsData context, OperationEdgeModel edgeModel, Task producerTask, Task consumerTask) {
		super(context);
		setMetaInfo(edgeModel);
		setProducerTask(producerTask);
		setConsumerTask(consumerTask);
		setAlignmentType(edgeModel.getAlignmentType());
		setOffsetMillisecs(edgeModel.getOffsetMillisecs());
		setTimeRangeType(edgeModel.getTimeRangeType());

	}

	public TaskEdge(ApsData context, OperationEdgeModel edgeModel, Task producerTask, Task consumerTask, Pegging pegging) {
		super(context);
		setMetaInfo(edgeModel);
		setProducerTask(producerTask);
		setConsumerTask(consumerTask);
		setAlignmentType(edgeModel.getAlignmentType());
		setOffsetMillisecs(edgeModel.getOffsetMillisecs());
		setRappresentedPegging(pegging);
		setTimeRangeType(edgeModel.getTimeRangeType());
		pegging.setEdge(this);
	}
	
	public boolean isPeggingEdge() {
		return this.rappresentedPegging!=null;
	}
	private Task consumerTask = null;
	private BatchingInfo consumingBatchInfo = null;
	public final Task getConsumerTask() {
		return consumerTask;
	}

	public final void setConsumerTask(Task value) {
		consumerTask = value;
	}

	private Task producerTask = null;

	public final Task getProducerTask() {
		return producerTask;
	}

	public final void setProducerTask(Task value) {
		producerTask = value;
	}

	private OperationEdgeModel metaInfo = null;
	private BomItemModel bomItemModel=null;

	public final OperationEdgeModel getMetaInfo() {
		return metaInfo;
	}

	public final void setMetaInfo(OperationEdgeModel value) {
		metaInfo = value;
	}

	private PeriodsAlignmentType alignmentType = PeriodsAlignmentType.START_AFTER_END;

	public final PeriodsAlignmentType getAlignmentType() {
		return alignmentType;
	}

	public final void setAlignmentType(PeriodsAlignmentType value) {
		alignmentType = value;
	}

	private TimeSegmentType timeSegmentType = com.openi40.scheduler.model.time.TimeSegmentType.WORK_TIME;

	public final TimeSegmentType getTimeRangeType() {
		return timeSegmentType;
	}

	public final void setTimeRangeType(TimeSegmentType value) {
		timeSegmentType = value;
	}

	private long offsetMillisecs = 0L;

	public final long getOffsetMillisecs() {
		return offsetMillisecs;
	}

	public final void setOffsetMillisecs(long value) {
		offsetMillisecs = value;
	}

	private Pegging rappresentedPegging = null;

	public final Pegging getRappresentedPegging() {
		return rappresentedPegging;
	}

	public final void setRappresentedPegging(Pegging value) {
		rappresentedPegging = value;
	}

	protected void finalize() throws Throwable {
		setConsumerTask(null);
		setProducerTask(null);
		setRappresentedPegging(null);
	}

	public BatchingInfo getConsumingBatchInfo() {
		return consumingBatchInfo;
	}

	public void setConsumingBatchInfo(BatchingInfo consumingBatchInfo) {
		this.consumingBatchInfo = consumingBatchInfo;
	}

	public BomItemModel getBomItemModel() {
		return bomItemModel;
	}

	public void setBomItemModel(BomItemModel bomItemModel) {
		this.bomItemModel = bomItemModel;
	}
}