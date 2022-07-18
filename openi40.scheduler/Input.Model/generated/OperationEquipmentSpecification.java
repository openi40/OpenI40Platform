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
import javax.persistence.UniqueConstraint;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.cycles.OperationEquipmentSpecificationInputDto;
@Entity
@Table(name = "op_equip_spec")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "machineTime", column = @Column(name = "mac_time")),
@AttributeOverride(name = "machineTimeSpec", column = @Column(name = "mac_time_spec")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
@AttributeOverride(name = "setupGroupCode", column = @Column(name = "setup_group_code")),
@AttributeOverride(name = "setupTime", column = @Column(name = "setup_time")),
@AttributeOverride(name = "workCenterCode", column = @Column(name = "work_center_code"))
})
public class OperationEquipmentSpecification extends OperationEquipmentSpecificationInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
@StreamLoadRelated(overriddenType=com.openi40.scheduler.input.model.cycles.MachinePriorityInputDto.class, loadType=List.class,relationType=RelationType.ONE2MANY,joinProperty="")
@Override
public void setMachinePriorities(java.util.List<com.openi40.scheduler.input.model.cycles.MachinePriorityInputDto> p){
super.setMachinePriorities(p);
}
@StreamLoadRelated(overriddenType=com.openi40.scheduler.input.model.cycles.SecondaryResourceUseSpecificationInputDto.class, loadType=List.class,relationType=RelationType.ONE2MANY,joinProperty="")
@Override
public void setSecondaryResourcesSpecs(java.util.List<com.openi40.scheduler.input.model.cycles.SecondaryResourceUseSpecificationInputDto> p){
super.setSecondaryResourcesSpecs(p);
}
}
