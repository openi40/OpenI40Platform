package com.openi40.scheduler.model.messages;

import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@Data
public class TaskContextMessage extends AbstractBaseMessage implements IMachineRelatedMessage, ITaskRelatedMessage {
	protected String taskCode = null;
	protected String machineCode = null;

	public TaskContextMessage(ApsData context) {
		super(context);

	}

}