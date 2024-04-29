package com.openi40.scheduler.engine;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 * Main Engine exception
 */
public class OpenI40Exception extends RuntimeException {
	public OpenI40Exception(String msg) {
		super(msg);
	}

	public OpenI40Exception(String string, Throwable t) {
		super(string, t);
	}
}