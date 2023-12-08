package com.openi40.dbmodel.entities;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "scheduling_set")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "position", column = @Column(name = "position")),
		@AttributeOverride(name = "options", column = @Column(name = "options")),
		@AttributeOverride(name = "algorithmDirection", column = @Column(name = "algo_dir")),
		@AttributeOverride(name = "algorithmType", column = @Column(name = "algo_type")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})

public class OI40DBApsSchedulingSet extends OI40DBBaseEntity implements Serializable{
	int position = 0;
	private String options = null, algorithmDirection = null, algorithmType = null;
	@Id
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAlgorithmDirection() {
		return algorithmDirection;
	}

	public void setAlgorithmDirection(String algorithmDirection) {
		this.algorithmDirection = algorithmDirection;
	}

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

}
