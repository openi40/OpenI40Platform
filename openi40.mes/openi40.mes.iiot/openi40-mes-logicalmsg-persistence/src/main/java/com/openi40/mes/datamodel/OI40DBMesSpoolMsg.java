package com.openi40.mes.datamodel;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MES_SPOOL_MSG")
@SequenceGenerator(name = "OI40DBMesSpoolMsgGen", sequenceName = "mes_spool_msg_seq", initialValue = 1000, allocationSize = 1)

public class OI40DBMesSpoolMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OI40DBMesSpoolMsgGen")
	@Column(name = "id")
	Long id = null;
	@Column(name = "message_time")
	Timestamp messageTime = null;
	@Column(name = "message_type")
	String messageType = null;
	@Column(name = "message_uuid")
	String messageUuid = null;
	@Column(name = "spool_type")
	String spoolType = null;
	@Column(name = "resend_trheshold")
	Timestamp resendThreshold = null;
	@Column(name = "originalMsgId")
	String originalMsgId = null;
	@Column(name = "correlationId")
	String correlationId = null;
	@Column(name = "from_ref")
	String fromRef = null;
	@Column(name = "to_ref")
	String toRef = null;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	@Column(name = "mac_code")
	String macCode = null;
	@Column(name = "ip_address")
	String ipAddress = null;
	@Column(name = "sent")
	Boolean sent = null;
	@Column(name = "payload")
	String payload;

	public OI40DBMesSpoolMsg() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getSpoolType() {
		return spoolType;
	}

	public void setSpoolType(String spoolType) {
		this.spoolType = spoolType;
	}

	public Timestamp getResendThreshold() {
		return resendThreshold;
	}

	public void setResendThreshold(Timestamp resendThreshold) {
		this.resendThreshold = resendThreshold;
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

	public String getFromRef() {
		return fromRef;
	}

	public void setFromRef(String fromRef) {
		this.fromRef = fromRef;
	}

	public String getToRef() {
		return toRef;
	}

	public void setToRef(String toRef) {
		this.toRef = toRef;
	}

	public String getMesAssetCode() {
		return mesAssetCode;
	}

	public void setMesAssetCode(String mesAssetCode) {
		this.mesAssetCode = mesAssetCode;
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

	public Boolean getSent() {
		return sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
