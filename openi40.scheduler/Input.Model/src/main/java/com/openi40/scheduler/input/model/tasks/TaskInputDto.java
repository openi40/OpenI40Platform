package com.openi40.scheduler.input.model.tasks;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;
import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;
import com.openi40.scheduler.input.model.cycles.OperationModelInputDto;
import com.openi40.scheduler.input.model.equipment.MachineInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
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
@MappedSuperclass
public class TaskInputDto extends InputDto {
	protected String workOrderCode = null;
	protected boolean successfullyScheduled = false;
	protected String workCenterCode = null;
	protected String cycleCode = null;
	protected String operationCode = null;
	protected String sequenceCode = null;
	protected String predefinedMachineCode = null;
	protected String forcedMachineCode = null;
	protected String scheduledMachineCode = null;
	protected String equipmentSpecCode=null;
	protected boolean workOrderRootTask = false;
	protected Timestamp startPreparation=null,endPreparation=null,startExecution=null,endExecution=null;
	protected Timestamp askedDeliveryDateTime=null;
	protected String salesOrderLineCode = null;
	protected double qtyTotal = 0.0;
	protected double qtyProduced = 0.0;
	protected Integer customPriority = 0;
	protected double setupTime=0.0;
	protected double workTime=0.0;
	protected String setupGroupCode=null;
	
	@Transient
	protected List<TaskResourceReservationInputDto> resourcesReservations = new ArrayList<>();

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkOrderInputDto.class, nullable = false)
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

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkCenterInputDto.class, nullable = true)
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = CycleModelInputDto.class, nullable = true)
	public String getCycleCode() {
		return cycleCode;
	}

	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = OperationModelInputDto.class, nullable = true)
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

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = MachineInputDto.class, nullable = true)
	public String getPredefinedMachineCode() {
		return predefinedMachineCode;
	}

	public void setPredefinedMachineCode(String predefinedMachineCode) {
		this.predefinedMachineCode = predefinedMachineCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = MachineInputDto.class, nullable = true)
	public String getScheduledMachineCode() {
		return scheduledMachineCode;
	}

	public void setScheduledMachineCode(String scheduledMachineCode) {
		this.scheduledMachineCode = scheduledMachineCode;
	}

	public String getForcedMachineCode() {
		return forcedMachineCode;
	}

	public void setForcedMachineCode(String forcedMachineCode) {
		this.forcedMachineCode = forcedMachineCode;
	}

	public boolean isWorkOrderRootTask() {
		return workOrderRootTask;
	}

	public void setWorkOrderRootTask(boolean workOrderRootTask) {
		this.workOrderRootTask = workOrderRootTask;
	}
	

	@Transient

	public List<TaskResourceReservationInputDto> getResourcesReservations() {
		return resourcesReservations;
	}

	public void setResourcesReservations(List<TaskResourceReservationInputDto> resourcesReservations) {
		this.resourcesReservations = resourcesReservations;
	}

	public Timestamp getStartPreparation() {
		return startPreparation;
	}

	public void setStartPreparation(Timestamp startPreparation) {
		this.startPreparation = startPreparation;
	}

	public Timestamp getEndPreparation() {
		return endPreparation;
	}

	public void setEndPreparation(Timestamp endPreparation) {
		this.endPreparation = endPreparation;
	}

	public Timestamp getStartExecution() {
		return startExecution;
	}

	public void setStartExecution(Timestamp startExecution) {
		this.startExecution = startExecution;
	}

	public Timestamp getEndExecution() {
		return endExecution;
	}

	public void setEndExecution(Timestamp endExecution) {
		this.endExecution = endExecution;
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

	

}
