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
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoExceptionRuleInputDto;
@Entity
@Table(name = "tsheet_meta_exc_rule")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "endPeriod", column = @Column(name = "end_period")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "startPeriod", column = @Column(name = "start_period")),
@AttributeOverride(name = "working", column = @Column(name = "working"))
})
public class TimesheetMetaInfoExceptionRule extends TimesheetMetaInfoExceptionRuleInputDto{
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
