package com.openi40.scheduler.model.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.model.TreeVisitType;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.material.ItemProducedMetaInfo;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.tasks.ITasksVisitor;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.tasks.TaskVisitUtil;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
@Data

public class WorkOrder extends AbstractWorkOrder {

	private static final String PEGGINGS = "Peggings";
	
	

	protected static class TaskResettor implements ITasksVisitor {
		private WorkOrder parent = null;

		public TaskResettor(WorkOrder wo) {
			parent = wo;
		}

		public  void onEdge(TaskEdge edge) {
		}

		public  void onNode(Task task) {
			task.resetSchedulingData();
			parent.getOrderExecution().Add(task.getMainTimeRange());
		}
	}

	private static class ActionAssigner implements ITasksVisitor {
		private ApsSchedulingSet action = null;

		public ActionAssigner(ApsSchedulingSet _action) {
			this.action = _action;
		}

		public final void onEdge(TaskEdge edge) {

		}

		public final void onNode(Task task) {
			task.setParentSchedulingSet(action);
		}
	}
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	private boolean rootSalesOrderWorkOrder=false;
	private Date askedDeliveryDateTime=null;
	protected String color = null;
	private String salesOrderLineCode = null;
	private TimeSegmentsGroup OrderExecution = null;

	private ApsSchedulingSet parentSchedulingAction = null;
	@Setter(value = AccessLevel.NONE)
	private List<Pegging> peggings = createCleanChild(this, PEGGINGS, Pegging.class);

	private ItemProducedMetaInfo producedPart = null;

	private Task rootTask = null;
	private List<Task> childTasks=new ArrayList<>();
	private CycleModel cycleModel = null;
	private AbstractOrderLine parentOrderLine = null;

	public WorkOrder(ApsData context) {
		super(context);
		setOrderExecution(new TimeSegmentsGroup(TimeSegmentType.PRODUCTION_ORDER_TIME, this));
	}

	public WorkOrder(ApsData context, ItemProducedMetaInfo producedPart) {
		super(context);
		this.setProducedPart(producedPart);
		setOrderExecution(new TimeSegmentsGroup(TimeSegmentType.PRODUCTION_ORDER_TIME, this));

	}

	public  void DoVisit(IWorkOrderVisitor visitor) {
		DoVisit(visitor, TreeVisitType.NODES_FIRST);
	}

	public final void DoVisit(IWorkOrderVisitor visitor, TreeVisitType visitType) {
		if (visitType == TreeVisitType.NODES_FIRST) {
			visitor.OnNode(this);
		}
		for (Pegging pegging : getPeggings()) {
			if (IsThisWorkOrderConsumer(pegging)) {
				if (visitType == TreeVisitType.NODES_FIRST) {
					visitor.OnEdge(pegging);
				}
				pegging.getSupplier().DoVisit(visitor, visitType);
				if (visitType == TreeVisitType.LEAFS_FIRST) {
					visitor.OnEdge(pegging);
				}
			}
		}
		if (visitType == TreeVisitType.LEAFS_FIRST) {
			visitor.OnNode(this);
		}
	}

	

	/**
	 * Returns true if is a leaf, in other terms work order that is only a producer
	 * for other work orders having peggings with this.
	 */
	public  boolean isLeaf() {
		boolean leaf = true;
		for (Pegging pegging : getPeggings()) {
			leaf = leaf && IsThisWorkOrderProducer(pegging);
		}
		return leaf;
	}

	/**
	 * Returns true if is a root in other terms work order that is only consumer for
	 * other work orders but no work orders depend on it
	 */
	public  boolean isRoot() {
		boolean root = true;
		for (Pegging pegging : getPeggings()) {
			root = root && IsThisWorkOrderConsumer(pegging);
		}
		return root;
	}

	public  boolean isScheduled() {
		boolean scheduled = true;
		if (rootTask != null)
			scheduled = rootTask.isSuccessfullyScheduled() && rootTask.isSubtreeSuccessfullyScheduled();

		return scheduled;
	}

	public boolean IsThisWorkOrderConsumer(Pegging pegging) {
		return pegging.getConsumer() != null && pegging.getConsumer().equals(this);
	}

	public boolean IsThisWorkOrderProducer(Pegging pegging) {
		return pegging.getSupplier() != null && pegging.getSupplier().equals(this);
	}

	@Override
	public void resetSchedulingData() {
		super.resetSchedulingData();
		setOrderExecution(new TimeSegmentsGroup(TimeSegmentType.PRODUCTION_ORDER_TIME, this));
		TaskResettor visitor = new TaskResettor(this);
		if (rootTask != null)
			TaskVisitUtil.Instance.doTasksTreeVisitOnSameOrder(rootTask, visitor);

	}

	public  void setParentSchedulingAction(ApsSchedulingSet value) {
		parentSchedulingAction = value;
		ActionAssigner assigner = new ActionAssigner(value);
		if (rootTask != null)
			TaskVisitUtil.Instance.doTasksTreeVisitOnSameOrder(rootTask, assigner);

	}

	public ProductionSupply getProductionSupply() {
		return rootTask != null ? rootTask.getProduction() : null;
	}
}