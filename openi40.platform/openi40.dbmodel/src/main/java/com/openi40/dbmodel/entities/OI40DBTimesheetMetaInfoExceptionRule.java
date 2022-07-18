package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "tsheet_meta_exc_rule")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "endPeriod", column = @Column(name = "end_period")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "startPeriod", column = @Column(name = "start_period")),
		@AttributeOverride(name = "working", column = @Column(name = "working")),
		@AttributeOverride(name = "timesheetMetaCode", column = @Column(name = "tsheet_meta_code")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")) })
public class OI40DBTimesheetMetaInfoExceptionRule  extends OI40DBBaseEntity implements Serializable {
	private Date startPeriod = null, endPeriod = null;
	private Boolean working = null;
	private String timesheetMetaCode = null;

}
