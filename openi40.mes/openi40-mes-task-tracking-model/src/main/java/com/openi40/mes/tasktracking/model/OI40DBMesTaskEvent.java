package com.openi40.mes.tasktracking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_task_event")
@Data
public class OI40DBMesTaskEvent {
	@Id
	Long id = null;
	@Column(name = "mes_task_code")
	String mesTaskCode = null;
	@Column(name = "machine_code")
	String machineCode = null;
	@Column(name = "eventType")
	String eventType = null;
	@Column(name = "event_time")
	Timestamp eventTime = null;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	

	public OI40DBMesTaskEvent() {
		// TODO Auto-generated constructor stub
	}

}
