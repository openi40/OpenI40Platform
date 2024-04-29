package com.openi40.scheduler.engine.equipment.configuration;

import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IWorkResourceConfigurator.class, entityClass = TaskExecutionModel.ResourceModelInfo.class)
public class WorkResourceConfiguratorImpl extends AbstractWorkResourceConfigurator<Machine, MachinesGroup, TaskExecutionModel.ResourceModelInfo> implements IWorkResourceConfigurator {
	public WorkResourceConfiguratorImpl() {
		super(TaskExecutionModel.ResourceModelInfo.class);
	}

}