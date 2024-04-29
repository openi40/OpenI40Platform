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
import com.openi40.scheduler.input.model.cycles.SecondaryResourceUseSpecificationInputDto;
@Entity
@Table(name = "rc_use_spec")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "afterStartMinutes", column = @Column(name = "after_start_minutes")),
@AttributeOverride(name = "beforeStopMinutes", column = @Column(name = "before_stop_minutes")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "maxQty", column = @Column(name = "max_qty")),
@AttributeOverride(name = "minQty", column = @Column(name = "min_qty")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "qty", column = @Column(name = "qty")),
@AttributeOverride(name = "secondaryResourceGroupCode", column = @Column(name = "rc_group_code")),
@AttributeOverride(name = "useType", column = @Column(name = "use_type")),
@AttributeOverride(name = "usedTime", column = @Column(name = "used_time"))
})
public class SecondaryResourceUseSpecification extends SecondaryResourceUseSpecificationInputDto{
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
