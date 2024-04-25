package com.openi40.scheduler.model.equipment;

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
 * @author architectures@openi40.org
 *
 */

public class TaskEquipmentModelInfo extends AbstractApsObject implements IMetaInfo,IApsResourcesDependencyTreeObject {
	public TaskEquipmentModelInfo(ApsData context) {
		super(context);
		setPreparationModel(new TaskPreparationModel(context));
		setExecutionModel(new TaskExecutionModel(context));
		setTaskMetaInfo(new TaskProcessMetaInfo(context));
	}
	private String setupGroupCode=null;
	private TaskPreparationModel preparationModel;
	private TaskExecutionModel executionModel;
	private TaskProcessMetaInfo taskMetaInfo = null;
	public String getSetupGroupCode() {
		return setupGroupCode;
	}
	public void setSetupGroupCode(String setupGroupCode) {
		this.setupGroupCode = setupGroupCode;
	}
	public TaskPreparationModel getPreparationModel() {
		return preparationModel;
	}
	public void setPreparationModel(TaskPreparationModel preparationModel) {
		this.preparationModel = preparationModel;
	}
	public TaskExecutionModel getExecutionModel() {
		return executionModel;
	}
	public void setExecutionModel(TaskExecutionModel executionModel) {
		this.executionModel = executionModel;
	}
	public TaskProcessMetaInfo getTaskMetaInfo() {
		return taskMetaInfo;
	}
	public void setTaskMetaInfo(TaskProcessMetaInfo taskMetaInfo) {
		this.taskMetaInfo = taskMetaInfo;
	}
	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		ResourceDepsItemMetaInfo info=new ResourceDepsItemMetaInfo(this);
		
		return info;
	}
	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		
		return aggregateChilds(List.of(preparationModel),List.of(executionModel));
	}
}