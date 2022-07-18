package com.openi40.scheduler.engine.worktime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskProcessInfo;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = IWorkTimeLogic.class, entityClass = TaskEquipmentInfo.class)
public class WorkTimeLogicImpl extends BusinessLogic<TaskEquipmentInfo> implements IWorkTimeLogic {
	static Logger LOGGER=LoggerFactory.getLogger(WorkTimeLogicImpl.class);
	private double calculateWorkTime(Task scheduledActivity,double qty, TaskEquipmentInfo equipment, TaskProcessInfo infos) {
		
		double _totalMachineTime = infos.getMachineTime() * scheduledActivity.getQtyTotal();
		if (infos.getMachineTimeSpecCode() != null) {
			if (infos.getMachineTimeSpecCode().equals(TaskProcessInfo.TIME4PIECE) || infos.getMachineTimeSpecCode().equals(TaskProcessInfo.PIECE4HOUR_MACHINESPECIFIC)) {
				_totalMachineTime = infos.getMachineTime() * qty;
			} else {
				if (infos.getMachineTimeSpecCode().equals(TaskProcessInfo.TIME4OPERATION) || infos.getMachineTimeSpecCode().equals(TaskProcessInfo.TIME4PIECE_MACHINESPECIFIC)) {
					_totalMachineTime = infos.getMachineTime();
				} else {
					if (infos.getMachineTimeSpecCode().equals(TaskProcessInfo.PIECE4HOUR) || infos.getMachineTimeSpecCode().equals(TaskProcessInfo.TIME4OPERATION_MACHINESPECIFIC)) {
						_totalMachineTime = infos.getMachineTime() * qty / 60.0;
					}
				}
			}
		}

		return _totalMachineTime;
	}

	public double calculateWorkTime(Task scheduledActivity, TaskEquipmentInfo equipment) {
		return calculateWorkTime(scheduledActivity, scheduledActivity.getQtyResidual(), equipment);
	}

	@Override
	public double calculateWorkTime(Task scheduledActivity, double qty, TaskEquipmentInfo equipment) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin WorkTimeLogicImpl.calculateWorkTime(task="+scheduledActivity.getCode()+")");
		}
		double workTime = 0.0;
		TaskProcessInfo infos = null;
		String choosenMachineCode = equipment.getExecution() != null && equipment.getExecution().getResource() != null && equipment.getExecution().getResource().getChoosenEquipment() != null ? equipment.getExecution().getResource().getChoosenEquipment().getCode() : null;
		if (equipment.getTaskInfo() != null) {
			infos = choosenMachineCode != null ? equipment.getTaskInfo().GetMachineInfo(choosenMachineCode) : null;
			if (infos == null) {
				infos = equipment.getTaskInfo();
			}
			workTime = calculateWorkTime(scheduledActivity,qty, equipment, infos);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End WorkTimeLogicImpl.calculateWorkTime(task="+scheduledActivity.getCode()+")");
		}
		if (workTime<1.0) workTime=1.0;
		workTime=Math.round(workTime);
		return workTime;
	}
}