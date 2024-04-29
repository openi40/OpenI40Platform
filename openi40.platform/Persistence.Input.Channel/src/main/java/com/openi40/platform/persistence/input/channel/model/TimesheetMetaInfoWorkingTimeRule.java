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

import com.openi40.scheduler.input.model.time.TimesheetMetaInfoWorkingTimeRuleInputDto;

@Entity
@Table(name = "tsheet_meta_working_time_rule")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "dayOfWeek", column = @Column(name = "day_of_week")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "endTime", column = @Column(name = "end_time")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "startTime", column = @Column(name = "start_time")),
		@AttributeOverride(name = "timesheetMetaCode", column = @Column(name = "tsheet_meta_code")) })
public class TimesheetMetaInfoWorkingTimeRule extends TimesheetMetaInfoWorkingTimeRuleInputDto {
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
