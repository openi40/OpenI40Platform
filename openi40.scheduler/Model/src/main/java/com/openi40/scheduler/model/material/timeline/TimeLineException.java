package com.openi40.scheduler.model.material.timeline;
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
public class TimeLineException extends Exception {
	public TimeLineException(String s) {
		super(s);
	}

	public TimeLineException(String s, Throwable t) {
		super(s, t);
	}
}
