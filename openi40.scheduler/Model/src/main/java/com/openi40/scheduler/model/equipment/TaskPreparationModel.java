package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;
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
public class TaskPreparationModel extends AbstractApsObject implements IMetaInfo ,IApsResourcesDependencyTreeObject{

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

	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		ResourceDepsItemMetaInfo info=new ResourceDepsItemMetaInfo(this);
		return info;
	}

	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		
		return aggregateChilds(List.of(Machine),new ArrayList<IApsResourcesDependencyTreeObject>(SecondaryResources));
	}

	
}