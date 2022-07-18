package com.openi40.scheduler.model.equipment;

import java.util.HashMap;

import com.openi40.scheduler.common.aps.ICloneable;
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
public class TaskProcessInfo extends TaskProcessMetaInfo {

	public TaskProcessInfo(ApsData c) {
		super(c);
	}

	public TaskProcessInfo(ApsData c, TaskProcessMetaInfo mInfo) {
		super(c, mInfo);

	}

	public TaskProcessInfo(ApsData c, TaskProcessInfo mInfo) {
		super(c, mInfo);
		this.setSetupTime(mInfo.getSetupTime());

		this.setBatchingQuantity(mInfo.getBatchingQuantity());
		this.setBatchingType(mInfo.getBatchingType());
		this.setMachineTime(mInfo.getMachineTime());
		this.setManTime(mInfo.getManTime());
		this.setMaxTimeBeforeNextStage(mInfo.getMaxTimeBeforeNextStage());
		this.setMachineTimeSpecCode(mInfo.getMachineTimeSpecCode());
		this.setMinTimeBeforeNextStage(mInfo.getMinTimeBeforeNextStage());

	}

	private HashMap<String, TaskProcessInfo> PerMachineInfos = new HashMap<String, TaskProcessInfo>();

	public void AddMachineInfo(String machineCode, TaskProcessInfo meta) {
		if (PerMachineInfos.containsKey(machineCode)) {
			PerMachineInfos.put(machineCode, meta);
		} else {
			PerMachineInfos.put(machineCode, meta);
		}
	}

	public void RemoveMachineInfo(String machineCode) {
		if (PerMachineInfos.containsKey(machineCode)) {
			PerMachineInfos.remove(machineCode);
		}
	}

	public TaskProcessInfo GetMachineInfo(String machineCode) {
		TaskProcessInfo m = null;
		if (PerMachineInfos.containsKey(machineCode)) {
			m = new TaskProcessInfo(this.getContext(), PerMachineInfos.get(machineCode));
		}
		return m;
	}

	@Override
	public ICloneable cleanClone() {
		TaskProcessInfo meta = new TaskProcessInfo(this.getContext(), this);
		return meta;
	}
}