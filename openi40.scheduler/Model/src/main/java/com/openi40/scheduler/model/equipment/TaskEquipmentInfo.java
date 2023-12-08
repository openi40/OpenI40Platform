package com.openi40.scheduler.model.equipment;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.model.AbstractApsObjectReferencingMetaInfo;
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

public class TaskEquipmentInfo extends AbstractApsObjectReferencingMetaInfo<TaskEquipmentModelInfo> {
	private TaskPreparationPlanned preparation;
	private String setupGroupCode=null;
	private TaskExecutionPlanned execution;
	private TaskProcessInfo taskInfo = null;
	public TaskEquipmentInfo(ApsData context) {
		super(context);
		setTaskInfo(new TaskProcessInfo(context));
	}

	@Override
	public void resetSchedulingData() {
		if (getPreparation() != null) {
			getPreparation().resetSchedulingData();
		}

		if (getExecution() != null) {
			getExecution().resetSchedulingData();
		}
	}
	@Override
	public void setMetaInfo(TaskEquipmentModelInfo metaInfo) {
		super.setMetaInfo(metaInfo);
		this.setupGroupCode=metaInfo!=null?metaInfo.getSetupGroupCode():null;
	}
	@Override
	public ICloneable cleanClone() throws CloneNotSupportedException {
		TaskEquipmentInfo cp = new TaskEquipmentInfo(getContext());
		cp.setMetaInfo(this.getMetaInfo());
		cp.setupGroupCode=this.setupGroupCode;
		cp.setPreparation((TaskPreparationPlanned) getPreparation().cleanClone());
		cp.setExecution((TaskExecutionPlanned) getExecution().cleanClone());
		if (this.getTaskInfo() != null) {
			cp.setTaskInfo((TaskProcessInfo) this.getTaskInfo().cleanClone());
		}
		return cp;
	}

	public TaskPreparationPlanned getPreparation() {
		return preparation;
	}

	public void setPreparation(TaskPreparationPlanned preparation) {
		this.preparation = preparation;
	}

	public String getSetupGroupCode() {
		return setupGroupCode;
	}

	public void setSetupGroupCode(String setupGroupCode) {
		this.setupGroupCode = setupGroupCode;
	}

	public TaskExecutionPlanned getExecution() {
		return execution;
	}

	public void setExecution(TaskExecutionPlanned execution) {
		this.execution = execution;
	}

	public TaskProcessInfo getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(TaskProcessInfo taskInfo) {
		this.taskInfo = taskInfo;
	}


}