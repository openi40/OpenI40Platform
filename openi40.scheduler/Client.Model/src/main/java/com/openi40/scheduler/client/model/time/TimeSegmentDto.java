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
package com.openi40.scheduler.client.model.time;

import java.io.Serializable;
import java.util.Date;

import com.openi40.scheduler.client.model.ClientDto;

public class TimeSegmentDto extends ClientDto implements Serializable {

	public TimeSegmentDto() {
		
	}
	Date startDateTime=null,endDateTime=null;
	public String toString() {
		return "{"+getId()+",["+startDateTime+","+endDateTime+"]}";
	}
	public long getUtcStartDateTime() {
		return startDateTime!=null?startDateTime.getTime():0l;
	}
	public long getUtcEndDateTime() {
		return endDateTime!=null?endDateTime.getTime():0l;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	
}
