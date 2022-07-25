package com.openi40.dbmodel.entities;

import java.io.Serializable;

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
@Table(name = "resource_group")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
		@AttributeOverride(name = "infiniteCapacity", column = @Column(name = "infinite_capacity")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "resourcesNumber", column = @Column(name = "resources_number")),
		@AttributeOverride(name = "timesheetMetaInfoCode", column = @Column(name = "tsheet_meta_code")) ,
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})
@Data
public class OI40DBResourceGroup extends OI40DBBaseTimesheetManaged implements Serializable {
	private String departmentCode=null;	
	private Integer resourcesNumber=null;
	
}
