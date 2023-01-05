package com.openi40.scheduler.model.messages;

import com.openi40.scheduler.model.aps.ApsData;

public class OutOfOrderMachineMessage extends AbstractMachineStatusMessage {

	public OutOfOrderMachineMessage(ApsData context) {
		super(context);
		
	}

}
