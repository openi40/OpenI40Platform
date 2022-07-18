package com.openi40.dbmodel.entities;

import java.io.Serializable;
import java.util.UUID;

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
@Table(name = "mac_equip_spec")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "machineCode", column = @Column(name = "mac_code")),
		@AttributeOverride(name = "machineTime", column = @Column(name = "mac_time")),
		@AttributeOverride(name = "machineTimeSpec", column = @Column(name = "mac_time_spec")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
		@AttributeOverride(name = "priority", column = @Column(name = "priority")),
		@AttributeOverride(name = "setupGroupCode", column = @Column(name = "setup_group_code")),
		@AttributeOverride(name = "setupTime", column = @Column(name = "setup_time")),
		@AttributeOverride(name = "minNextPhaseDelay", column = @Column(name = "min_next_phase_delay")) ,
		@AttributeOverride(name = "maxNextPhaseDelay", column = @Column(name = "max_next_phase_delay")) ,
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))})
@Data
public class OI40DBMachineEquipmentSpecification extends OI40DBBaseEntity implements Serializable {

	private String operationCode = null;
	private String setupGroupCode = null;
	private Double setupTime = null;
	private Double machineTime = null;
	private String machineTimeSpec = null;
	private Double minNextPhaseDelay=null;
	private Double maxNextPhaseDelay=null;
}
