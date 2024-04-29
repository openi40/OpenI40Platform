/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
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
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
@Entity
@Table(name = "task")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "cycleCode", column = @Column(name = "cycle_code")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
@AttributeOverride(name = "predefinedMachineCode", column = @Column(name = "pred_mac_code")),
@AttributeOverride(name = "scheduledMachineCode", column = @Column(name = "scheduled_mac_code")),
@AttributeOverride(name = "sequenceCode", column = @Column(name = "sequence_code")),
@AttributeOverride(name = "successfullyScheduled", column = @Column(name = "successfully_scheduled")),
@AttributeOverride(name = "workCenterCode", column = @Column(name = "work_center_code")),
@AttributeOverride(name = "workOrderCode", column = @Column(name = "work_order_code"))
})
public class Task extends TaskInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
}
