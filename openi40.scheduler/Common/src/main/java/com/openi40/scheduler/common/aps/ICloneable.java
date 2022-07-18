package com.openi40.scheduler.common.aps;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 *
 * Interface to be implemented to express the functionality to obtain a copy
 * without scheduling informations
 */
public interface ICloneable {
	/**
	 * Clones the content of the actual object without references and data affected
	 * from scheduling algorithm
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	ICloneable cleanClone() throws CloneNotSupportedException;
}