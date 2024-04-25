package com.openi40.scheduler.iomessages;

import java.io.Serializable;

public class TaskProductionUpdateIOMessage extends AbstractBaseTaskIOMessage  implements Serializable {
	protected double produced = 0.0;
	protected boolean incremental = false;
	public double getProduced() {
		return produced;
	}
	public void setProduced(double produced) {
		this.produced = produced;
	}
	public boolean isIncremental() {
		return incremental;
	}
	public void setIncremental(boolean incremental) {
		this.incremental = incremental;
	}
}
