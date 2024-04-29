package com.openi40.scheduler.model.messages;

import com.openi40.scheduler.model.aps.ApsData;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class TaskProductionUpdateMessage extends TaskContextMessage {
	protected double produced = 0.0;
	protected boolean incremental = false;

	public TaskProductionUpdateMessage(ApsData context) {
		super(context);

	}

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
