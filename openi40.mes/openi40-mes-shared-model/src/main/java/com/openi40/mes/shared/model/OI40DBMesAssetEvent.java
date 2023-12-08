package com.openi40.mes.shared.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mes_asset_event")
@SequenceGenerator(name = "OI40DBMesAssetEventGen", sequenceName = "mes_asset_event_seq", initialValue = 1000, allocationSize = 1)

public class OI40DBMesAssetEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2124822759303718144L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OI40DBMesAssetEventGen")
	@Column(name = "event_id")
	Long eventId = null;
	@Column(name = "event_time")
	Timestamp eventTime = null;
	@Column(name = "mac_asset_code")
	String macAssetCode = null;
	@Column(name = "mac_code")
	String macCode = null;
	@Column(name = "ip_address")
	String ipAddress = null;
	@Column(name = "event_type")
	String eventType = null;
	@Column(name = "event_payload")
	String eventPayload = null;
	@Column(name = "processed_time")
	Timestamp processedTime = null;
	@Column(name = "processed_status")
	String processedStatus = null;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	@Column(name = "alt_code")
	String mesAltCode = null;

	public OI40DBMesAssetEvent() {

	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	public String getMacAssetCode() {
		return macAssetCode;
	}

	public void setMacAssetCode(String macAssetCode) {
		this.macAssetCode = macAssetCode;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventPayload() {
		return eventPayload;
	}

	public void setEventPayload(String eventPayload) {
		this.eventPayload = eventPayload;
	}

	public Timestamp getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(Timestamp processedTime) {
		this.processedTime = processedTime;
	}

	public String getProcessedStatus() {
		return processedStatus;
	}

	public void setProcessedStatus(String processedStatus) {
		this.processedStatus = processedStatus;
	}

	public String getMesAssetCode() {
		return mesAssetCode;
	}

	public void setMesAssetCode(String mesAssetCode) {
		this.mesAssetCode = mesAssetCode;
	}

	public String getMesAltCode() {
		return mesAltCode;
	}

	public void setMesAltCode(String mesAltCode) {
		this.mesAltCode = mesAltCode;
	}

}
