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
package com.openi40.scheduler.client.model.tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.aps.ApsMessageDto;
import com.openi40.scheduler.client.model.material.MaterialConsumptionDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;


public class TaskDto extends ClientDto {
	protected String workOrderCode = null;
	protected boolean successfullyScheduled = false;
	protected boolean workOrderRootTask=false;
	protected Date askedDeliveryDateTime=null;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	protected String workCenterCode = null;
	protected String cycleCode=null;
	protected String operationCode=null;
	protected String sequenceCode = null;
	protected String predefinedMachineCode = null;
	protected String scheduledMachineCode = null;
	protected String salesOrderLineCode = null;
	protected double qtyTotal = 0.0;
	protected double qtyProduced = 0.0;
	protected List<ApsMessageDto> messages=new ArrayList<ApsMessageDto>();
	protected String color = null;
	protected TimeSegmentDto setup = new TimeSegmentDto(), work = new TimeSegmentDto();
	protected EquipmentInfoDto setupEquip=new EquipmentInfoDto();
	protected EquipmentInfoDto workEquip=new EquipmentInfoDto();
	protected TimeSegmentDto execution = new TimeSegmentDto();
	
	

	public static class TaskDependency {
		String supplyTaskId = null;

		public String getSupplyTaskId() {
			return supplyTaskId;
		}

		public void setSupplyTaskId(String supplyTaskId) {
			this.supplyTaskId = supplyTaskId;
		}
	}

	protected List<TaskDependency> dependencies = new ArrayList<>();
	protected List<MaterialConsumptionDto> MaterialConsumptions=new ArrayList<MaterialConsumptionDto>();
	public void addSupplyTaskId(String id) {
		TaskDependency dep = new TaskDependency();
		dep.setSupplyTaskId(id);
		dependencies.add(dep);
	}
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
	public boolean isWorkOrderRootTask() {
		return workOrderRootTask;
	}
	public void setWorkOrderRootTask(boolean workOrderRootTask) {
		this.workOrderRootTask = workOrderRootTask;
	}
	public Date getAskedDeliveryDateTime() {
		return askedDeliveryDateTime;
	}
	public void setAskedDeliveryDateTime(Date askedDeliveryDateTime) {
		this.askedDeliveryDateTime = askedDeliveryDateTime;
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
	public String getScheduledMachineCode() {
		return scheduledMachineCode;
	}
	public void setScheduledMachineCode(String scheduledMachineCode) {
		this.scheduledMachineCode = scheduledMachineCode;
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
	public List<ApsMessageDto> getMessages() {
		return messages;
	}
	public void setMessages(List<ApsMessageDto> messages) {
		this.messages = messages;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public TimeSegmentDto getSetup() {
		return setup;
	}
	public void setSetup(TimeSegmentDto setup) {
		this.setup = setup;
	}
	public TimeSegmentDto getWork() {
		return work;
	}
	public void setWork(TimeSegmentDto work) {
		this.work = work;
	}
	public EquipmentInfoDto getSetupEquip() {
		return setupEquip;
	}
	public void setSetupEquip(EquipmentInfoDto setupEquip) {
		this.setupEquip = setupEquip;
	}
	public EquipmentInfoDto getWorkEquip() {
		return workEquip;
	}
	public void setWorkEquip(EquipmentInfoDto workEquip) {
		this.workEquip = workEquip;
	}
	public TimeSegmentDto getExecution() {
		return execution;
	}
	public void setExecution(TimeSegmentDto execution) {
		this.execution = execution;
	}
	public List<TaskDependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<TaskDependency> dependencies) {
		this.dependencies = dependencies;
	}
	public List<MaterialConsumptionDto> getMaterialConsumptions() {
		return MaterialConsumptions;
	}
	public void setMaterialConsumptions(List<MaterialConsumptionDto> materialConsumptions) {
		MaterialConsumptions = materialConsumptions;
	}
}
