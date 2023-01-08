package com.openi40.scheduler.iomessages;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaskProductionUpdateIOMessage extends AbstractBaseTaskIOMessage  implements Serializable {
	protected double produced = 0.0;
	protected boolean incremental = false;
}
