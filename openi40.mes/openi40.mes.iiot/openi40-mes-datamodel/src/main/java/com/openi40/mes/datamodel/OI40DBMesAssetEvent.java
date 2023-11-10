package com.openi40.mes.datamodel;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mes_asset_event")
@SequenceGenerator(name = "OI40DBMesAssetEventGen", sequenceName = "mes_asset_event_seq", initialValue = 1000, allocationSize = 1)
@Data
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

}
