package com.openi40.scheduler.model.messages.handling;

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

public class ApsMessageManagementResponse {
	protected boolean reschedule=false;
	public ApsMessageManagementResponse() {
		
	}
	public boolean isReschedule() {
		return reschedule;
	}
	public void setReschedule(boolean reschedule) {
		this.reschedule = reschedule;
	}

}
