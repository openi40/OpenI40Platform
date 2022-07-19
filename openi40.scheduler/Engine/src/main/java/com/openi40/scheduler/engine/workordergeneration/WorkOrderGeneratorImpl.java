package com.openi40.scheduler.engine.workordergeneration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.cyclesrouting.IProductionCycleRoutingStrategy;
import com.openi40.scheduler.engine.tasksgeneration.ITaskGenerator;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationEdgeModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.ItemProducedMetaInfo;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.ITasksVisitor;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.tasks.TaskVisitUtil;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IWorkOrderGenerator.class, entityClass = ApsData.class)
public class WorkOrderGeneratorImpl extends BusinessLogic<ApsData> implements IWorkOrderGenerator {
	static Logger LOGGER = LoggerFactory.getLogger(WorkOrderGeneratorImpl.class);

	public List<WorkOrder> createWorkOrder(Product productToProduce, Plant plant, String orderCode,
			String salesOrderLineCode, boolean rootSalesOrderWorkOrder, double qty, Date deliveryDateTime,
			boolean createDependencyWorkOrders, boolean simulated, String color, ApsData context,
			Date minProductionDateConstraint, Date maxProductionDateConstraint) {
		if (productToProduce == null)
			throw new OpenI40Exception("No product passed");
		List<WorkOrder> wo = null;
		IProductionCycleRoutingStrategy taskModelFinder = componentsFactory
				.create(IProductionCycleRoutingStrategy.class, null, context);

		CycleModel cycleModel = taskModelFinder.findAppliableCycleModel(plant, productToProduce, context);
		if (cycleModel != null) {

			wo = createWorkOrder(cycleModel, plant, orderCode, salesOrderLineCode, rootSalesOrderWorkOrder, qty,
					deliveryDateTime, createDependencyWorkOrders, simulated, color, context,
					minProductionDateConstraint, maxProductionDateConstraint);
		} else {
			throw new OpenI40Exception("Item TaskModel not found for " + productToProduce.getCode());
		}
		return wo;
	}

	private List<WorkOrder> createDependingWorkOrders(WorkOrder parentWorkOrder, Plant plant, Task task,
			boolean createDependencyWorkOrder, boolean simulated, ApsData context) {
		List<WorkOrder> woList = new ArrayList<WorkOrder>();
		List<Task> tlist = TaskVisitUtil.Instance.getTasksTreeList(task, parentWorkOrder);

		for (Task currentTask : tlist) {
			int nChild = 1;
			for (ItemConsumed requiredMaterial : currentTask.getMaterialConsumptions()) {
				if (requiredMaterial.getProduct() != null
						&& requiredMaterial.getProduct().isCanBeProducedByScheduler()) {
					if (createDependencyWorkOrder) {
						List<WorkOrder> list = createWorkOrder(requiredMaterial.getProduct(), plant,
								parentWorkOrder.getCode() + "-" + requiredMaterial.getProduct().getCode() + "-"
										+ nChild,
								parentWorkOrder.getSalesOrderLineCode(), false, requiredMaterial.getRequiredQty(),
								parentWorkOrder.getAskedDeliveryDateTime(), createDependencyWorkOrder, simulated,
								parentWorkOrder.getColor(), context, parentWorkOrder.getMinProductionDateConstraint(),
								parentWorkOrder.getMaxProductionDateConstraint());
						nChild++;
						// Create pegging only with first created work order because it is the one
						// suppliyng the actual by algorithm
						// design that is nodes first visit creation.
						WorkOrder producingOrder = list.get(0);

						Pegging pegging = businessModelFactory.create(Pegging.class, context);
						// new Pegging(context, parentWorkOrder, producingOrder);
						pegging.setConsumer(parentWorkOrder);
						pegging.setSupplier(producingOrder);
						pegging.setTotalQty(producingOrder.getProducedPart().getQty());
						parentWorkOrder.getPeggings().add(pegging);
						producingOrder.getPeggings().add(pegging);
						pegging.setEdge(new TaskEdge(context));
						pegging.getEdge().setRappresentedPegging(pegging);
						pegging.getEdge().setBomItemModel(requiredMaterial.getMetaInfo());
						ProducingTaskSearcher taskSearcher = new ProducingTaskSearcher(producingOrder);
						Task producingTask = producingOrder.getRootTask();
						/*
						 * for (int i = 0; taskSearcher.getFoundTask() == null && i <
						 * producingOrder.getRootActivities().size(); i++) {
						 * producingOrder.getRootActivities().get(i).DoTasksTreeVisitOnSameOrder(
						 * taskSearcher); } producingTask = taskSearcher.getFoundTask();
						 */
						if (producingTask == null) {
							throw new OpenI40Exception(
									"Incoherent data structure, no producing task connected to WorkOrder");
						}
						pegging.getEdge().setProducerTask(producingTask);
						pegging.getEdge().setConsumerTask(currentTask);
						pegging.getEdge().setAlignmentType(currentTask.getDefaultPreceidingAlignmentType());
						pegging.getEdge().setConsumingBatchInfo(requiredMaterial.getConsumingBatchInfo());
						if (requiredMaterial.getConsumingBatchInfo() != null
								&& requiredMaterial.getConsumingBatchInfo().getBatchTransferType() != null) {
							switch (requiredMaterial.getConsumingBatchInfo().getBatchTransferType()) {
							case TRANSFER_ALL: {
								pegging.getEdge().setAlignmentType(PeriodsAlignmentType.START_AFTER_END);
							}
								;
								break;
							case GROUPING: {
								pegging.getEdge().setAlignmentType(PeriodsAlignmentType.START_AFTER_START);
							}
								;
								break;
							case CONTINUOUS: {
								pegging.getEdge().setAlignmentType(PeriodsAlignmentType.START_AFTER_START);
							}
								;
								break;
							}
						}
						currentTask.getChildTasks().add(pegging.getEdge());
						producingTask.setParentTask(pegging.getEdge());
						// TODO: ALIGNMENT & BATCHING INFOS MUST BE TAKEN AND PUT IN THE CREATED EDGE
						// BETWEEN TASKS
						requiredMaterial.setTaskProductionLink(producingTask.getProduction());
						CollectionUtil.getInstance().AddCollection(woList, list);
					}
				}
			}
		}
		return woList;
	}

	private static class ProducingTaskSearcher implements ITasksVisitor {
		private WorkOrder workOrderToMatch = null;
		private Task FoundTask = null;

		public final Task getFoundTask() {
			return FoundTask;
		}

		private void setFoundTask(Task value) {
			FoundTask = value;
		}

		public ProducingTaskSearcher(WorkOrder workOrderToMatch) {
			this.workOrderToMatch = workOrderToMatch;
		}

		public final void OnNode(Task task) {
			if (getFoundTask() == null && task.getProduction() != null && task.getProduction().getSuppliedItem() != null
					&& workOrderToMatch.getProducedPart() != null
					&& workOrderToMatch.getProducedPart().getSuppliedItem() != null
					&& task.getProduction().getSuppliedItem().getCode()
							.equals(workOrderToMatch.getProducedPart().getSuppliedItem().getCode())) {
				setFoundTask(task);
			}
		}

		public final void OnEdge(TaskEdge edge) {

		}
	}

	private void printWorkOrderDump(int indentation, WorkOrder wo) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indentation; i++) {
			sb.append(" ");
		}
		sb.append(wo.getCode());
		LOGGER.debug(sb.toString());
		printTaskAndDependencies(indentation + 1, wo, wo.getRootTask());
	}

	private void printTaskAndDependencies(int indentation, WorkOrder wo, Task rootTask) {
		if (rootTask.getWorkOrderCode().equals(wo.getCode())) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < indentation; i++) {
				sb.append(" ");
			}
			sb.append(wo.getCode() + "/" + rootTask.getSequenceCode());
			LOGGER.debug(sb.toString());
			sb = new StringBuffer();
			for (TaskEdge ch : rootTask.getChildTasks()) {
				for (int i = 0; i < indentation; i++) {
					sb.append(" ");
				}
				sb.append(ch.getProducerTask().getCode() + "-->" + ch.getConsumerTask().getCode());
				LOGGER.debug(sb.toString());
				sb = new StringBuffer();
				printTaskAndDependencies(indentation, wo, ch.getProducerTask());
			}
		}
	}

	private void printTaskModel(int indentation, OperationModel taskModel) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indentation; i++) {
			sb.append(" ");
		}
		sb.append(taskModel.getCode() + " - " + taskModel.getDescription());
		LOGGER.info(sb.toString());
		sb = new StringBuffer();
		if (taskModel.getChildTasks() != null) {
			List<OperationEdgeModel> childs = taskModel.getChildTasks();
			for (OperationEdgeModel taskEdgeModel : childs) {
				for (int i = 0; i < indentation; i++) {
					sb.append(" ");
				}
				sb.append(taskModel.getCode() + "--child-->" + taskEdgeModel.child.getCode());
				LOGGER.debug(sb.toString());
				printTaskModel(indentation, taskEdgeModel.child);
			}
		}
	}

	public List<WorkOrder> createWorkOrder(CycleModel cycleModel, Plant plant, String orderCode,
			String salesOrderLineCode, boolean rootSalesOrderWorkOrder, double qty, Date deliveryDateTime,
			boolean createDependencyWorkOrders, boolean simulated, String color, ApsData context,
			Date minProductionDateConstraint, Date maxProductionDateConstraint) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin WorkOrderGeneratorImpl.createWorkOrder(..)", "");
		if (LOGGER.isDebugEnabled())
			printTaskModel(1, cycleModel.getRootOperation());
		List<WorkOrder> woList = new ArrayList<WorkOrder>();
		WorkOrder workOrder = businessModelFactory.create(WorkOrder.class, context);
		workOrder.setCode(orderCode);
		workOrder.setColor(color);
		workOrder.setSalesOrderLineCode(salesOrderLineCode);
		workOrder.setDeliveryDate(deliveryDateTime);
		workOrder.setProducedPart(new ItemProducedMetaInfo(cycleModel.getContext(),
				cycleModel.getRootOperation().getItemProducedModel()));
		workOrder.setTotalQty(qty);
		workOrder.setCycleModel(cycleModel);
		workOrder.setPlantCode(plant.getCode());
		workOrder.setAskedDeliveryDateTime(deliveryDateTime);
		workOrder.setRootSalesOrderWorkOrder(rootSalesOrderWorkOrder);
		workOrder.setMinProductionDateConstraint(minProductionDateConstraint);
		workOrder.setMaxProductionDateConstraint(maxProductionDateConstraint);
		ITaskGenerator schedulableTaskBuilder = this.componentsFactory.create(ITaskGenerator.class,
				cycleModel.getRootOperation(), context);
		Task rootTask = schedulableTaskBuilder.generateWorkOrderTasks(workOrder, context);
		workOrder.setChildTasks(getChilds(rootTask, workOrder));
		woList.add(workOrder);
		if (createDependencyWorkOrders) {
			List<WorkOrder> dependencyWorkOrders = createDependingWorkOrders(workOrder, plant, rootTask,
					createDependencyWorkOrders, simulated, context);
			CollectionUtil.getInstance().AddCollection(woList, dependencyWorkOrders);
		}
		if (LOGGER.isDebugEnabled())
			printWorkOrderDump(1, workOrder);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End WorkOrderGeneratorImpl.createWorkOrder(..)", "");
		return woList;
	}

	private List<Task> getChilds(Task task, WorkOrder wo) {
		List<Task> list = new ArrayList<>();
		if (task.getWorkOrder() == wo) {
			list.add(task);
			for (TaskEdge child : task.getChildTasks()) {
				Task producerTask = child.getProducerTask();
				list.addAll(getChilds(producerTask, wo));
			}
		}
		return list;
	}

	@Override
	public List<WorkOrder> createWorkOrder(SalesOrderLine orderLine, Plant plant) {
		Product productToProduce = orderLine.getProduct();
		String orderCode = orderLine.getCode();
		double qty = orderLine.getResidualQty();
		Date deliveryDateTime = orderLine.getAskedDeliveryDate();
		boolean createDependencyWorkOrders = true;
		boolean simulated = false;
		List<WorkOrder> wos = this.createWorkOrder(productToProduce, plant, orderCode, orderLine.getCode(), true, qty,
				deliveryDateTime, createDependencyWorkOrders, simulated, orderLine.getColor(), plant.getContext(),
				orderLine.getMinProductionDateConstraint(), orderLine.getMaxProductionDateConstraint());

		plant.getWorkOrders().addAll(wos);
		return wos;
	}
}