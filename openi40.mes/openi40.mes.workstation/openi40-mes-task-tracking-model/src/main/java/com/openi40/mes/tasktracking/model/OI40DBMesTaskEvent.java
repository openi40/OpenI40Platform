package com.openi40.mes.tasktracking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_task_event")

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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMesTaskCode() {
		return mesTaskCode;
	}


	public void setMesTaskCode(String mesTaskCode) {
		this.mesTaskCode = mesTaskCode;
	}


	public String getMachineCode() {
		return machineCode;
	}


	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public Timestamp getEventTime() {
		return eventTime;
	}


	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}


	public String getMesAssetCode() {
		return mesAssetCode;
	}


	public void setMesAssetCode(String mesAssetCode) {
		this.mesAssetCode = mesAssetCode;
	}

}
