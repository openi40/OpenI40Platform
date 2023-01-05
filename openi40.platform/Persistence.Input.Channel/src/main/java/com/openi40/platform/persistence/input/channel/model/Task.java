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
package com.openi40.platform.persistence.input.channel.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskResourceReservationInputDto;
import com.openi40.scheduler.input.model.tasks.UsedSecondaryResourcesInfoInputDto;

@Entity
@Table(name = "task")
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
		@AttributeOverride(name = "minProductionDateConstraint", column = @Column(name = "min_prd_date")),
		@AttributeOverride(name = "maxProductionDateConstraint", column = @Column(name = "max_prd_date")),
		@AttributeOverride(name = "status", column = @Column(name = "status")),
		@AttributeOverride(name = "acquiredStartSetup", column = @Column(name = "acq_prep_start")),
		@AttributeOverride(name = "acquiredEndSetup", column = @Column(name = "acq_prep_end")),
		@AttributeOverride(name = "acquiredStartWork", column = @Column(name = "acq_prd_start")),
		@AttributeOverride(name = "acquiredEndWork", column = @Column(name = "acq_prd_end")),
		@AttributeOverride(name = "acquiredProductionUpdate", column = @Column(name = "acq_prd_upd")),
		@AttributeOverride(name = "acquiredMachineCode", column = @Column(name = "acq_machine_code")) })
public class Task extends TaskInputDto {
	@Id
	@Override
	public String getCode() {

		return super.getCode();
	}

	@Override
	public void setCode(String code) {

		super.setCode(code);
	}

	@StreamLoadRelated(overriddenType = TaskResourceReservationInputDto.class, loadType = TaskResourceReservation.class, relationType = RelationType.ONE2MANY, joinProperty = "taskCode")
	@Override
	public void setResourcesReservations(List<TaskResourceReservationInputDto> resourcesReservations) {

		super.setResourcesReservations(resourcesReservations);
	}

	@StreamLoadRelated(overriddenType = UsedSecondaryResourcesInfoInputDto.class, loadType = AcquiredSetupSecondaryResourceInfo.class, relationType = RelationType.ONE2MANY, joinProperty = "taskCode")
	@Override
	public void setAcquiredSetupUsedResources(List<UsedSecondaryResourcesInfoInputDto> acquiredSetupUsedResources) {

		super.setAcquiredSetupUsedResources(acquiredSetupUsedResources);
	}

	@StreamLoadRelated(overriddenType = UsedSecondaryResourcesInfoInputDto.class, loadType = AcquiredWorkSecondaryResourceInfo.class, relationType = RelationType.ONE2MANY, joinProperty = "taskCode")
	@Override
	public void setAcquiredWorkUsedResources(List<UsedSecondaryResourcesInfoInputDto> acquiredWorkUsedResources) {

		super.setAcquiredWorkUsedResources(acquiredWorkUsedResources);
	}

}
