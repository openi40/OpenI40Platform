package com.openi40.scheduler.model.time;

import java.util.Date;
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
public interface ITimeSegment {
	public Date getEndDateTime();

	public Date getStartDateTime();

	public boolean isContiguous(ITimeSegment segment);

	public boolean isInRange(Date datet);

	public boolean isInRange(ITimeSegment segment);

	public boolean isLowerLimited();

	public boolean isLowUpLimited();

	public boolean IsOverlapping(ITimeSegment segment);

	public boolean IsSameLimits(ITimeSegment segment);

	public boolean isUpperLimited();

	public boolean isValid();
}
