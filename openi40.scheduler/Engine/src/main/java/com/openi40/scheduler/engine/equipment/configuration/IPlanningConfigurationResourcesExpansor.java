package com.openi40.scheduler.engine.equipment.configuration;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.*;
import com.openi40.scheduler.model.aps.*;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.tasks.Task;
@BusinessInterface(entityClass = ApsData.class)
public interface IPlanningConfigurationResourcesExpansor extends IBusinessLogic<ApsData> {
	public List<TaskEquipmentModelInfo> expandList(List<TaskEquipmentModelInfo> models, Task task, ApsSchedulingSet action) ;
}
