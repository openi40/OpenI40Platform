package com.openi40.platform.persistence.input.channel.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
