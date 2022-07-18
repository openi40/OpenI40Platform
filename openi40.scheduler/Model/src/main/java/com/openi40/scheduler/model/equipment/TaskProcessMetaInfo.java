package com.openi40.scheduler.model.equipment;

import java.util.HashMap;

import com.openi40.scheduler.common.aps.ICloneable;
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
public class TaskProcessMetaInfo extends AbstractApsObject {
	public static final String TIME4OPERATION_MACHINESPECIFIC = "TIME4OPERATION_MACHINESPECIFIC";
	public static final String PIECE4HOUR = "PIECE4HOUR";
	public static final String TIME4PIECE_MACHINESPECIFIC = "TIME4PIECE_MACHINESPECIFIC";
	public static final String TIME4OPERATION = "TIME4OPERATION";
	public static final String PIECE4HOUR_MACHINESPECIFIC = "PIECE4HOUR_MACHINESPECIFIC";
	public static final String TIME4PIECE = "TIME4PIECE";

	public TaskProcessMetaInfo(ApsData c) {
		super(c);
	}

	public TaskProcessMetaInfo(ApsData c, TaskProcessMetaInfo info) {
		super(c);
		this.setBatchingQuantity(info.getBatchingQuantity());
		this.setBatchingType(info.getBatchingType());
		this.setMachineTime(info.getMachineTime());
		this.setMachineTimeSpecCode(info.getMachineTimeSpecCode());
		this.setManTime(info.getManTime());
		this.setMaxTimeBeforeNextStage(info.getMaxTimeBeforeNextStage());
		this.setMinTimeBeforeNextStage(info.getMinTimeBeforeNextStage());
		this.setSetupTime(info.getSetupTime());
	}

	public enum BatchType {
		NONE, TRANSFER, SPLIT;

	}

	// Predefined man time coefficient in minutes (copied here from OperationModel)
	private double ManTime = 0.0;

	public final double getManTime() {
		return ManTime;
	}

	public final void setManTime(double value) {
		ManTime = value;
	}

	// Predefined setup time in minutes (copied here from OperationModel)
	private double SetupTime = 0.0;

	public final double getSetupTime() {
		return SetupTime;
	}

	public final void setSetupTime(double value) {
		SetupTime = value;
	}

	private String MachineTimeSpecCode = TIME4PIECE;

	public final String getMachineTimeSpecCode() {
		return MachineTimeSpecCode;
	}

	public final void setMachineTimeSpecCode(String value) {
		MachineTimeSpecCode = value;
	}

	// Predefined machine time coefficient in unities specified by
	// MachineTimeSpecCode (copied here from OperationModel)
	private double MachineTime = 0.0;

	public final double getMachineTime() {
		return MachineTime;
	}

	public final void setMachineTime(double value) {
		MachineTime = value;
	}

	// Next cycle operation time minimum offset/delay (minutes)
	private double MinTimeBeforeNextStage = 0.0;

	public final double getMinTimeBeforeNextStage() {
		return MinTimeBeforeNextStage;
	}

	public final void setMinTimeBeforeNextStage(double value) {
		MinTimeBeforeNextStage = value;
	}

	// Next cycle operation time maximum offset/delay (minutes)
	private double MaxTimeBeforeNextStage = 0.0;

	public final double getMaxTimeBeforeNextStage() {
		return MaxTimeBeforeNextStage;
	}

	public final void setMaxTimeBeforeNextStage(double value) {
		MaxTimeBeforeNextStage = value;
	}

	// Batching type (view OperationModel)
	private String BatchingType = BatchType.NONE.toString();

	public final String getBatchingType() {
		return BatchingType;
	}

	public final void setBatchingType(String value) {
		BatchingType = value;
	}

	// Batching quantity (view OperationModel)
	private double BatchingQuantity = 0.0;

	public final double getBatchingQuantity() {
		return BatchingQuantity;
	}

	public final void setBatchingQuantity(double value) {
		BatchingQuantity = value;
	}

	private HashMap<String, TaskProcessMetaInfo> PerMachineMetaInfos = new HashMap<String, TaskProcessMetaInfo>();

	public final void AddMachineMetaInfo(String machineCode, TaskProcessMetaInfo meta) {
		if (PerMachineMetaInfos.containsKey(machineCode)) {
			PerMachineMetaInfos.put(machineCode, meta);
		} else {
			PerMachineMetaInfos.put(machineCode, meta);
		}
	}

	public final void RemoveMachineMetaInfo(String machineCode) {
		if (PerMachineMetaInfos.containsKey(machineCode)) {
			PerMachineMetaInfos.remove(machineCode);
		}
	}

	public final TaskProcessMetaInfo GetMachineMetaInfo(String machineCode) {
		TaskProcessMetaInfo m = null;
		if (PerMachineMetaInfos.containsKey(machineCode)) {
			m = new TaskProcessMetaInfo(this.getContext(), PerMachineMetaInfos.get(machineCode));
		}
		return m;
	}

	@Override
	public ICloneable cleanClone() {
		TaskProcessMetaInfo meta = new TaskProcessMetaInfo(this.getContext(), this);
		return meta;
	}
}