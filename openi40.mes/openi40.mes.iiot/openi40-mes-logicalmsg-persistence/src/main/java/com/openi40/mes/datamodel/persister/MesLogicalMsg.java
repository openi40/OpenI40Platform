package com.openi40.mes.datamodel.persister;

import java.sql.Timestamp;

import com.openi40.dbmodel.easydbbeans.BaseDBBean;

public class MesLogicalMsg extends BaseDBBean {
	Long id = null;
	Timestamp message_time = null;
	String message_type = null;
	String message_uuid = null;
	String originalMsgId = null;
	String correlationId = null;
	String from_ref = null;
	String to_ref = null;
	String mes_asset_code = null;
	String mac_code = null;
	String ip_address = null;
	String payload = null;

	public MesLogicalMsg() {
		this.table = "MES_LOGICAL_MSG";
		this.autoIncrements = new String[] { "id" };
		this.primaryKeyProperties = new String[] { "id" };
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getMessage_time() {
		return message_time;
	}

	public void setMessage_time(Timestamp message_time) {
		this.message_time = message_time;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getMessage_uuid() {
		return message_uuid;
	}

	public void setMessage_uuid(String message_uuid) {
		this.message_uuid = message_uuid;
	}

	public String getOriginalMsgId() {
		return originalMsgId;
	}

	public void setOriginalMsgId(String originalMsgId) {
		this.originalMsgId = originalMsgId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getFrom_ref() {
		return from_ref;
	}

	public void setFrom_ref(String from_ref) {
		this.from_ref = from_ref;
	}

	public String getTo_ref() {
		return to_ref;
	}

	public void setTo_ref(String to_ref) {
		this.to_ref = to_ref;
	}

	public String getMes_asset_code() {
		return mes_asset_code;
	}

	public void setMes_asset_code(String mes_asset_code) {
		this.mes_asset_code = mes_asset_code;
	}

	public String getMac_code() {
		return mac_code;
	}

	public void setMac_code(String mac_code) {
		this.mac_code = mac_code;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
