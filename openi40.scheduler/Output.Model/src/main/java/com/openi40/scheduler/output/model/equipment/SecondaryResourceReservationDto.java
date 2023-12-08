package com.openi40.scheduler.output.model.equipment;

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

import com.openi40.scheduler.output.model.OutputDto;

public class SecondaryResourceReservationDto extends OutputDto {
	protected String secondaryResourceCode = null, taskCode = null;
	protected Date startReservation = null;
	protected Date endReservation = null;
	protected String reservationType = null;
	public SecondaryResourceReservationDto() {
		
	}
	public String getSecondaryResourceCode() {
		return secondaryResourceCode;
	}
	public void setSecondaryResourceCode(String secondaryResourceCode) {
		this.secondaryResourceCode = secondaryResourceCode;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public Date getStartReservation() {
		return startReservation;
	}
	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}
	public Date getEndReservation() {
		return endReservation;
	}
	public void setEndReservation(Date endReservation) {
		this.endReservation = endReservation;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

}
