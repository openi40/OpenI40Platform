package com.openi40.scheduler.model.messages;

import com.openi40.scheduler.model.aps.ApsData;
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

public abstract class AbstractMachineStatusMessage extends AbstractBaseMessage implements IMachineRelatedMessage {
	protected String machineCode = null;
	public AbstractMachineStatusMessage(ApsData context) {
		super(context);
		
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

}
