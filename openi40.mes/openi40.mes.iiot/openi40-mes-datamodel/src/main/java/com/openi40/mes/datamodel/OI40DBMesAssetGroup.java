package com.openi40.mes.datamodel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_asset_group")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts")),
		@AttributeOverride(name = "parentObjectType", column = @Column(name = "context_type")),
		@AttributeOverride(name = "parentObjectCode", column = @Column(name = "parent_object_code")) })
@Data

public class OI40DBMesAssetGroup extends OI40DBMesBaseEntity {
	private String parentObjectType = null;
	private String parentObjectCode = null;

	public OI40DBMesAssetGroup() {

	}

}
