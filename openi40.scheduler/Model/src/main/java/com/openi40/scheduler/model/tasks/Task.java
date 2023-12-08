package com.openi40.scheduler.model.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.AbstractPlantRelatedApsObject;
import com.openi40.scheduler.model.cycle.BatchingInfo;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfoSample;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.messages.UsedSecondaryResourcesInfo;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.planning.PlanGraphItem;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.time.ITimePlacedEntity;
import com.openi40.scheduler.model.time.PeriodsAlignmentType;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;

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

public class Task extends AbstractPlantRelatedApsObject
		implements IReferencingMetaInfo<OperationModel>, ITimePlacedEntity {
	protected boolean workOrderRootTask = false;
	protected boolean productionLock = false;
	protected TaskEquipmentInfoSample sampledTaskEquipmentInfo = null;

	protected static class CollectorTreeVisitor implements ITasksVisitor {
		protected List<Task> Visited = new ArrayList<Task>();

		public List<Task> getVisited() {
			return Visited;
		}

		public void onEdge(TaskEdge edge) {

		}

		public void onNode(Task task) {
			getVisited().add(task);
		}
	}

	protected String salesOrderLineCode = null;
	protected String predefinedMachineCode = null;
	protected String forcedMachineCode = null;
	protected Date askedDeliveryDateTime = null;
	protected TaskStatus status = TaskStatus.NOT_YET_EXECUTED;

	protected List<TaskEdge> childTasks = createCleanChild(this, "ChildTasks", TaskEdge.class);

	protected PlanGraphItem decisionGraphItem = null;

	protected TaskEquipmentInfo equipment;

	protected boolean successfullyScheduled = false;

	protected TimeSegmentsGroup mainTimeRange;

	protected List<ItemConsumed> materialConsumptions = createCleanChild(this, "MaterialConsumptions",
			ItemConsumed.class);

	protected class CountingArray extends ArrayList<ApsMessage> {
		private void recount() {
			int n = 1;
			for (ApsMessage apm : this) {
				apm.setPosition(n);
				n++;
			}
		}

		@Override
		public boolean add(ApsMessage e) {
			e.setPosition(getMessages().size() + 1);
			e.setGlobalPosition(getContext().getApsMessagesCounter());
			getContext().setApsMessagesCounter(getContext().getApsMessagesCounter() + 1);
			return super.add(e);
		}

		@Override
		public boolean addAll(Collection<? extends ApsMessage> c) {
			boolean rv = super.addAll(c);
			recount();
			return rv;
		}

		@Override
		public boolean addAll(int index, Collection<? extends ApsMessage> c) {
			boolean rv = super.addAll(index, c);
			recount();
			return rv;
		}
	}

	protected List<ApsMessage> messages = new CountingArray();

	protected OperationModel metaInfo;

	protected WorkOrder workOrder;

	protected ApsSchedulingSet parentSchedulingSet;

	protected TaskEdge parentTask;

	protected ProductionSupply production;
	protected BatchingInfo producingBatchInfo = null;
	protected List<Rule> constraintRules = new ArrayList<Rule>();
	protected String cycleCode = null;
	protected String operationCode = null;
	protected String sequenceCode = null;

	protected TimeSegmentsGroup setupPhaseExecution;

	protected String workOrderCode = null;

	protected TimeSegmentsGroup workPhaseExecution;
	private PeriodsAlignmentType defaultPreceidingAlignmentType = PeriodsAlignmentType.START_AFTER_END;
	protected double qtyTotal = 0.0;
	protected double qtyProduced = 0.0;
	protected Integer customPriority = 0;
	protected String color = null;
	protected Date minProductionDateConstraint = null;
	protected Date maxProductionDateConstraint = null;
	protected Date acquiredStartSetup = null;
	protected Date acquiredEndSetup = null;
	protected Date acquiredStartWork = null;
	protected Date acquiredEndWork = null;
	protected Date acquiredProductionUpdate = null;
	protected String acquiredMachineCode = null;
	protected List<UsedSecondaryResourcesInfo> acquiredSetupUsedResources = new ArrayList<UsedSecondaryResourcesInfo>();
	protected List<UsedSecondaryResourcesInfo> acquiredWorkUsedResources = new ArrayList<UsedSecondaryResourcesInfo>();

	public double getQtyResidual() {
		return qtyTotal > qtyProduced ? qtyTotal - qtyProduced : 0.0;
	}

	public Task(ApsData context) {
		super(context);
		setEquipment(null);
		resetSchedulingData();
	}

	protected void finalize() throws Throwable {
		getMaterialConsumptions().clear();
		this.materialConsumptions = null;
		getChildTasks().clear();
		this.childTasks = null;
		getConstraintRules().clear();
		setConstraintRules(null);
		setAskedDeliveryDateTime(null);
		setProduction(null);
		setSetupPhaseExecution(null);
		setWorkPhaseExecution(null);
		setMainTimeRange(null);
		getMessages().clear();
		setEquipment(null);
	}

	public <CRule extends Rule> List<CRule> getRulesByClass(Class<CRule> searchedType) {
		return CollectionUtil.getInstance().<Rule, CRule>filterByType(getConstraintRules(), searchedType);
	}

	public Date getEndDateTime() {
		return getMainTimeRange().getEndDateTime();
	}

	public Date getStartDateTime() {
		return getMainTimeRange().getStartDateTime();
	}

	public int getTreeCardinality() {
		int c = 1;
		for (TaskEdge childTask : getChildTasks()) {
			if (childTask.getProducerTask() != null) {
				c += childTask.getProducerTask().getTreeCardinality();
			}
		}
		return c;
	}

	public boolean isSubtreeSuccessfullyScheduled() {
		boolean allChildsAreScheduled = true;
		for (TaskEdge childTask : getChildTasks()) {
			allChildsAreScheduled = childTask.getProducerTask().isSuccessfullyScheduled() && allChildsAreScheduled;
		}
		return allChildsAreScheduled;
	}

	/**
	 * Resets all scheduling infos and releases all resources and material
	 */
	@Override
	public void resetSchedulingData() {
		boolean isLocked = isLocked() || isProductionLock();
		if (isLocked && this.getEquipment() != null) {
			this.sampledTaskEquipmentInfo = new TaskEquipmentInfoSample(getEquipment(), this);
		}
		this.messages = new CountingArray();
		resetResources();
	}

	private void resetResources() {
		if (getProduction() != null) {
			getProduction().setAvailabilityDateTime(null);
		}
		setSuccessfullyScheduled(false);
		getMessages().clear();
		setSetupPhaseExecution(new TimeSegmentsGroup(TimeSegmentType.SETUP_TIME, this));
		setWorkPhaseExecution(new TimeSegmentsGroup(TimeSegmentType.WORK_TIME, this));
		setMainTimeRange(new TimeSegmentsGroup(TimeSegmentType.TASK_WHOLE_TIME, this));
		getMainTimeRange().Add(getSetupPhaseExecution());
		getMainTimeRange().Add(getWorkPhaseExecution());
		if (getEquipment() != null) {
			getEquipment().resetSchedulingData();
		}
		setEquipment(null);
		setDecisionGraphItem(null);
		for (ItemConsumed mc : getMaterialConsumptions()) {
			if (!mc.isLocked()) {
				mc.resetSchedulingData();
			}

		}
		if (getProduction() != null) {
			getProduction().resetSchedulingData();
		}
	}

	public String toString() {
		return "id=>" + getId() + " workOrder:" + workOrderCode + " sequenceCode:" + sequenceCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Task) {
			Task otherTask = (Task) o;
			return this == o || otherTask.getId().equals(getId());
		} else
			return super.equals(o);
	}

	public boolean isWorkOrderRootTask() {
		return workOrderRootTask;
	}

	public void setWorkOrderRootTask(boolean workOrderRootTask) {
		this.workOrderRootTask = workOrderRootTask;
	}

	public boolean isProductionLock() {
		return productionLock;
	}

	public void setProductionLock(boolean productionLock) {
		this.productionLock = productionLock;
	}

	public TaskEquipmentInfoSample getSampledTaskEquipmentInfo() {
		return sampledTaskEquipmentInfo;
	}

	public void setSampledTaskEquipmentInfo(TaskEquipmentInfoSample sampledTaskEquipmentInfo) {
		this.sampledTaskEquipmentInfo = sampledTaskEquipmentInfo;
	}

	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}

	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
	}

	public String getPredefinedMachineCode() {
		return predefinedMachineCode;
	}

	public void setPredefinedMachineCode(String predefinedMachineCode) {
		this.predefinedMachineCode = predefinedMachineCode;
	}

	public String getForcedMachineCode() {
		return forcedMachineCode;
	}

	public void setForcedMachineCode(String forcedMachineCode) {
		this.forcedMachineCode = forcedMachineCode;
	}

	public Date getAskedDeliveryDateTime() {
		return askedDeliveryDateTime;
	}

	public void setAskedDeliveryDateTime(Date askedDeliveryDateTime) {
		this.askedDeliveryDateTime = askedDeliveryDateTime;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public PlanGraphItem getDecisionGraphItem() {
		return decisionGraphItem;
	}

	public void setDecisionGraphItem(PlanGraphItem decisionGraphItem) {
		this.decisionGraphItem = decisionGraphItem;
	}

	public TaskEquipmentInfo getEquipment() {
		return equipment;
	}

	public void setEquipment(TaskEquipmentInfo equipment) {
		this.equipment = equipment;
	}

	public boolean isSuccessfullyScheduled() {
		return successfullyScheduled;
	}

	public void setSuccessfullyScheduled(boolean successfullyScheduled) {
		this.successfullyScheduled = successfullyScheduled;
	}

	public TimeSegmentsGroup getMainTimeRange() {
		return mainTimeRange;
	}

	public void setMainTimeRange(TimeSegmentsGroup mainTimeRange) {
		this.mainTimeRange = mainTimeRange;
	}

	public List<ApsMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ApsMessage> messages) {
		this.messages = messages;
	}

	public OperationModel getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(OperationModel metaInfo) {
		this.metaInfo = metaInfo;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public ApsSchedulingSet getParentSchedulingSet() {
		return parentSchedulingSet;
	}

	public void setParentSchedulingSet(ApsSchedulingSet parentSchedulingSet) {
		this.parentSchedulingSet = parentSchedulingSet;
	}

	public TaskEdge getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskEdge parentTask) {
		this.parentTask = parentTask;
	}

	public ProductionSupply getProduction() {
		return production;
	}

	public void setProduction(ProductionSupply production) {
		this.production = production;
	}

	public BatchingInfo getProducingBatchInfo() {
		return producingBatchInfo;
	}

	public void setProducingBatchInfo(BatchingInfo producingBatchInfo) {
		this.producingBatchInfo = producingBatchInfo;
	}

	public List<Rule> getConstraintRules() {
		return constraintRules;
	}

	public void setConstraintRules(List<Rule> constraintRules) {
		this.constraintRules = constraintRules;
	}

	public String getCycleCode() {
		return cycleCode;
	}

	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public TimeSegmentsGroup getSetupPhaseExecution() {
		return setupPhaseExecution;
	}

	public void setSetupPhaseExecution(TimeSegmentsGroup setupPhaseExecution) {
		this.setupPhaseExecution = setupPhaseExecution;
	}

	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public TimeSegmentsGroup getWorkPhaseExecution() {
		return workPhaseExecution;
	}

	public void setWorkPhaseExecution(TimeSegmentsGroup workPhaseExecution) {
		this.workPhaseExecution = workPhaseExecution;
	}

	public PeriodsAlignmentType getDefaultPreceidingAlignmentType() {
		return defaultPreceidingAlignmentType;
	}

	public void setDefaultPreceidingAlignmentType(PeriodsAlignmentType defaultPreceidingAlignmentType) {
		this.defaultPreceidingAlignmentType = defaultPreceidingAlignmentType;
	}

	public double getQtyTotal() {
		return qtyTotal;
	}

	public void setQtyTotal(double qtyTotal) {
		this.qtyTotal = qtyTotal;
	}

	public double getQtyProduced() {
		return qtyProduced;
	}

	public void setQtyProduced(double qtyProduced) {
		this.qtyProduced = qtyProduced;
	}

	public Integer getCustomPriority() {
		return customPriority;
	}

	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getMinProductionDateConstraint() {
		return minProductionDateConstraint;
	}

	public void setMinProductionDateConstraint(Date minProductionDateConstraint) {
		this.minProductionDateConstraint = minProductionDateConstraint;
	}

	public Date getMaxProductionDateConstraint() {
		return maxProductionDateConstraint;
	}

	public void setMaxProductionDateConstraint(Date maxProductionDateConstraint) {
		this.maxProductionDateConstraint = maxProductionDateConstraint;
	}

	public Date getAcquiredStartSetup() {
		return acquiredStartSetup;
	}

	public void setAcquiredStartSetup(Date acquiredStartSetup) {
		this.acquiredStartSetup = acquiredStartSetup;
	}

	public Date getAcquiredEndSetup() {
		return acquiredEndSetup;
	}

	public void setAcquiredEndSetup(Date acquiredEndSetup) {
		this.acquiredEndSetup = acquiredEndSetup;
	}

	public Date getAcquiredStartWork() {
		return acquiredStartWork;
	}

	public void setAcquiredStartWork(Date acquiredStartWork) {
		this.acquiredStartWork = acquiredStartWork;
	}

	public Date getAcquiredEndWork() {
		return acquiredEndWork;
	}

	public void setAcquiredEndWork(Date acquiredEndWork) {
		this.acquiredEndWork = acquiredEndWork;
	}

	public Date getAcquiredProductionUpdate() {
		return acquiredProductionUpdate;
	}

	public void setAcquiredProductionUpdate(Date acquiredProductionUpdate) {
		this.acquiredProductionUpdate = acquiredProductionUpdate;
	}

	public String getAcquiredMachineCode() {
		return acquiredMachineCode;
	}

	public void setAcquiredMachineCode(String acquiredMachineCode) {
		this.acquiredMachineCode = acquiredMachineCode;
	}

	public List<UsedSecondaryResourcesInfo> getAcquiredSetupUsedResources() {
		return acquiredSetupUsedResources;
	}

	public void setAcquiredSetupUsedResources(List<UsedSecondaryResourcesInfo> acquiredSetupUsedResources) {
		this.acquiredSetupUsedResources = acquiredSetupUsedResources;
	}

	public List<UsedSecondaryResourcesInfo> getAcquiredWorkUsedResources() {
		return acquiredWorkUsedResources;
	}

	public void setAcquiredWorkUsedResources(List<UsedSecondaryResourcesInfo> acquiredWorkUsedResources) {
		this.acquiredWorkUsedResources = acquiredWorkUsedResources;
	}

	public List<TaskEdge> getChildTasks() {
		return childTasks;
	}

	public List<ItemConsumed> getMaterialConsumptions() {
		return materialConsumptions;
	}

}