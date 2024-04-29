package com.openi40.scheduler.output.model.tasks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.output.model.OutputDto;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class TaskOutputDto extends OutputDto {
	protected String workOrderCode = null;
	protected boolean successfullyScheduled = false;
	protected String workCenterCode = null;
	protected String cycleCode = null;
	protected String operationCode = null;
	protected String sequenceCode = null;
	protected String predefinedMachineCode = null;
	protected String forcedMachineCode = null;
	protected String scheduledMachineCode = null;
	protected boolean workOrderRootTask = false;
	protected String equipmentSpecCode = null;
	protected Timestamp askedDeliveryDateTime = null;
	protected String salesOrderLineCode = null;
	protected double qtyTotal = 0.0;
	protected double qtyProduced = 0.0;
	protected Integer customPriority = 0;
	protected double setupTime = 0.0;
	protected double workTime = 0.0;
	protected String setupGroupCode = null;
	protected Timestamp minProductionDateConstraint = null;
	protected Timestamp maxProductionDateConstraint = null;
	protected String status = null;
	protected Date acquiredStartSetup = null;
	protected Date acquiredEndSetup = null;
	protected Date acquiredStartWork = null;
	protected Date acquiredEndWork = null;
	protected Date acquiredProductionUpdate = null;
	protected String acquiredMachineCode = null;

	protected List<UsedSecondaryResourcesInfoOutputDto> acquiredSetupUsedResources = new ArrayList<UsedSecondaryResourcesInfoOutputDto>();

	protected List<UsedSecondaryResourcesInfoOutputDto> acquiredWorkUsedResources = new ArrayList<UsedSecondaryResourcesInfoOutputDto>();

	
	public static class SecondaryReservation {
		public SecondaryReservation() {

		}

		String resourceCode = null;
		String secondaryResourceGroupCode = null;
		String slotType = null;
		Date start = null;
		Date end = null;
		public String getResourceCode() {
			return resourceCode;
		}
		public void setResourceCode(String resourceCode) {
			this.resourceCode = resourceCode;
		}
		public String getSecondaryResourceGroupCode() {
			return secondaryResourceGroupCode;
		}
		public void setSecondaryResourceGroupCode(String secondaryResourceGroupCode) {
			this.secondaryResourceGroupCode = secondaryResourceGroupCode;
		}
		public String getSlotType() {
			return slotType;
		}
		public void setSlotType(String slotType) {
			this.slotType = slotType;
		}
		public Date getStart() {
			return start;
		}
		public void setStart(Date start) {
			this.start = start;
		}
		public Date getEnd() {
			return end;
		}
		public void setEnd(Date end) {
			this.end = end;
		}
	}

	public static enum LinkType {
		STOCK, PRODUCTION, PURCHASE
	}

	
	public static class TaskMaterialTransfer {
		String productCode = null;
		String warehouseCode = null;
		String taskCode = null;
		String machineCode = null;
		String workCenterCode = null;
		String supplyTaskCode = null;
		String supplyWorkOrderCode = null;
		String purchaseOrderCode = null;
		String purchaseOrderLineCode = null;
		Date startTransfer = null;
		Date endTransfer = null;
		String transferType = null;
		double batchQty = 0;
		double nrTransfers = 0;
		double qtyReserved = 0;
		LinkType linkType = null;
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getWarehouseCode() {
			return warehouseCode;
		}
		public void setWarehouseCode(String warehouseCode) {
			this.warehouseCode = warehouseCode;
		}
		public String getTaskCode() {
			return taskCode;
		}
		public void setTaskCode(String taskCode) {
			this.taskCode = taskCode;
		}
		public String getMachineCode() {
			return machineCode;
		}
		public void setMachineCode(String machineCode) {
			this.machineCode = machineCode;
		}
		public String getWorkCenterCode() {
			return workCenterCode;
		}
		public void setWorkCenterCode(String workCenterCode) {
			this.workCenterCode = workCenterCode;
		}
		public String getSupplyTaskCode() {
			return supplyTaskCode;
		}
		public void setSupplyTaskCode(String supplyTaskCode) {
			this.supplyTaskCode = supplyTaskCode;
		}
		public String getSupplyWorkOrderCode() {
			return supplyWorkOrderCode;
		}
		public void setSupplyWorkOrderCode(String supplyWorkOrderCode) {
			this.supplyWorkOrderCode = supplyWorkOrderCode;
		}
		public String getPurchaseOrderCode() {
			return purchaseOrderCode;
		}
		public void setPurchaseOrderCode(String purchaseOrderCode) {
			this.purchaseOrderCode = purchaseOrderCode;
		}
		public String getPurchaseOrderLineCode() {
			return purchaseOrderLineCode;
		}
		public void setPurchaseOrderLineCode(String purchaseOrderLineCode) {
			this.purchaseOrderLineCode = purchaseOrderLineCode;
		}
		public Date getStartTransfer() {
			return startTransfer;
		}
		public void setStartTransfer(Date startTransfer) {
			this.startTransfer = startTransfer;
		}
		public Date getEndTransfer() {
			return endTransfer;
		}
		public void setEndTransfer(Date endTransfer) {
			this.endTransfer = endTransfer;
		}
		public String getTransferType() {
			return transferType;
		}
		public void setTransferType(String transferType) {
			this.transferType = transferType;
		}
		public double getBatchQty() {
			return batchQty;
		}
		public void setBatchQty(double batchQty) {
			this.batchQty = batchQty;
		}
		public double getNrTransfers() {
			return nrTransfers;
		}
		public void setNrTransfers(double nrTransfers) {
			this.nrTransfers = nrTransfers;
		}
		public double getQtyReserved() {
			return qtyReserved;
		}
		public void setQtyReserved(double qtyReserved) {
			this.qtyReserved = qtyReserved;
		}
		public LinkType getLinkType() {
			return linkType;
		}
		public void setLinkType(LinkType linkType) {
			this.linkType = linkType;
		}
	}

	
	public static class UsedSecondaryResourcesInfoOutputDto extends OutputDto {
		private String resourceGroup = null;
		private List<String> usedResourcesCodes = new ArrayList<String>();
		public String getResourceGroup() {
			return resourceGroup;
		}
		public void setResourceGroup(String resourceGroup) {
			this.resourceGroup = resourceGroup;
		}
		public List<String> getUsedResourcesCodes() {
			return usedResourcesCodes;
		}
		public void setUsedResourcesCodes(List<String> usedResourcesCodes) {
			this.usedResourcesCodes = usedResourcesCodes;
		}
	}

	
	public static class ApsMessageOutputDto extends OutputDto {
		private Integer position = 0;
		private String taskCode = null;
		private String messageCategory = null;
		private String messageCode = null;
		private String messageDescription = null;
		private String msgLevel = null;
		private String sourceModule = null;
		private String sourceObjectClass = null;
		private Integer globalPosition = 0;
		public Integer getPosition() {
			return position;
		}
		public void setPosition(Integer position) {
			this.position = position;
		}
		public String getTaskCode() {
			return taskCode;
		}
		public void setTaskCode(String taskCode) {
			this.taskCode = taskCode;
		}
		public String getMessageCategory() {
			return messageCategory;
		}
		public void setMessageCategory(String messageCategory) {
			this.messageCategory = messageCategory;
		}
		public String getMessageCode() {
			return messageCode;
		}
		public void setMessageCode(String messageCode) {
			this.messageCode = messageCode;
		}
		public String getMessageDescription() {
			return messageDescription;
		}
		public void setMessageDescription(String messageDescription) {
			this.messageDescription = messageDescription;
		}
		public String getMsgLevel() {
			return msgLevel;
		}
		public void setMsgLevel(String msgLevel) {
			this.msgLevel = msgLevel;
		}
		public String getSourceModule() {
			return sourceModule;
		}
		public void setSourceModule(String sourceModule) {
			this.sourceModule = sourceModule;
		}
		public String getSourceObjectClass() {
			return sourceObjectClass;
		}
		public void setSourceObjectClass(String sourceObjectClass) {
			this.sourceObjectClass = sourceObjectClass;
		}
		public Integer getGlobalPosition() {
			return globalPosition;
		}
		public void setGlobalPosition(Integer globalPosition) {
			this.globalPosition = globalPosition;
		}
	}

	protected List<ApsMessageOutputDto> messages = new ArrayList<>();
	protected List<SecondaryReservation> secondaryReservations = new ArrayList<>();
	protected List<TaskMaterialTransfer> materialTransfer = new ArrayList<>();
	protected Date startPreparation = null, endPreparation = null, startExecution = null, endExecution = null;
	public String getWorkOrderCode() {
		return workOrderCode;
	}
	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}
	public boolean isSuccessfullyScheduled() {
		return successfullyScheduled;
	}
	public void setSuccessfullyScheduled(boolean successfullyScheduled) {
		this.successfullyScheduled = successfullyScheduled;
	}
	public String getWorkCenterCode() {
		return workCenterCode;
	}
	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
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
	public String getScheduledMachineCode() {
		return scheduledMachineCode;
	}
	public void setScheduledMachineCode(String scheduledMachineCode) {
		this.scheduledMachineCode = scheduledMachineCode;
	}
	public boolean isWorkOrderRootTask() {
		return workOrderRootTask;
	}
	public void setWorkOrderRootTask(boolean workOrderRootTask) {
		this.workOrderRootTask = workOrderRootTask;
	}
	public String getEquipmentSpecCode() {
		return equipmentSpecCode;
	}
	public void setEquipmentSpecCode(String equipmentSpecCode) {
		this.equipmentSpecCode = equipmentSpecCode;
	}
	public Timestamp getAskedDeliveryDateTime() {
		return askedDeliveryDateTime;
	}
	public void setAskedDeliveryDateTime(Timestamp askedDeliveryDateTime) {
		this.askedDeliveryDateTime = askedDeliveryDateTime;
	}
	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}
	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
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
	public double getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(double setupTime) {
		this.setupTime = setupTime;
	}
	public double getWorkTime() {
		return workTime;
	}
	public void setWorkTime(double workTime) {
		this.workTime = workTime;
	}
	public String getSetupGroupCode() {
		return setupGroupCode;
	}
	public void setSetupGroupCode(String setupGroupCode) {
		this.setupGroupCode = setupGroupCode;
	}
	public Timestamp getMinProductionDateConstraint() {
		return minProductionDateConstraint;
	}
	public void setMinProductionDateConstraint(Timestamp minProductionDateConstraint) {
		this.minProductionDateConstraint = minProductionDateConstraint;
	}
	public Timestamp getMaxProductionDateConstraint() {
		return maxProductionDateConstraint;
	}
	public void setMaxProductionDateConstraint(Timestamp maxProductionDateConstraint) {
		this.maxProductionDateConstraint = maxProductionDateConstraint;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public List<UsedSecondaryResourcesInfoOutputDto> getAcquiredSetupUsedResources() {
		return acquiredSetupUsedResources;
	}
	public void setAcquiredSetupUsedResources(List<UsedSecondaryResourcesInfoOutputDto> acquiredSetupUsedResources) {
		this.acquiredSetupUsedResources = acquiredSetupUsedResources;
	}
	public List<UsedSecondaryResourcesInfoOutputDto> getAcquiredWorkUsedResources() {
		return acquiredWorkUsedResources;
	}
	public void setAcquiredWorkUsedResources(List<UsedSecondaryResourcesInfoOutputDto> acquiredWorkUsedResources) {
		this.acquiredWorkUsedResources = acquiredWorkUsedResources;
	}
	public List<ApsMessageOutputDto> getMessages() {
		return messages;
	}
	public void setMessages(List<ApsMessageOutputDto> messages) {
		this.messages = messages;
	}
	public List<SecondaryReservation> getSecondaryReservations() {
		return secondaryReservations;
	}
	public void setSecondaryReservations(List<SecondaryReservation> secondaryReservations) {
		this.secondaryReservations = secondaryReservations;
	}
	public List<TaskMaterialTransfer> getMaterialTransfer() {
		return materialTransfer;
	}
	public void setMaterialTransfer(List<TaskMaterialTransfer> materialTransfer) {
		this.materialTransfer = materialTransfer;
	}
	public Date getStartPreparation() {
		return startPreparation;
	}
	public void setStartPreparation(Date startPreparation) {
		this.startPreparation = startPreparation;
	}
	public Date getEndPreparation() {
		return endPreparation;
	}
	public void setEndPreparation(Date endPreparation) {
		this.endPreparation = endPreparation;
	}
	public Date getStartExecution() {
		return startExecution;
	}
	public void setStartExecution(Date startExecution) {
		this.startExecution = startExecution;
	}
	public Date getEndExecution() {
		return endExecution;
	}
	public void setEndExecution(Date endExecution) {
		this.endExecution = endExecution;
	}

}
