package com.openi40.scheduler.iomessages;

import java.io.Serializable;

public abstract class AbstractBaseTaskIOMessage extends AbstractBaseIOMessage implements Serializable {
	protected String taskCode = null;
	protected String machineCode = null;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
}
