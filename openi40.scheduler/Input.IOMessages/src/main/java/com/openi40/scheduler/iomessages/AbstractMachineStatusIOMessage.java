package com.openi40.scheduler.iomessages;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class AbstractMachineStatusIOMessage extends AbstractBaseIOMessage  implements Serializable{
	private String machineCode=null;
}
