package com.openi40.scheduler.engine.realtime;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.time.RealTimeSegmentRequirements;

@BusinessInterface(entityClass = TaskEquipmentInfo.class)
public interface ITaskUnderProductionTimeRequirementsAnalyzer extends IBusinessLogic<TaskEquipmentInfo> {

	public RealTimeSegmentRequirements analyzeUnderProductionTaskRequirements(Machine usedMachine, Task task,
			TaskEquipmentInfo taskEquipmentInfo, ApsSchedulingSet schedulingSet, ApsData context);
}
