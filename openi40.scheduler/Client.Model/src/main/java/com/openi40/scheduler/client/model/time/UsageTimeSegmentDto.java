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
package com.openi40.scheduler.client.model.time;

import java.io.Serializable;


public class UsageTimeSegmentDto implements Serializable {
	double usageQty = 0.0;
	long utcStartDateTime = 0l;
	long utcEndDateTime = 0l;
	long reservedTime = 0l;
	String resourceCode=null;
	public double getUsageQty() {
		return usageQty;
	}
	public void setUsageQty(double usageQty) {
		this.usageQty = usageQty;
	}
	public long getUtcStartDateTime() {
		return utcStartDateTime;
	}
	public void setUtcStartDateTime(long utcStartDateTime) {
		this.utcStartDateTime = utcStartDateTime;
	}
	public long getUtcEndDateTime() {
		return utcEndDateTime;
	}
	public void setUtcEndDateTime(long utcEndDateTime) {
		this.utcEndDateTime = utcEndDateTime;
	}
	public long getReservedTime() {
		return reservedTime;
	}
	public void setReservedTime(long reservedTime) {
		this.reservedTime = reservedTime;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
}
