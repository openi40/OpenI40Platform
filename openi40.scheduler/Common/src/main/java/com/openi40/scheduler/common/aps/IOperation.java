package com.openi40.scheduler.common.aps;

import java.io.Serializable;

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
public interface IOperation extends Serializable {
	public void apply(IOperationActuatorFactory factory);

	public void reverse(IOperationActuatorFactory factory);

	public boolean isApplied();

	public boolean isReversed();

	public default void discardOperation() {

	}
}