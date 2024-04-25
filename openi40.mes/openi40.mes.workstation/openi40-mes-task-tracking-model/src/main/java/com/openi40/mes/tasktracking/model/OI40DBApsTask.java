package com.openi40.mes.tasktracking.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openi40.mes.shared.model.OI40DBMesBaseEntity;
@Entity
@Table(name = "aps_task")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "cycleCode", column = @Column(name = "cycle_code")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
		@AttributeOverride(name = "predefinedMachineCode", column = @Column(name = "pred_mac_code")),
		@AttributeOverride(name = "scheduledMachineCode", column = @Column(name = "scheduled_mac_code")),
		@AttributeOverride(name = "forcedMachineCode", column = @Column(name = "forced_mac_code")),
		@AttributeOverride(name = "sequenceCode", column = @Column(name = "sequence_code")),
		@AttributeOverride(name = "successfullyScheduled", column = @Column(name = "successfully_scheduled")),
		@AttributeOverride(name = "workCenterCode", column = @Column(name = "work_center_code")),
		@AttributeOverride(name = "workOrderCode", column = @Column(name = "work_order_code")),
		@AttributeOverride(name = "workOrderRootTask", column = @Column(name = "work_order_root")),
		@AttributeOverride(name = "equipmentSpecCode", column = @Column(name = "equip_spec_code")),
		@AttributeOverride(name = "startPreparation", column = @Column(name = "start_preparation")),
		@AttributeOverride(name = "endPreparation", column = @Column(name = "end_preparation")),
		@AttributeOverride(name = "startExecution", column = @Column(name = "start_execution")),
		@AttributeOverride(name = "endExecution", column = @Column(name = "end_execution")),
		@AttributeOverride(name = "salesOrderLineCode", column = @Column(name = "sales_line_code")),
		@AttributeOverride(name = "qtyTotal", column = @Column(name = "qty_total")),
		@AttributeOverride(name = "qtyProduced", column = @Column(name = "qty_produced")),
		@AttributeOverride(name = "customPriority", column = @Column(name = "custom_priority")),
		@AttributeOverride(name = "setupTime", column = @Column(name = "setup_time")),
		@AttributeOverride(name = "workTime", column = @Column(name = "work_time")),
		@AttributeOverride(name = "setupGroupCode", column = @Column(name = "setup_group_code")),
		@AttributeOverride(name = "askedDeliveryDateTime", column = @Column(name = "asked_del_time")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
		@AttributeOverride(name="minProductionDateConstraint",column=@Column(name="min_prd_date")),
		@AttributeOverride(name="maxProductionDateConstraint",column=@Column(name="max_prd_date"))

})

public class OI40DBApsTask extends OI40DBMesBaseEntity implements Serializable{
	
	protected Boolean successfullyScheduled = null;
	protected String workCenterCode = null;
	protected String cycleCode = null;
	protected String operationCode = null;
	protected String sequenceCode = null;
	protected String predefinedMachineCode = null;
	protected String forcedMachineCode = null;
	protected String scheduledMachineCode = null;
	protected String equipmentSpecCode = null;
	protected Boolean workOrderRootTask = null;
	protected java.util.Date startPreparation = null, endPreparation = null, startExecution = null, endExecution = null;
	protected java.util.Date askedDeliveryDateTime = null;
	protected String salesOrderLineCode = null;
	protected Double qtyTotal = null;
	protected Double qtyProduced = null;
	protected Integer customPriority = null;
	protected Double setupTime = null;
	protected Double workTime = null;
	protected String setupGroupCode = null;
	protected Date minProductionDateConstraint=null;
	protected Date maxProductionDateConstraint=null;
	public OI40DBApsTask() {
		// TODO Auto-generated constructor stub
	}
	public Boolean getSuccessfullyScheduled() {
		return successfullyScheduled;
	}
	public void setSuccessfullyScheduled(Boolean successfullyScheduled) {
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
	public String getEquipmentSpecCode() {
		return equipmentSpecCode;
	}
	public void setEquipmentSpecCode(String equipmentSpecCode) {
		this.equipmentSpecCode = equipmentSpecCode;
	}
	public Boolean getWorkOrderRootTask() {
		return workOrderRootTask;
	}
	public void setWorkOrderRootTask(Boolean workOrderRootTask) {
		this.workOrderRootTask = workOrderRootTask;
	}
	public java.util.Date getStartPreparation() {
		return startPreparation;
	}
	public void setStartPreparation(java.util.Date startPreparation) {
		this.startPreparation = startPreparation;
	}
	public java.util.Date getEndPreparation() {
		return endPreparation;
	}
	public void setEndPreparation(java.util.Date endPreparation) {
		this.endPreparation = endPreparation;
	}
	public java.util.Date getStartExecution() {
		return startExecution;
	}
	public void setStartExecution(java.util.Date startExecution) {
		this.startExecution = startExecution;
	}
	public java.util.Date getEndExecution() {
		return endExecution;
	}
	public void setEndExecution(java.util.Date endExecution) {
		this.endExecution = endExecution;
	}
	public java.util.Date getAskedDeliveryDateTime() {
		return askedDeliveryDateTime;
	}
	public void setAskedDeliveryDateTime(java.util.Date askedDeliveryDateTime) {
		this.askedDeliveryDateTime = askedDeliveryDateTime;
	}
	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}
	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
	}
	public Double getQtyTotal() {
		return qtyTotal;
	}
	public void setQtyTotal(Double qtyTotal) {
		this.qtyTotal = qtyTotal;
	}
	public Double getQtyProduced() {
		return qtyProduced;
	}
	public void setQtyProduced(Double qtyProduced) {
		this.qtyProduced = qtyProduced;
	}
	public Integer getCustomPriority() {
		return customPriority;
	}
	public void setCustomPriority(Integer customPriority) {
		this.customPriority = customPriority;
	}
	public Double getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(Double setupTime) {
		this.setupTime = setupTime;
	}
	public Double getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Double workTime) {
		this.workTime = workTime;
	}
	public String getSetupGroupCode() {
		return setupGroupCode;
	}
	public void setSetupGroupCode(String setupGroupCode) {
		this.setupGroupCode = setupGroupCode;
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

}
