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

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;

@Entity
@Table(name = "resource_group")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "departmentCode", column = @Column(name = "dept_code")),
		@AttributeOverride(name = "infiniteCapacity", column = @Column(name = "infinite_capacity")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "resourcesNumber", column = @Column(name = "resources_number")),
		@AttributeOverride(name = "timesheetMetaInfoCode", column = @Column(name = "tsheet_meta_code")) })
public class ResourceGroup extends ResourceGroupInputDto {
	@Id
	@Override
	public String getCode() {

		return super.getCode();
	}

	@Override
	public void setCode(String code) {

		super.setCode(code);
	}

	@StreamLoadRelated(overriddenType = com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto.class, loadType = SecondaryResource.class, relationType = RelationType.ONE2MANY, joinProperty = "resourceGroupCode")
	@Override
	public void setResources(List<SecondaryResourceInputDto> resources) {
		// TODO Auto-generated method stub
		super.setResources(resources);
	}
}
