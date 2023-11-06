package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_task_event_equip")
@Data
public class OI40DBMesTaskEventEquip {
	@Id
	@Column(name = "id")
	Long id = null;
	@Column(name = "resource_code")
	String resourceCode = null;
	@Column(name = "resource_group_code")
	String resourceGroupCode = null;
	int cardinality = 1;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	@Column(name="event_id")
	Long eventId=null;
	public OI40DBMesTaskEventEquip() {

	}

}
