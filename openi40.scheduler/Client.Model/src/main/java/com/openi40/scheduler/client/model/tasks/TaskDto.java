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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.aps.ApsMessageDto;
import com.openi40.scheduler.client.model.material.MaterialConsumptionDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;

import lombok.Data;

@Data
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
	protected List<ApsMessageDto> messages=new ArrayList<ApsMessageDto>();
	protected String color = null;
	protected TimeSegmentDto setup = new TimeSegmentDto(), work = new TimeSegmentDto();
	protected EquipmentInfoDto setupEquip=new EquipmentInfoDto();
	protected EquipmentInfoDto workEquip=new EquipmentInfoDto();
	protected TimeSegmentDto execution = new TimeSegmentDto();
	
	
	@Data
	public static class TaskDependency {
		String supplyTaskId = null;
	}

	protected List<TaskDependency> dependencies = new ArrayList<>();
	protected List<MaterialConsumptionDto> MaterialConsumptions=new ArrayList<MaterialConsumptionDto>();
	public void addSupplyTaskId(String id) {
		TaskDependency dep = new TaskDependency();
		dep.setSupplyTaskId(id);
		dependencies.add(dep);
	}
}
