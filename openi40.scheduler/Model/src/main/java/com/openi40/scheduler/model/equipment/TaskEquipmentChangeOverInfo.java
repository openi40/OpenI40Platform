package com.openi40.scheduler.model.equipment;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class TaskEquipmentChangeOverInfo extends AbstractApsObject {

	public TaskEquipmentChangeOverInfo(ApsData context) {
		super(context);
	}

	private TaskEquipmentInfo BeforeEquipment;

	public final TaskEquipmentInfo getBeforeEquipment() {
		return BeforeEquipment;
	}

	public final void setBeforeEquipment(TaskEquipmentInfo value) {
		BeforeEquipment = value;
	}

	private TaskEquipmentInfo AfterEquipment;

	public final TaskEquipmentInfo getAfterEquipment() {
		return AfterEquipment;
	}

	public final void setAfterEquipment(TaskEquipmentInfo value) {
		AfterEquipment = value;
	}

}