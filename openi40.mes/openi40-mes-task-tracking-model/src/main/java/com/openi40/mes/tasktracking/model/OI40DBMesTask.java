package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_task")
@Data
public class OI40DBMesTask {
	@Id
	@Column(name = "code")
	private String code = null;
	@Column(name = "aps_task_code")
	private String apsTaskCode = null;
	@Column(name = "mes_asset_code")
	private String mesAssetCode = null;

	public OI40DBMesTask() {

	}

}
