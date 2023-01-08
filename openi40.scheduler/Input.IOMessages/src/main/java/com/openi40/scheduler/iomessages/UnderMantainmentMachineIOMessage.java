package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UnderMantainmentMachineIOMessage extends AbstractMachineStatusIOMessage  implements Serializable{
	protected Date availabilityDateTime = null;
}
