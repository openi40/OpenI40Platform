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
package com.openi40.scheduler.client.model;

import java.util.Date;

public class ApsTimeSegmentDto extends ClientDto {
	protected Date StartDateTime = null;
	protected Date EndDateTime = null;
	public Date getStartDateTime() {
		return StartDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		StartDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return EndDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		EndDateTime = endDateTime;
	}
}
