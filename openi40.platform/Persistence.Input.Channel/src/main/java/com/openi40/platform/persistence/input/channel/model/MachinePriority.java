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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.cycles.MachinePriorityInputDto;

@Entity
@Table(name = "mac_priority")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "machineCode", column = @Column(name = "mac_code")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "priority", column = @Column(name = "priority")),
@AttributeOverride(name = "operationEquipmentSpecCode", column = @Column(name = "op_equip_spec_code"))
})
public class MachinePriority extends MachinePriorityInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
	String operationEquipmentSpecCode=null;
	public String getOperationEquipmentSpecCode() {
		return operationEquipmentSpecCode;
	}
	public void setOperationEquipmentSpecCode(String operationEquipmentSpecCode) {
		this.operationEquipmentSpecCode = operationEquipmentSpecCode;
	}
}
