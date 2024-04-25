package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_task")

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApsTaskCode() {
		return apsTaskCode;
	}

	public void setApsTaskCode(String apsTaskCode) {
		this.apsTaskCode = apsTaskCode;
	}

	public String getMesAssetCode() {
		return mesAssetCode;
	}

	public void setMesAssetCode(String mesAssetCode) {
		this.mesAssetCode = mesAssetCode;
	}

}
