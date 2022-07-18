package com.openi40.scheduler.engine.tasksgeneration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.cycle.OperationEdgeModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
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
@DefaultImplementation(implemented = ITaskGenerator.class, entityClass = OperationModel.class)
public class TaskGeneratorImpl extends BusinessLogic<OperationModel> implements ITaskGenerator {
	static Logger LOGGER = LoggerFactory.getLogger(TaskGeneratorImpl.class);

	public Task generateWorkOrderTasks(WorkOrder workOrder, ApsData context) {
		if (workOrder.getRootTask() != null) {
			throw new OpenI40Exception(
					"The work order " + workOrder.toString() + " already has generated ScheduledTasks");
		}

		Task task = generateTask(workOrder.getCycleModel().getRootOperation(), workOrder, workOrder.getTotalQty(), true,
				context);
		task.setAskedDeliveryDateTime(workOrder.getAskedDeliveryDateTime());
		workOrder.setRootTask(task);
		task.setWorkOrderRootTask(true);
		task.setWorkOrderCode(workOrder.getCode());
		task.setWorkOrder(workOrder);
		return task;

	}

	public void initializeTask(Task task, OperationModel model, WorkOrder workOrder, double producedItemQty,
			boolean recursive, ApsData context) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin TaskGeneratorImpl.initializeTask(..)",
					"Creating task with model " + model.getId() + " with qty=" + producedItemQty);
		task.setDefaultPreceidingAlignmentType(model.getDefaultPreceidingAlignmentType());
		task.setCode(task.getId());
		if (workOrder != null) {
			task.setCode(workOrder.getCode() + "-" + model.getSequenceCode());
		}
		String _taskDescription = (model.getDescription() != null ? model.getDescription()
				: "Operation " + model.getSequenceCode());
		task.setDescription(_taskDescription);
		task.setOperationCode(model.getCode());
		task.setCycleCode(model.getCycleCode());
		task.setMetaInfo(model);
		task.setPlantCode(model.getPlantCode());
		task.setColor(workOrder.getColor());
		task.setCompanyCode(model.getCompanyCode());
		task.setParentTask(null);
		task.setSequenceCode(model.getSequenceCode());
		task.setWorkOrderCode(workOrder.getCode());
		task.setWorkOrder(workOrder);
		task.setQtyTotal(producedItemQty);
		task.setProducingBatchInfo(model.getProducingBatchInfo());
		task.setSalesOrderLineCode(workOrder.getSalesOrderLineCode());
		task.setAskedDeliveryDateTime(workOrder.getAskedDeliveryDateTime());
		if (model.getItemProducedModel() != null) {
			// new ProductionSupply(context, task, model.getItemProducedModel())
			task.setProduction(businessModelFactory.create(ProductionSupply.class, context));
			task.getProduction().setOrderCode(task.getWorkOrderCode());
			task.getProduction().setProducingBatchInfo(model.getProducingBatchInfo());
			task.getProduction().setSequenceCode(task.getSequenceCode());
			task.getProduction().setProductionTaskId(task.getId());
			task.getProduction().setTaskCode(task.getCode());
			task.getProduction().setSequenceCode(task.getSequenceCode());
			task.getProduction().setMetaInfo(model.getItemProducedModel());
			task.getProduction().setSuppliedItem(model.getItemProducedModel().getSuppliedItem());
			task.getProduction().setOwnerTask(task);
			task.getProduction().setWorkOrderCode(task.getWorkOrderCode());
			task.getProduction().setWarehouseCode(model.getItemProducedModel().getWarehouseCode());
			task.setQtyTotal(model.getItemProducedModel().getQty() * producedItemQty);

		}
		for (int i = 0; i < model.getBomItems().size(); i++) {
			BomItemModel rqm = model.getBomItems().get(i);
			ItemConsumed rqmCopy = businessModelFactory.create(ItemConsumed.class, context);
			rqmCopy.setProduct(rqm.getConsumedItem());
			rqmCopy.setWarehouseCode(rqm.getWarehouseCode());
			rqmCopy.setRequiredQty(rqm.getQty() * producedItemQty);
			rqmCopy.setMetaInfo(rqm);
			rqmCopy.setConsumingBatchInfo(rqm.getConsumingBatchInfo());
			task.getMaterialConsumptions().add(rqmCopy);
		}

		if (recursive) {
			for (OperationEdgeModel taskEdge : model.getChildTasks()) {
				// If the child task produces a
				double neededQtyFactor = 1.0;
				TaskEdge clonedTaskEdge = businessModelFactory.create(TaskEdge.class, context);
				clonedTaskEdge.setMetaInfo(taskEdge);
				clonedTaskEdge.setConsumerTask(task);
				clonedTaskEdge.setAlignmentType(taskEdge.getAlignmentType());
				clonedTaskEdge.setTimeRangeType(taskEdge.getTimeRangeType());
				clonedTaskEdge.setOffsetMillisecs(taskEdge.getOffsetMillisecs());
				clonedTaskEdge.setConsumingBatchInfo(taskEdge.getConsumingBatchInfo());
				ITaskGenerator specificGenerator = this.componentsFactory.create(ITaskGenerator.class, taskEdge.child,
						context);
				clonedTaskEdge.setProducerTask(specificGenerator.generateTask(taskEdge.child, workOrder,
						neededQtyFactor * producedItemQty, recursive, context));
				task.getChildTasks().add(clonedTaskEdge);
				clonedTaskEdge.getProducerTask().setParentTask(clonedTaskEdge);

			}
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End TaskGeneratorImpl.initializeTask(..)", "Creating task with model " + model.getId());
	}

	public final Task generateTask(OperationModel model, WorkOrder workOrder, double producedItemQty, boolean recursive,
			ApsData context) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin TaskGeneratorImpl.generateTask(..)",
					"Creating task with model " + model.getId() + " with qty=" + producedItemQty);
		Task task = businessModelFactory.create(Task.class, context);
		this.initializeTask(task, model, workOrder, producedItemQty, recursive, context);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End TaskGeneratorImpl.generateTask(..)", "Creating task with model " + model.getId());
		return task;
	}
}