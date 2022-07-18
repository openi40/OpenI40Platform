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
public class TaskEquipmentModelOptions extends AbstractApsObject implements IMetaInfo {

	public TaskEquipmentModelOptions(ApsData context) {
		super(context);
	}



	private List<TaskEquipmentModelInfo> EquipmentModels = new ArrayList<TaskEquipmentModelInfo>();

	public final List<TaskEquipmentModelInfo> getEquipmentModels() {
		return EquipmentModels;
	}

	public final void setEquipmentModels(List<TaskEquipmentModelInfo> value) {
		EquipmentModels = value;
	}

}