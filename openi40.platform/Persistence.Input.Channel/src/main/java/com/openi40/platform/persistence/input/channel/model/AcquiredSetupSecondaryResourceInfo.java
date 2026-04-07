package com.openi40.platform.persistence.input.channel.model;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.openi40.scheduler.input.model.tasks.UsedSecondaryResourcesInfoInputDto;

@Entity
@Table(name = "acq_setup_resources")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "taskCode", column = @Column(name = "task_code")),
		@AttributeOverride(name = "rc_codes", column = @Column(name = "rc_codes")),
		@AttributeOverride(name = "resourceGroup", column = @Column(name = "resource_group"))  })

public class AcquiredSetupSecondaryResourceInfo extends AbstractUsedSecondaryResourcesInfo {
	
}
