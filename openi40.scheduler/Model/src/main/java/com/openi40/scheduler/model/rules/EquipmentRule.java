package com.openi40.scheduler.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;

import lombok.Data;
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
@Data
public class EquipmentRule extends Rule {
	private TaskEquipmentModelOptions equipmentModelOptions = null;

	private List<TaskEquipmentInfo> taskEquipmentInfos = new ArrayList<TaskEquipmentInfo>();

	public EquipmentRule(ApsData context) {
		super(context);
	}

}