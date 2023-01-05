package com.openi40.scheduler.engine.equipment.configuration;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.tasks.Task;
@BusinessInterface(entityClass = ApsData.class)
public interface IEquipmentConfigurationResourcesExpansor extends IBusinessLogic<ApsData> {
	public List<TaskEquipmentModelInfo> expandList(List<TaskEquipmentModelInfo> models, Task task, ApsSchedulingSet action) ;
}
