package com.openi40.scheduler.iomessages;

import java.io.Serializable;


public abstract class AbstractMachineStatusIOMessage extends AbstractBaseIOMessage  implements Serializable{
	private String machineCode=null;

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
}
