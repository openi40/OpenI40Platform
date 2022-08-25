package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class TaskPreparationModel extends AbstractApsObject implements IMetaInfo {

	public TaskPreparationModel(ApsData context) {
		super(context);
	}

	private TaskPreparationUseModel<Machine, MachinesGroup> Machine = new TaskPreparationUseModel<Machine, MachinesGroup>(Machine.class, MachinesGroup.class);

	public  TaskPreparationUseModel<Machine, MachinesGroup> getResource() {
		return Machine;
	}

	public  void setResource(TaskPreparationUseModel<Machine, MachinesGroup> value) {
		Machine = value;
	}

	private List<TaskPreparationUseModel<Resource, ResourceGroup>> SecondaryResources = new ArrayList<TaskPreparationUseModel<Resource, ResourceGroup>>();

	public  List<TaskPreparationUseModel<Resource, ResourceGroup>> getSecondaryResources() {
		return SecondaryResources;
	}

	public  void setSecondaryResources(List<TaskPreparationUseModel<Resource, ResourceGroup>> value) {
		SecondaryResources = value;
	}

	
}