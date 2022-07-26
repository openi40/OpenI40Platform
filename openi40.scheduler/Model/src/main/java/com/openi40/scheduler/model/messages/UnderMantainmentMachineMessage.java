package com.openi40.scheduler.model.messages;

import java.util.Date;

import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;

@Data
public class UnderMantainmentMachineMessage extends AbstractMachineStatusMessage {
	protected Date availabilityDateTime = null;

	public UnderMantainmentMachineMessage(ApsData context) {
		super(context);

	}

}
