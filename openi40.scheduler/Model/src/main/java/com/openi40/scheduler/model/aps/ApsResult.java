package com.openi40.scheduler.model.aps;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@Data
public class ApsResult<T> {
	protected T content = null;
	protected boolean success = false;
	protected List<ApsMessage> messages = new ArrayList<ApsMessage>();

	public ApsResult() {
	}

	public ApsResult(T c) {
		this.content = c;
	}

	public ApsResult(T c, List<ApsMessage> m) {
		this.content = c;
		this.messages = m;
	}

	public ApsResult(T c, List<ApsMessage> m, boolean s) {
		this.content = c;
		this.messages = m;
		this.success = s;
	}
}
