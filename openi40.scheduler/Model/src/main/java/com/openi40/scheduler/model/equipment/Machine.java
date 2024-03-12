package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsReservableObject;
import com.openi40.scheduler.model.aps.ApsData;
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

public class Machine extends AbstractApsReservableObject {

	public Machine(ApsData context) {
		super(context);

	}
	private String workCenterCode=null;
	private TaskEquipmentInfo currentEquipmentSetting = null;
	private List<TaskEquipmentInfo> equipmentSettingHistory = new ArrayList<TaskEquipmentInfo>();
	
	@Override
	public void resetSchedulingData() {
		if (getTimesheet() != null) {
			getTimesheet().resetSchedulingData();
		}
		equipmentSettingHistory.clear();
		currentEquipmentSetting = null;
	}
	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		ResourceDepsItemMetaInfo info = super.getResourceItemInfo();
		info.setResourceType("Machine");
		info.setResourceUniqueCode("Machine:"+getCode());
		info.setResource(true);
		return info;
	}
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}

	public TaskEquipmentInfo getCurrentEquipmentSetting() {
		return currentEquipmentSetting;
	}

	public void setCurrentEquipmentSetting(TaskEquipmentInfo currentEquipmentSetting) {
		this.currentEquipmentSetting = currentEquipmentSetting;
	}

	public List<TaskEquipmentInfo> getEquipmentSettingHistory() {
		return equipmentSettingHistory;
	}

	public void setEquipmentSettingHistory(List<TaskEquipmentInfo> equipmentSettingHistory) {
		this.equipmentSettingHistory = equipmentSettingHistory;
	}
}