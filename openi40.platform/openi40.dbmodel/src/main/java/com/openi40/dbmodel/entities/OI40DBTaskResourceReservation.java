package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
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
@Entity
@Table(name = "rc_group_reserv")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "resourceCode", column = @Column(name = "rc_code")),
		@AttributeOverride(name = "secondaryResourceGroupCode", column = @Column(name = "rc_group_code")),
		@AttributeOverride(name = "taskCode", column = @Column(name = "task_code")),
		@AttributeOverride(name = "slotType", column = @Column(name = "use_type")),
		@AttributeOverride(name = "start", column = @Column(name = "start_reserv")),
		@AttributeOverride(name = "end", column = @Column(name = "end_reserv")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })
@Data
public class OI40DBTaskResourceReservation extends OI40DBBaseEntity implements Serializable {

	String taskCode=null;
	String resourceCode = null;
	String secondaryResourceGroupCode = null;
	String slotType = null;
	Date start = null;
	Date end = null;

}
