package com.openi40.scheduler.engine.timesheet;

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
public class TimeSegmentEvaluationResult {
	public static enum TimeSegmentEvaluationResultType {
		SUCCESSFULLY_EVALUATED, LOWER_BOUND_LIMIT_REACH, UPPER_BOUND_LIMIT_REACH, LOWER_UPPER_BOUND_LIMIT_REACH,LOWER_BOUND_OUT_OF_SCHEDULING_WINDOW,HIGHER_BOUND_OUT_OF_SCHEDULING_WINDOW,TIMESEGMENT_REQUEST_WITH_UNMANAGED_MARGINS
	}
	private TimeSegmentEvaluationResultType resultType=null;
	private TimeSegment result=null;
	public TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType rType) {
		this(rType,null);
	}
	public TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType rType,TimeSegment ts) {
		this.resultType=rType;
		this.result=ts;
	}
	public TimeSegmentEvaluationResultType getResultType() {
		return resultType;
	}
	public TimeSegment getResult() {
		return result;
	}
}
