package com.openi40.scheduler.model.cycle;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.TimeSegmentType;
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
public class OperationEdgeModel extends AbstractApsObject implements IMetaInfo {

	public OperationEdgeModel(ApsData context) {
		super(context);
	}
	public BatchingInfo consumingBatchInfo = null;
	public OperationModel parent;

	public OperationModel child;

	private PeriodsAlignmentType AlignmentType = PeriodsAlignmentType.START_AFTER_END;

	public final PeriodsAlignmentType getAlignmentType() {
		return AlignmentType;
	}

	public final void setAlignmentType(PeriodsAlignmentType value) {
		AlignmentType = value;
	}

	private TimeSegmentType TimeSegmentType = com.openi40.scheduler.model.time.TimeSegmentType.WORK_TIME;

	public final TimeSegmentType getTimeRangeType() {
		return TimeSegmentType;
	}

	public final void setTimeRangeType(TimeSegmentType value) {
		TimeSegmentType = value;
	}

	private long OffsetMillisecs = 0L;

	public final long getOffsetMillisecs() {
		return OffsetMillisecs;
	}

	public final void setOffsetMillisecs(long value) {
		OffsetMillisecs = value;
	}

	protected void finalize() throws Throwable {
		parent = null;
		child = null;

	}

	public BatchingInfo getConsumingBatchInfo() {
		return consumingBatchInfo;
	}

	public void setConsumingBatchInfo(BatchingInfo consumingBatchInfo) {
		this.consumingBatchInfo = consumingBatchInfo;
	}
}