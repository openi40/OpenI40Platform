package com.openi40.scheduler.engine.tasksgeneration;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
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
@BusinessInterface(entityClass = OperationModel.class)
public interface ITaskGenerator extends IBusinessLogic<OperationModel> {
	public Task generateTask(OperationModel model, WorkOrder workOrder, double producedItemQty, boolean recursive, ApsData context);
	public Task generateWorkOrderTasks(WorkOrder workOrder, ApsData context);
	public void initializeTask(Task task, OperationModel model, WorkOrder workOrder, double producedItemQty,
			boolean recursive, ApsData context);
}