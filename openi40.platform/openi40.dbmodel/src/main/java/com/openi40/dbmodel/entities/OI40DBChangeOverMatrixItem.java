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
@Table(name = "chng_over_matrix_item")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "machineCode", column = @Column(name = "mac_code")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "setupGroupCodeFrom", column = @Column(name = "setup_group_code_from")),
@AttributeOverride(name = "setupGroupCodeTo", column = @Column(name = "setup_group_code_to")),
@AttributeOverride(name = "setupTime", column = @Column(name = "setup_time")),
@AttributeOverride(name = "workCenterCode", column = @Column(name = "work_center_code")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})
@Data
public class OI40DBChangeOverMatrixItem  extends OI40DBBaseEntity implements Serializable{
	protected String setupGroupCodeFrom = null;
	protected String setupGroupCodeTo = null;
	protected String machineCode = null;
	protected String workCenterCode=null;
	protected Double setupTime = null;
}
