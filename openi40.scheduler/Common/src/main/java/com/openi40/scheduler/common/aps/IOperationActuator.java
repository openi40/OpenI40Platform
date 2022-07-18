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
 */
public interface IOperationActuator<IReversibleOperationType extends IOperation> {
	public void apply(IReversibleOperationType operation);

	public void reverse(IReversibleOperationType operation);

	public Class<IReversibleOperationType> getRootOperationType();
}
