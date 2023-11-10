package com.openi40.mes.tasktracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_task_material_event")
@Data
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

}
