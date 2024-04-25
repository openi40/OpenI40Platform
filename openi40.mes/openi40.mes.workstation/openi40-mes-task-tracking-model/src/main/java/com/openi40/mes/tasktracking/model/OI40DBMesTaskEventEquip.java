package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_task_event_equip")

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceGroupCode() {
		return resourceGroupCode;
	}
	public void setResourceGroupCode(String resourceGroupCode) {
		this.resourceGroupCode = resourceGroupCode;
	}
	public int getCardinality() {
		return cardinality;
	}
	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
	public String getMesAssetCode() {
		return mesAssetCode;
	}
	public void setMesAssetCode(String mesAssetCode) {
		this.mesAssetCode = mesAssetCode;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}
