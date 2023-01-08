package com.openi40.scheduler.iomessages;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class AbstractBaseTaskIOMessage extends AbstractBaseIOMessage  implements Serializable{
	protected String taskCode = null;
	protected String machineCode = null;
}
