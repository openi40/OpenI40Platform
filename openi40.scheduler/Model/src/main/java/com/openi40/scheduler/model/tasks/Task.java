package com.openi40.scheduler.model.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsMessage;
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

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
@Data
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
	@Setter(value = AccessLevel.NONE)
	protected List<TaskEdge> childTasks = createCleanChild(this, "ChildTasks", TaskEdge.class);

	protected PlanGraphItem decisionGraphItem = null;

	protected TaskEquipmentInfo equipment;

	protected boolean successfullyScheduled = false;

	protected TimeSegmentsGroup mainTimeRange;
	@Setter(value = AccessLevel.NONE)
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

}