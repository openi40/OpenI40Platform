package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_task_material_event")

public class OI40DBMesTaskEventMaterial {
	@Id
	@Column(name = "id")
	Long id = null;
	@Column(name = "product_code")
	String productCode = null;
	@Column(name = "traceability")
	String traceability = null;
	@Column(name = "unity")
	String unity = null;
	@Column(name = "quantity")
	Double quantity = null;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	@Column(name="event_id")
	Long eventId=null;
	public OI40DBMesTaskEventMaterial() {

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getTraceability() {
		return traceability;
	}
	public void setTraceability(String traceability) {
		this.traceability = traceability;
	}
	public String getUnity() {
		return unity;
	}
	public void setUnity(String unity) {
		this.unity = unity;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
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
