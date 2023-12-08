package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.Date;

public class UnderMantainmentMachineIOMessage extends AbstractMachineStatusIOMessage  implements Serializable{
	protected Date availabilityDateTime = null;

	public Date getAvailabilityDateTime() {
		return availabilityDateTime;
	}

	public void setAvailabilityDateTime(Date availabilityDateTime) {
		this.availabilityDateTime = availabilityDateTime;
	}
}
